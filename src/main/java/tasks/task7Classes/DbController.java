package tasks.task7Classes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import tasks.task7Classes.mappers.EventListMapper;
import tasks.task7Classes.mappers.EventMapper;

import java.sql.SQLException;
import java.util.List;

@Service
public class DbController {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DbController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getAllPeopleFromDbInJson() throws SQLException {
        var jsonArr = new JsonArray();
        var lists = jdbcTemplate.query("SELECT * FROM event_list", new EventListMapper());
        for (var list : lists) {
            var events = jdbcTemplate.query("SELECT * FROM event WHERE list_name=?", new Object[]{list.getName()}, new EventMapper());
            var item = new JsonObject();
            var gson = new Gson();
            item.addProperty("name", list.getName());
            item.addProperty("events", new Gson().toJson(getEventNames(events)));
            jsonArr.add(item);
        }
        return jsonArr.toString();
    }

    private String[] getEventNames(List<Event> events) {
        var names = new String[events.size()];
        for (var i =0; i < events.size(); i++) {
            names[i] = events.get(i).getName();
        }
        return names;
    }

    public void saveEventLIst(EventList eventList) {
        jdbcTemplate.update("INSERT INTO event_list(name) VALUES(?)", eventList.getName());
    }

    public void saveEvent(Event event) {
        jdbcTemplate.update("INSERT INTO event(name, list_name) VALUES(?, ?)", event.getName(), event.getListId());
    }

}
