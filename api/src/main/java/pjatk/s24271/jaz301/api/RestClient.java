package pjatk.s24271.jaz301.api;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pjatk.s24271.jaz301.api.objects.ChampionDTO;
import pjatk.s24271.jaz301.api.objects.MatchDTO;
import pjatk.s24271.jaz301.api.objects.SummonerDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pjatk.s24271.jaz301.api.RestClient.PlatformHost.*;
import static pjatk.s24271.jaz301.api.RestClient.RegionHost.*;

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

    public SummonerDTO getSummoner(String name, PlatformHost host) {
        String url = url(EUN1, "/lol/summoner/v4/summoners/by-name/" + name);
        return rest.getForObject(url, SummonerDTO.class);
    }

    public MatchDTO getMatch() {

    }

    public List<ChampionDTO> getRotation() {
        
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