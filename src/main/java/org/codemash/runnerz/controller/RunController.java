package org.codemash.runnerz.controller;

import org.codemash.runnerz.model.Location;
import org.codemash.runnerz.model.Run;
import org.codemash.runnerz.repository.RunRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping
    public List<Run> findAll() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    public Run findById(@PathVariable Integer id) {
        return runRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody Run run) {
        runRepository.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Run run, @PathVariable Integer id) {
        runRepository.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        runRepository.delete(runRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @GetMapping("/filter/title-starts-with/{title}")
    public List<Run> findByTitleStartingWith(@PathVariable String title) {
        return runRepository.findRunsByTitleStartingWith(title);
    }

    @GetMapping("/filter/location/{location}")
    public List<Run> findRunsByLocation(@PathVariable String location) {
        return runRepository.findRunsByLocation(Location.valueOf(location));
    }

    @GetMapping("/filter/miles-indoors/{miles}")
    public List<Run> findByMilesIndoors(@PathVariable Integer miles) {
        return runRepository.findRunsByMilesIsAndLocationIs(miles, Location.INDOOR);
    }

    @GetMapping("/filter/miles-this-year/{miles}")
    public List<Run> findByMilesThisYear(@PathVariable Integer miles) {
        return runRepository.findRunsByStartedOnAfterAndMilesGreaterThan(LocalDateTime.of(2023,1,1,0,0,0), miles);
    }

    @GetMapping("/filter/miles-this-year-equal/{miles}")
    public List<Run> findByMilesThisYearEqual(@PathVariable Integer miles) {
        return runRepository.findRunsByStartedOnAfterAndMilesGreaterThanEqual(LocalDateTime.of(2023,1,1,0,0,0), miles);
    }

    @GetMapping("/filter/miles/{miles}")
    public List<Run> findByMiles(@PathVariable Integer miles) {
        return runRepository.listRunsWhereMilesEquals(miles);
    }

}
