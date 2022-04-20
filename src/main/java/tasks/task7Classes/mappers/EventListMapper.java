package tasks.task7Classes.mappers;

import org.springframework.jdbc.core.RowMapper;
import tasks.task7Classes.EventList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventListMapper implements RowMapper<EventList> {
    @Override
    public EventList mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new EventList(rs.getString("name"));
    }
}
