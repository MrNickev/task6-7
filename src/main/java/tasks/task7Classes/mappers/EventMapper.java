package tasks.task7Classes.mappers;

import org.springframework.jdbc.core.RowMapper;
import tasks.task7Classes.Event;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Event(rs.getString("list_name"), rs.getString("name"));
    }
}
