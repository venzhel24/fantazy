package ru.sfedu.fantazy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sfedu.fantazy.model.Race;
import ru.sfedu.fantazy.repository.RaceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RaceService {
    private final RaceRepository raceRepository;

    public Race getById(long id){
        return raceRepository.getById(id);
    }

    public List<Race> getAll(){
        return raceRepository.findAll();
    }

}
