package tasks.task7Classes;

import com.google.gson.JsonElement;

public class EventList {
    private String name;

    public EventList() {

    }

    public EventList(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
