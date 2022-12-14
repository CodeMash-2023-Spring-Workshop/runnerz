package org.codemash.runnerz.controller;

import org.codemash.runnerz.model.Run;
import org.codemash.runnerz.service.RunService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunService runService;

    public RunController(RunService runService) {
        this.runService = runService;
    }

    @GetMapping
    public List<Run> findAll() {
        return runService.findAll();
    }

    @GetMapping("/{id}")
    public Run findById(@PathVariable Integer id) {
        return runService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Run create(@RequestBody Run run) {
        return runService.create(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Run run, @PathVariable Integer id) {
        runService.update(run,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        runService.delete(id);
    }

    @GetMapping("/filter/{title}")
    public List<Run> filterByTitleStartsWith(@PathVariable String title) {
        return findAll().stream().filter(run -> run.title().startsWith(title)).collect(Collectors.toList());
    }
}
