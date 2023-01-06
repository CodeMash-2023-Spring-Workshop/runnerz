package org.codemash.runnerz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.codemash.runnerz.model.Location;
import org.codemash.runnerz.model.Run;
import org.codemash.runnerz.repository.RunRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RunController.class)
class RunControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    RunRepository repository;

    @Test
    void shouldFindAllRuns() throws Exception {
        mvc.perform(get("/api/runs"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFindOneRun() throws Exception {
        mvc.perform(get("/api/runs/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateNewRun() throws Exception {
        var run = new Run(null,"test", LocalDateTime.now(),LocalDateTime.now(),1, Location.INDOOR);
        mvc.perform(post("/api/runs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(run))
                )
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateRun() throws Exception {
        var run = new Run(null,"test", LocalDateTime.now(),LocalDateTime.now(),1, Location.INDOOR);
        mvc.perform(put("/api/runs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(run))
                )
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteRun() throws Exception {
        mvc.perform(delete("/api/runs/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFilterByTitleStarsWith() throws  Exception {
        mvc.perform(get("/api/runs/filter/title-starts-with/Monday"))
                .andExpect(status().isOk());
    }

}