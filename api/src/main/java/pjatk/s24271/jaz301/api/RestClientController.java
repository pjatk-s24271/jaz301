package pjatk.s24271.jaz301.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pjatk.s24271.jaz301.api.objects.ChampionDTO;
import pjatk.s24271.jaz301.api.objects.MatchDTO;
import pjatk.s24271.jaz301.api.objects.SummonerDTO;

import java.util.List;


@RestController
@RequestMapping("/api/client/")
public class RestClientController {

    @Autowired
    RestClient client;

    @GetMapping("summoner/{platform}/{name}")
    public SummonerDTO summonerClient(@PathVariable(value = "name") String name, @PathVariable(value = "platform") String platform) {
        return client.getSummoner(name, RestClient.PlatformHost.valueOf(platform));
    }

    @GetMapping("match/{region}/{puuid}/{count}")
    public List<MatchDTO> matchClient(
            @PathVariable(value = "region") String region,
            @PathVariable(value = "puuid") String puuid,
            @PathVariable(value = "count") int count
    ) {
        return client.getMatches(RestClient.RegionHost.valueOf(region), puuid, count);
    }

    @GetMapping("rotation/{platform}")
    public List<ChampionDTO> rotationClient(@PathVariable(value = "platform") String platform) {
        return client.getRotation(RestClient.PlatformHost.valueOf(platform));
    }
}
