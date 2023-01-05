package org.codemash.runnerz.model;

import org.springframework.data.annotation.Id;

import java.time.Duration;
import java.time.LocalDateTime;

public record Run(
        @Id
        Integer id,
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        Integer miles,
        Location location
) {

    public Duration getDuration() {
        return Duration.between(startedOn,completedOn);
    }

    public Integer getAvgPace() {
        return Math.toIntExact(getDuration().toMinutes() / miles);
    }
}
