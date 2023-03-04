package ru.sfedu.fantazy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sfedu.fantazy.model.Sportsman;
import ru.sfedu.fantazy.repository.SportsmanRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SportsmanService {
    private final SportsmanRepository sportsmanRepository;

    public Sportsman getById(long id){
        return sportsmanRepository.getById(id);
    }

    public List<Sportsman> getAll(){
        return sportsmanRepository.findAll();
    }
}
