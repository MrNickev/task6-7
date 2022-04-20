package tasks.task6Classes;

import java.util.Random;

public class Info {
    String date;
    int id;

    public Info(String date) {
        this.date = date;
    }

    public void createId() {
        var rnd = new Random();
        id = rnd.nextInt(1000);
    }
}
