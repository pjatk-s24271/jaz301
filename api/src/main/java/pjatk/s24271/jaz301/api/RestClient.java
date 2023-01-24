package pjatk.s24271.jaz301.api;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pjatk.s24271.jaz301.api.objects.ChampionDTO;
import pjatk.s24271.jaz301.api.objects.ChampionInfoDTO;
import pjatk.s24271.jaz301.api.objects.MatchDTO;
import pjatk.s24271.jaz301.api.objects.SummonerDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pjatk.s24271.jaz301.api.RestClient.PlatformHost.*;
import static pjatk.s24271.jaz301.api.RestClient.RegionHost.*;
import static pjatk.s24271.jaz301.api.objects.UtilsKt.filterChampions;

@Component
public class RestClient {
    RestTemplate rest;

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

    public RestClient() {
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
                url(region, "/lol/match/v5/matches/by-puuid/" + puuid + "/ids?start=0&count=" + count),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {
                }
        ).getBody();

        if (ids != null) {
            List<MatchDTO> matches = new ArrayList<>();
            String url = url(region, "/lol/match/v5/matches/");
            for (String id : ids) matches.add(rest.getForObject(url + id, MatchDTO.class));

            return matches;
        } else return null;
    }

    //Get current free champion rotation
    public List<ChampionDTO> getRotation(PlatformHost host) {
        String url = url(host, "/lol/platform/v3/champion-rotations");
        ChampionInfoDTO info = rest.getForObject(url, ChampionInfoDTO.class);

        ResponseEntity<List<ChampionDTO>> response = rest.exchange(
                "http://ddragon.leagueoflegends.com/cdn/13.1.1/data/en_US/champion.json",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ChampionDTO>>() {
                }
        );

        if (info != null) return filterChampions(response.getBody(), info.freeChampionIds);
        else return null;
    }

    private String url(PlatformHost h, String s) {
        return "https://" + platforms.get(h) + s + "?api_key=RGAPI-ff2c023d-eb17-40cf-9bfc-06d132ee28ea";
    }

    private String url(RegionHost h, String s) {
        return "https://" + regions.get(h) + s + "?api_key=RGAPI-ff2c023d-eb17-40cf-9bfc-06d132ee28ea";
    }

    enum PlatformHost {BR1, EUN1, EUW1, JP1, KR, LA1, LA2, NA1, OC1, TR1, RU, PH2, SG2, TH2, TW2, VN2}

    enum RegionHost {AMERICAS, ASIA, EUROPE, SEA}
}