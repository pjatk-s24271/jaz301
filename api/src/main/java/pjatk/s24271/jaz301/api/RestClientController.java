package pjatk.s24271.jaz301.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pjatk.s24271.jaz301.api.dto.ChampionDTO;
import pjatk.s24271.jaz301.api.dto.MatchDTO;
import pjatk.s24271.jaz301.api.dto.SummonerDTO;

import java.util.List;


@RestController
@RequestMapping("/api/client/")
public class RestClientController {

    @Autowired
    RiotRestClient client;

    @GetMapping("summoner/{platform}/{name}")
    public SummonerDTO summonerGet(@PathVariable String platform, @PathVariable String name) {
        return client.getSummoner(RiotRestClient.PlatformHost.valueOf(platform), name);
    }

    @GetMapping("match/{region}/{puuid}/{count}")
    public List<MatchDTO> matchGet(
            @PathVariable String region,
            @PathVariable String puuid,
            @PathVariable int count
    ) {
        return client.getMatches(RiotRestClient.RegionHost.valueOf(region), puuid, count);
    }

    @GetMapping("rotation/{platform}")
    public List<ChampionDTO> rotationGet(@PathVariable String platform) {
        return client.getRotation(RiotRestClient.PlatformHost.valueOf(platform));
    }

    @PutMapping("key")
    public void updateApiKey(@RequestBody String apiKey) {
        client.apiKey = apiKey;
    }
}
