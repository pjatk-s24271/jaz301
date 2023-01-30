package pjatk.s24271.jaz301;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pjatk.s24271.jaz301.api.dto.ChampionDTO;
import pjatk.s24271.jaz301.api.dto.MatchDTO;
import pjatk.s24271.jaz301.api.dto.SummonerDTO;

import java.util.List;

@Component
public class RestClient {
    RestTemplate rest;
    String url = "https://alteratom.com:8080/";

    public RestClient() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        this.rest = builder.build();
    }

    public List<MatchDTO> getMatches(String region, String puuid, int count) {
        return rest.exchange(
                url + "api/data/match/" + region + "/" + puuid + "/" + count,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MatchDTO>>() {
                }
        ).getBody();
    }

    public SummonerDTO getSummoner(String host, String name) {
        return rest.getForObject(url + "api/client/summoner/" + host + '/' + name, SummonerDTO.class);
    }

    public List<ChampionDTO> getRotation() {
        return rest.exchange(
                url + "api/data/rotation",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ChampionDTO>>() {
                }
        ).getBody();
    }
}