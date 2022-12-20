package org.codemash.runnerz.controller;

import org.codemash.runnerz.model.Run;
import org.codemash.runnerz.service.RunService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RunController {

    private final RunService runService;

    public RunController(RunService runService) {
        this.runService = runService;
    }

    public List<Run> findAll() {
        return runService.findAll();
    }

}
