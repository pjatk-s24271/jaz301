package pjatk.s24271.jaz301.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pjatk.s24271.jaz301.api.data.SummonerDAO;
import pjatk.s24271.jaz301.api.data.SummonerRepository;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    RestClient client;

    @Autowired
    SummonerRepository summonerRepo;

    @GetMapping("/get")
    public String get(@RequestParam(value = "name") String name, @RequestParam(value = "region") String region) {
        return client.getSummoner(name, RestClient.PlatformHost.valueOf(region)).puuid;
    }

    @GetMapping("/put")
    public String put(@RequestParam(value = "name") String name, @RequestParam(value = "region") String region) {
        SummonerDTO summoner = client.getSummoner(name, RestClient.PlatformHost.valueOf(region));
        summonerRepo.save(new SummonerDAO(
                summoner.accountId,
                summoner.profileIconId,
                summoner.revisionDate,
                summoner.name,
                summoner.id,
                summoner.puuid,
                summoner.summonerLevel
        ));
        return "done";
    }
}
