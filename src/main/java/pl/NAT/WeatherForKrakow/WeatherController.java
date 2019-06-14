package pl.NAT.WeatherForKrakow;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Date;

@Controller
public class WeatherController {
    @Autowired
    WeatherService weatherService;

    @GetMapping("/weather/krakow")
    public String weather() {

        weatherService.saveTemperature();

        return "weather_krakow";
    }

    @PostMapping("/weather/krakow")
    public String showWeather(@RequestParam("startDate") Date startDate,
                              @RequestParam("endDate") Date endDate,
                              Model model) {

        model.addAttribute("average", weatherService.getAverageTemperature(startDate, endDate));
        return "weather_krakow";
    }
}


