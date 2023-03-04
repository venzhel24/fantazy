package ru.sfedu.fantazy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sfedu.fantazy.model.Sportsman;
import ru.sfedu.fantazy.service.SportsmanService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SportsmanController {
    private final SportsmanService sportsmanService;

    @GetMapping("/sportsmen")
    public String findAll(Model model){
        List<Sportsman> sportsmen = sportsmanService.getAll();
        model.addAttribute("sportsmen", sportsmen);

        return "sportsman-list";
    }
}
