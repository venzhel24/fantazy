package ru.sfedu.fantazy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sfedu.fantazy.model.*;
import ru.sfedu.fantazy.repository.*;
import ru.sfedu.fantazy.util.UploadUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadService {
    private final RaceRepository raceRepository;
    private final TournamentRepository tournamentRepository;
    private final IndividualRepository individualRepository;
    private final SprintRepository sprintRepository;
    private final SportsmanRepository sportsmanRepository;
    private final MassStartRepository massStartRepository;
    private final PursuitRepository pursuitRepository;

    public boolean uploadRace(byte[] bytes) throws IOException {
        log.debug("start uploadRace[1]");
        String s = UploadUtil.convertPdfToString(bytes);
        Tournament tournament;
        Sportsman sportsman;
        Race race;
        String race_type;

        String[] arr = s.split("\\r?\\n");

        // Получаем строки с информацией о турнире и гонке
        String[] tmp_tournament = arr[arr.length - 10].split("\\s"); // 10 строка с конца, для создания турнира
        UploadUtil.tournamentEdit(tmp_tournament);
        String[] tmp_race = arr[arr.length - 8].split("\\s"); // 8 строка с конца, для создания гонки.
        if (tmp_race.length == 4) race_type = tmp_race[2].concat(" ").concat(tmp_race[3]);
        else race_type = tmp_race[2];

        // Заполняем экземпляр турнира
        log.debug("fill tournament [2]");
        Optional<Tournament> opt_tournament = getTournamentByCityAndYear(arr[arr.length - 11], Integer.parseInt(tmp_tournament[4]));
        if (opt_tournament.isPresent()) {
            tournament = opt_tournament.get();
            if (checkRaceByTournamentAndType(race_type, tournament.getId())) {
                log.debug("Race already exist[3]");
                return false;
            }
        } else {
            tournament = Tournament.builder()
                    .city(arr[arr.length - 11])
                    .start_day(Integer.parseInt(tmp_tournament[0]))
                    .end_day(Integer.parseInt(tmp_tournament[2]))
                    .month(tmp_tournament[3])
                    .year(Integer.parseInt(tmp_tournament[4]))
                    .build();
            log.debug("tournament created - {}", tournament);
            tournamentRepository.save(tournament);
        }

        //Заполняем экземпляр гонки
        race = Race.builder()
                .raceType(race_type)
                .distance(tmp_race[1])
                .tournament(tournament)
                .build();

        log.debug("tournament - {}[2]: ", tournament);
        log.debug("race - {}[3]: ", race);
        raceRepository.save(race);
        String[] row;

        // Заполняем экземпляры спортсменов и результатов
        if (race_type.equals("INDIVIDUAL")) {
            Individual individualResult;
            for (String str : arr) {
                if (!UploadUtil.checkRowResults(str)) continue;
                row = str.split("\\s");
                row = UploadUtil.nameEditIndividual(row);
                if (!getSportsmanByName(row[2]).isPresent()) {
                    sportsman = Sportsman.builder()
                            .name(row[2])
                            .country(row[3])
                            .build();
                    sportsmanRepository.save(sportsman);
                } else {
                    sportsman = getSportsmanByName(row[2]).get();
                }
                individualResult = Individual.builder()
                        .rank(Integer.parseInt(row[0]))
                        .bib(Integer.parseInt(row[1]))
                        .prone_shooting1(Integer.parseInt(row[4]))
                        .standing_shooting1(Integer.parseInt(row[5]))
                        .prone_shooting2(Integer.parseInt(row[6]))
                        .standing_shooting2(Integer.parseInt(row[7]))
                        .total_misses(Integer.parseInt(row[8]))
                        .ski_time(row[9])
                        .result(row[10])
                        .behind(row[11])
                        .race(race)
                        .sportsman(sportsman)
                        .build();
                log.debug("    sportsman - {}", sportsman);
                log.debug("    result - {}", individualResult);
                individualRepository.save(individualResult);
            }
        }

        if (race_type.equals("SPRINT")) {
            Sprint sprintResult;
            for (String str : arr) {
                if (!UploadUtil.checkRowResults(str)) continue;
                row = str.split("\\s");
                row = UploadUtil.nameEditSprint(row);
                if (!getSportsmanByName(row[2]).isPresent()) {
                    sportsman = Sportsman.builder()
                            .name(row[2])
                            .country(row[3])
                            .build();
                    sportsmanRepository.save(sportsman);
                } else {
                    sportsman = getSportsmanByName(row[2]).get();
                }
                sprintResult = Sprint.builder()
                        .rank(Integer.parseInt(row[0]))
                        .bib(Integer.parseInt(row[1]))
                        .prone_shooting(Integer.parseInt(row[4]))
                        .standing_shooting(Integer.parseInt(row[5]))
                        .total_misses(Integer.parseInt(row[6]))
                        .result(row[7])
                        .behind(row[8])
                        .race(race)
                        .sportsman(sportsman)
                        .build();
                log.debug("    sportsman - {}", sportsman);
                log.debug("    result - {}", sprintResult);
                sprintRepository.save(sprintResult);
            }
        }

        if (race_type.equals("MASS START")) {
            MassStart massStart;
            for (String str : arr) {
                if (!UploadUtil.checkRowResults(str)) continue;
                row = str.split("\\s");
                row = UploadUtil.nameEditMassStart(row);
                if (!getSportsmanByName(row[2]).isPresent()) {
                    sportsman = Sportsman.builder()
                            .name(row[2])
                            .country(row[3])
                            .build();
                    sportsmanRepository.save(sportsman);
                } else {
                    sportsman = getSportsmanByName(row[2]).get();
                }
                massStart = MassStart.builder()
                        .rank(Integer.parseInt(row[0]))
                        .bib(Integer.parseInt(row[1]))
                        .prone_shooting1(Integer.parseInt(row[4]))
                        .standing_shooting1(Integer.parseInt(row[5]))
                        .prone_shooting2(Integer.parseInt(row[6]))
                        .standing_shooting2(Integer.parseInt(row[7]))
                        .total_misses(Integer.parseInt(row[8]))
                        .time(row[9])
                        .race(race)
                        .sportsman(sportsman)
                        .build();
                log.debug("    sportsman - {}", sportsman);
                log.debug("    result - {}", massStart);
                massStartRepository.save(massStart);
            }
        }

        if (race_type.equals("PURSUIT")) {
            Pursuit pursuit;
            for (String str : arr) {
                if (!UploadUtil.checkRowResults(str)) continue;
                row = str.split("\\s");
                row = UploadUtil.nameEditPursuit(row);
                if (!getSportsmanByName(row[2]).isPresent()) {
                    sportsman = Sportsman.builder()
                            .name(row[2])
                            .country(row[3])
                            .build();
                    sportsmanRepository.save(sportsman);
                } else {
                    sportsman = getSportsmanByName(row[2]).get();
                }
                pursuit = Pursuit.builder()
                        .rank(Integer.parseInt(row[0]))
                        .bib(Integer.parseInt(row[1]))
                        .prone_shooting1(Integer.parseInt(row[4]))
                        .standing_shooting1(Integer.parseInt(row[5]))
                        .prone_shooting2(Integer.parseInt(row[6]))
                        .standing_shooting2(Integer.parseInt(row[7]))
                        .total_misses(Integer.parseInt(row[8]))
                        .time(row[9])
                        .race(race)
                        .sportsman(sportsman)
                        .build();
                log.debug("    sportsman - {}", sportsman);
                log.debug("    result - {}", pursuit);
                pursuitRepository.save(pursuit);
            }
        }
        log.debug("Race successfully uploaded[4]");
        return true;
    }

    public Optional<Sportsman> getSportsmanByName(String name) {
        List<Sportsman> sportsmen = sportsmanRepository.findAll();
        for(Sportsman s : sportsmen) {
            if(s.getName().equals(name)) return Optional.of(s);
        }
        return Optional.empty();
    }

    public Optional<Tournament> getTournamentByCityAndYear(String city, int year){
        List<Tournament> tournaments = tournamentRepository.findAll();
        for(Tournament t : tournaments) {
            if(t.getCity().equals(city) && t.getYear() == year) return Optional.of(t);
        }
        return Optional.empty();
    }

    private boolean checkRaceByTournamentAndType(String type, long id){
        List<Race> races = raceRepository.findAll();
        for(Race race : races) {
            if(race.getRaceType().equals(type) && race.getTournament().getId() == id)
                return true;
        }
        return false;
    }
}
