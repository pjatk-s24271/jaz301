package pjatk.s24271.jaz301.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pjatk.s24271.jaz301.api.data.MatchRepository;
import pjatk.s24271.jaz301.api.data.RotationRepository;
import pjatk.s24271.jaz301.api.data.SummonerRepository;
import pjatk.s24271.jaz301.api.data.objects.SummonerDAO;
import pjatk.s24271.jaz301.api.objects.ChampionDTO;
import pjatk.s24271.jaz301.api.objects.MatchDTO;
import pjatk.s24271.jaz301.api.objects.SummonerDTO;

import java.util.List;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    RestClient client;

    @Autowired
    SummonerRepository summonerRepo;

    @Autowired
    RotationRepository rotationRepo;

    @Autowired
    MatchRepository matchRepo;


    final String mData = "/api/data/";
    final String mClient = "/api/client/";


    @GetMapping(mData + "summoner")
    public SummonerDTO summonerData(@RequestParam(value = "puuid") String puuid) {
        SummonerDAO summoner = summonerRepo.findByPuuid(puuid).get(0);
        if (summoner != null) {
            return new SummonerDTO(
                    summoner.accountId,
                    summoner.profileIconId,
                    summoner.revisionDate,
                    summoner.name,
                    summoner.id,
                    summoner.puuid,
                    summoner.summonerLevel
            );
        } else {
            return null;
        }
    }

    @GetMapping(mClient + "summoner")
    public SummonerDTO summonerClient(@RequestParam(value = "name") String name, @RequestParam(value = "region") String region) {
        return client.getSummoner(name, RestClient.PlatformHost.valueOf(region));
    }

    @GetMapping(mData + "match")
    public MatchDTO matchData() {
        return matchRepo.findById();
    }

    @GetMapping(mClient + "match")
    public MatchDTO matchClient() {
        return client.getMatch();
    }

    @GetMapping(mData + "rotation")
    public List<ChampionDTO> rotationData() {
        return rotationRepo.findById();
    }

    @GetMapping(mClient + "rotation")
    public List<ChampionDTO> rotationClient() {
        return client.getRotation();
    }
}
