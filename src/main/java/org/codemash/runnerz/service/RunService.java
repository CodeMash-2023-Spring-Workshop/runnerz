package org.codemash.runnerz.service;

import org.codemash.runnerz.model.Location;
import org.codemash.runnerz.model.Run;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RunService {

    private final AtomicInteger id = new AtomicInteger(1);
    private final List<Run> runs = new ArrayList<>();

    public RunService() {
        runs.add(new Run(id.getAndIncrement(),
                "Monday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                3,
                Location.INDOOR));

        runs.add(new Run(id.getAndIncrement(),
                "Wednesday Evening Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES),
                6,
                Location.INDOOR));
    }

    public List<Run> findAll() {
        return runs;
    }

    public Run findById(Integer id) {
        return runs.stream().filter(run -> run.id() == id).findFirst().orElseThrow(RuntimeException::new);
    }

    public Run create(Run run) {
        Run newRun = new Run(id.getAndIncrement(),
                run.title(),
                run.startedOn(),
                run.completedOn(),
                run.miles(),
                run.location());

        runs.add(newRun);
        return newRun;
    }

    public void update(Run newRun, Integer id) {
        runs.set(runs.indexOf(findById(id)),newRun);
    }

    public void delete(Integer id) {
        runs.removeIf(run -> Objects.equals(run.id(), id));
    }
}
