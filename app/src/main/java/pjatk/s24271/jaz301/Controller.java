package pjatk.s24271.jaz301;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    RestClient client;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/rotation")
    public String rotation(Model model) {
        model.addAttribute("rotation", client.getRotation());
        return "rotation";
    }

    @GetMapping("matches/{region}/{puuid}/{count}")
    public String matches(@PathVariable String region, @PathVariable String puuid, @PathVariable int count, Model model) {
        model.addAttribute("matches", client.getMatches(region, puuid, count));
        return "matches";
    }

    @GetMapping("summoner/{platform}/{name}")
    public String summoner(@PathVariable String platform, @PathVariable String name, Model model) {
        model.addAttribute("summoner", client.getSummoner(platform, name));
        return "summoner";
    }
}
