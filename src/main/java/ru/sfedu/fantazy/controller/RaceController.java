package ru.sfedu.fantazy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sfedu.fantazy.model.Race;
import ru.sfedu.fantazy.service.RaceService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RaceController {
    private final RaceService raceService;

    @GetMapping("/races")
    public String findAll(Model model){
        List<Race> races = raceService.getAll();
        model.addAttribute("races", races);

        return "race-list";
    }
}
