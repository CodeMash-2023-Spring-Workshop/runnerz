package org.codemash.runnerz.service;

import org.codemash.runnerz.model.Location;
import org.codemash.runnerz.model.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RunService {

    private static final Logger log = LoggerFactory.getLogger(RunService.class);
    private final JdbcTemplate jdbcTemplate;

    public RunService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<Run> rowMapper = (rs, rowNum) -> new Run(rs.getInt("id"),
            rs.getString("title"),
            rs.getObject("started_on",LocalDateTime.class),
            rs.getObject("completed_on",LocalDateTime.class),
            rs.getInt("miles"),
            Location.valueOf(rs.getString("location")));

    public List<Run> findAll() {
        String sql = """
            SELECT id,title,started_on,completed_on,miles,location 
            from Run
        """;
        return jdbcTemplate.query(sql,rowMapper);
    }

    public Run findById(Integer id) {
        String sql = "SELECT id,title,started_on,completed_on,miles,location from Run where id = ?";
        return jdbcTemplate.queryForObject(sql,rowMapper,id);
    }

    public void create(Run run) {
        String sql = "INSERT INTO Run(title,started_on,completed_on,miles,location) VALUES(?,?,?,?,?)";
        int rows = jdbcTemplate.update(sql, run.title(), run.startedOn(), run.completedOn(), run.miles(), String.valueOf(run.location()));
        if(rows == 1) {
            log.info("Run was created successfully!");
        }
    }

    public void update(Run run, int id) {
        String sql = """
                UPDATE RUN
                SET title = ?,
                    started_on = ?,
                    completed_on = ?,
                    miles = ?,
                    location = ?
                WHERE id = ?
                """;
        int rows = jdbcTemplate.update(sql, run.title(), run.startedOn(), run.completedOn(), run.miles(), String.valueOf(run.location()), id);
        if(rows == 1) {
            log.info("Run was updated successfully!");
        }
    }

    public void delete(int id) {
        String sql = "DELETE from Run where id = ?";
        int rows = jdbcTemplate.update(sql, id);
        if(rows == 1) {
            log.info("Run was deleted successfully!");
        }
    }

}
