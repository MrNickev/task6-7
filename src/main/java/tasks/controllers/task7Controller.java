package tasks.controllers;

import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tasks.task7Classes.DbController;
import tasks.task7Classes.Event;
import tasks.task7Classes.EventList;

import java.sql.SQLException;

@Controller
public class task7Controller {

    private final DbController dbController;

    @Autowired
    public task7Controller(DbController dbController) {
        this.dbController = dbController;
    }

    @PostMapping("/add-events")
    public ResponseEntity<String> addEventsToDb(@RequestBody String gotString) {
        try {
            var json = new JsonParser().parse(gotString).getAsJsonObject();
            var eventList = new EventList(json.get("name").toString());
            dbController.saveEventLIst(eventList);
            var events = json.get("events").getAsJsonArray();
            for (var event : events) {
                dbController.saveEvent(new Event(eventList.getName(), event.toString()));
            }

        }
        catch (DuplicateKeyException e) {
            return new ResponseEntity<>("Such list was already exist", HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Incorrect data", HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>("Request get", HttpStatus.OK);
    }

    @GetMapping("/get-all-events")
    public ResponseEntity<String> getAllEvents() {
        var result = "";
        try {
            result = dbController.getAllPeopleFromDbInJson();
        } catch (SQLException e) {
            return new ResponseEntity<>("Can't create JSON", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
