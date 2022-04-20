package tasks.task7Classes;

public class Event {
    private transient int id;
    private transient String listName;
    private String name;


    public Event(String listName, String name) {
        this.listName = listName;
        this.name = name;
    };

    public Event() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getListId() {
        return listName;
    }

    public void setListId(String listId) {
        this.listName = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
