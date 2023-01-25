package pjatk.s24271.jaz301.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pjatk.s24271.jaz301.api.data.MatchRepository;
import pjatk.s24271.jaz301.api.data.RotationRepository;
import pjatk.s24271.jaz301.api.data.SummonerRepository;
import pjatk.s24271.jaz301.api.data.objects.ChampionDAO;
import pjatk.s24271.jaz301.api.data.objects.SummonerDAO;
import pjatk.s24271.jaz301.api.objects.ChampionDTO;
import pjatk.s24271.jaz301.api.objects.MatchDTO;
import pjatk.s24271.jaz301.api.objects.SummonerDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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


    @GetMapping(mData + "summoner/{puuid}")
    public SummonerDTO summonerData(@PathVariable("puuid") String puuid) {
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

    @GetMapping(mClient + "summoner/{platform}/{name}")
    public SummonerDTO summonerClient(@PathVariable(value = "name") String name, @PathVariable(value = "platform") String platform) {
        return client.getSummoner(name, RestClient.PlatformHost.valueOf(platform));
    }

    @GetMapping(mData + "match/{region}/{puuid}/{count}")
    public List<MatchDTO> matchData(
            @PathVariable(value = "region") String region,
            @PathVariable(value = "puuid") String puuid,
            @PathVariable(value = "count") int count) {
        return matchRepo.getLast(region, puuid, count).stream().map(m ->
                new MatchDTO(
                        m.puuid,
                        m.region,
                        m.id,
                        m.assists,
                        m.deaths,
                        m.kills,
                        m.startTimestamp
                )
        ).collect(Collectors.toList());
    }

    @GetMapping(mClient + "match/{region}/{puuid}/{count}")
    public List<MatchDTO> matchClient(
            @PathVariable(value = "region") String region,
            @PathVariable(value = "puuid") String puuid,
            @PathVariable(value = "count") int count
    ) {
        return client.getMatches(RestClient.RegionHost.valueOf(region), puuid, count); //todo
    }

    @GetMapping(mData + "rotation")
    public List<ChampionDTO> rotationData() {
        List<ChampionDAO> champs = rotationRepo.findAll();
        List<ChampionDTO> champions = new ArrayList<>();

        for (ChampionDAO champ : champs) champions.add(new ChampionDTO(champ.name, champ.key));
        return champions;
    }

    @GetMapping(mClient + "rotation/{platform}")
    public List<ChampionDTO> rotationClient(@PathVariable(value = "platform") String platform) {
        return client.getRotation(RestClient.PlatformHost.valueOf(platform)); //todo
    }
}
