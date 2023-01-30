package pjatk.s24271.jaz301.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pjatk.s24271.jaz301.api.data.MatchRepository;
import pjatk.s24271.jaz301.api.data.RotationRepository;
import pjatk.s24271.jaz301.api.data.SummonerRepository;
import pjatk.s24271.jaz301.api.data.objects.ChampionDAO;
import pjatk.s24271.jaz301.api.data.objects.MatchDAO;
import pjatk.s24271.jaz301.api.data.objects.SummonerDAO;
import pjatk.s24271.jaz301.api.dto.ChampionDTO;
import pjatk.s24271.jaz301.api.dto.MatchDTO;
import pjatk.s24271.jaz301.api.dto.SummonerDTO;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/data/")
public class RestDataController {

    @Autowired
    SummonerRepository summonerRepo;

    @Autowired
    RotationRepository rotationRepo;

    @Autowired
    MatchRepository matchRepo;

    @Autowired
    RiotRestClient client;


    @GetMapping("summoner/{puuid}")
    public SummonerDTO summonerGet(@PathVariable String puuid) {
        List<SummonerDAO> list = summonerRepo.findByPuuid(puuid);
        if (list.isEmpty()) return null;

        SummonerDAO summoner = list.get(0);
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
        } else return null;
    }

    @GetMapping("match/{region}/{puuid}/{count}")
    public List<MatchDTO> matchGet(
            @PathVariable String region,
            @PathVariable String puuid,
            @PathVariable int count) {
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

    @GetMapping("rotation")
    public List<ChampionDTO> rotationGet() {
        List<ChampionDAO> champs = rotationRepo.findAll();
        return champs.stream().map(champ -> new ChampionDTO(champ.name, champ.key)).collect(Collectors.toList());
    }

    @PostMapping("summoner/{platform}/{name}")
    public void summonerPost(@PathVariable String name, @PathVariable String platform) {
        SummonerDTO s = client.getSummoner(RiotRestClient.PlatformHost.valueOf(platform), name);
        SummonerDAO summoner = new SummonerDAO(
                s.accountId,
                s.profileIconId,
                s.revisionDate,
                s.name,
                s.id,
                s.puuid,
                s.summonerLevel
        );
        summonerRepo.save(summoner);
    }

    @PostMapping("match/{region}/{puuid}/{count}")
    public void matchPost(
            @PathVariable String region,
            @PathVariable String puuid,
            @PathVariable int count
    ) {
        List<MatchDTO> m = client.getMatches(RiotRestClient.RegionHost.valueOf(region), puuid, count);
        List<MatchDAO> matches = m.stream().map(match ->
                new MatchDAO(
                        match.puuid,
                        match.region,
                        match.id,
                        match.assists,
                        match.deaths,
                        match.kills,
                        match.startTimestamp
                )
        ).toList();
        matchRepo.saveAll(matches);
    }

    @PostMapping("rotation/{platform}")
    public void rotationPost(@PathVariable String platform) {
        List<ChampionDTO> c = client.getRotation(RiotRestClient.PlatformHost.valueOf(platform));
        List<ChampionDAO> champions = c.stream().map(champ -> new ChampionDAO(champ.name, champ.key)).toList();
        rotationRepo.deleteAll();
        rotationRepo.saveAll(champions);
    }
}
