package org.codemash.runnerz.model;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class RunEntityTest {

    @Test
    void newRunShouldHaveNullId() {
        var startedOn = LocalDateTime.now();
        var completedOn =  LocalDateTime.now().plus(30, ChronoUnit.MINUTES);
        Run run = new Run(null, "Monday Afternoon Run", startedOn, completedOn, 3, Location.INDOOR);
        assertNull(run.id());
        assertEquals("Monday Afternoon Run",run.title());
        assertEquals(startedOn,run.startedOn());
        assertEquals(completedOn,run.completedOn());
        assertEquals(3,run.miles());
        assertEquals(Location.INDOOR,run.location());
        assertEquals(Duration.between(startedOn,completedOn),run.getDuration());
        assertEquals(10,run.getAvgPace());
    }

}