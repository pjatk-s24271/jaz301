package pjatk.s24271.jaz301.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pjatk.s24271.jaz301.api.dto.*;


import java.util.*;
import java.util.stream.Collectors;

import static pjatk.s24271.jaz301.api.RiotRestClient.PlatformHost.*;
import static pjatk.s24271.jaz301.api.RiotRestClient.RegionHost.*;

@Component
public class RiotRestClient {
    RestTemplate rest;

    String apiKey = "";

    static Map<RegionHost, String> regions = new HashMap<>();
    static Map<PlatformHost, String> platforms = new HashMap<>();

    static {
        regions.put(AMERICAS, "americas.api.riotgames.com");
        regions.put(ASIA, "asia.api.riotgames.com");
        regions.put(EUROPE, "europe.api.riotgames.com");
        regions.put(SEA, "sea.api.riotgames.com");
        platforms.put(BR1, "br1.api.riotgames.com");
        platforms.put(EUN1, "eun1.api.riotgames.com");
        platforms.put(EUW1, "euw1.api.riotgames.com");
        platforms.put(JP1, "jp1.api.riotgames.com");
        platforms.put(KR, "kr.api.riotgames.com");
        platforms.put(LA1, "la1.api.riotgames.com");
        platforms.put(LA2, "la2.api.riotgames.com");
        platforms.put(NA1, "na1.api.riotgames.com");
        platforms.put(OC1, "oc1.api.riotgames.com");
        platforms.put(TR1, "tr1.api.riotgames.com");
        platforms.put(RU, "ru.api.riotgames.com");
        platforms.put(PH2, "ph2.api.riotgames.com");
        platforms.put(SG2, "sg2.api.riotgames.com");
        platforms.put(TH2, "th2.api.riotgames.com");
        platforms.put(TW2, "tw2.api.riotgames.com");
        platforms.put(VN2, "vn2.api.riotgames.com");
    }

    public RiotRestClient() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        this.rest = builder.build();
    }

    //Get summoner by name
    public SummonerDTO getSummoner(String name, PlatformHost host) {
        String url = url(host, "/lol/summoner/v4/summoners/by-name/" + name);
        return rest.getForObject(url, SummonerDTO.class);
    }

    //Return last {count} matches of summoner with {puuid}
    public List<MatchDTO> getMatches(RegionHost region, String puuid, int count) {

        List<String> ids = rest.exchange(
                url(region, "/lol/match/v5/matches/by-puuid/" + puuid + "/ids", "start", "0", "count", "" + count),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {
                }
        ).getBody();

        if (ids != null) {
            List<MatchDTO> matches = new ArrayList<>();
            for (String id : ids) {
                String url = url(region, "/lol/match/v5/matches/" + id);
                MatchRiotDTO match = rest.getForObject(url, MatchRiotDTO.class);
                if (match == null) continue;

                MatchRiotDTO.InfoDto.ParticipantDto participant = match.info.participants.stream().filter(p ->
                        Objects.equals(p.puuid, puuid)
                ).toList().get(0);

                matches.add(new MatchDTO(
                        puuid,
                        region.toString(),
                        match.metadata.matchId,
                        participant.assists,
                        participant.deaths,
                        participant.kills,
                        match.info.gameStartTimestamp
                ));
            }

            return matches;
        } else return null;
    }

    //Get current free champion rotation
    public List<ChampionDTO> getRotation(PlatformHost host) {
        String url = url(host, "/lol/platform/v3/champion-rotations");

        //Get list of ids of free champions
        ChampionInfoDTO info = rest.getForObject(url, ChampionInfoDTO.class);

        //Get json of champions
        String json = rest.getForObject(
                "http://ddragon.leagueoflegends.com/cdn/13.1.1/data/en_US/champion.json",
                String.class
        );

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<ChampionDTO> champions = new ArrayList<>();

        try {
            Iterator<Map.Entry<String, JsonNode>> fields =
                    mapper.readTree(json).at("/data").fields();

            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                JsonNode value = field.getValue();
                String text = value.toString();

                champions.add(
                        mapper.readValue(text, ChampionDTO.class)
                );
            }

            if (info == null) return null;

            return champions.stream().filter((ChampionDTO champ) ->
                    info.freeChampionIds.contains(champ.key)).collect(Collectors.toList());
        } catch (Exception ignored) {
            return null;
        }
    }

    private String url(PlatformHost host, String path, String... params) {
        return _url("https://" + platforms.get(host) + path, params);
    }

    private String url(RegionHost host, String path, String... params) {
        return _url("https://" + regions.get(host) + path, params);
    }

    private String _url(String path, String... qp) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(path)
                .queryParam("api_key", apiKey);

        List<String> params = Arrays.stream(qp).toList();
        for (int i = 0; i < qp.length; i += 2) {
            builder.queryParam(params.get(i), params.get(i + 1));
        }

        return builder.build().toUriString();
    }

    public enum PlatformHost {BR1, EUN1, EUW1, JP1, KR, LA1, LA2, NA1, OC1, TR1, RU, PH2, SG2, TH2, TW2, VN2}

    public enum RegionHost {AMERICAS, ASIA, EUROPE, SEA}
}