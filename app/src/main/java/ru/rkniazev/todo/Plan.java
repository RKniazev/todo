package ru.rkniazev.todo;

import java.util.Calendar;
import java.util.Objects;

public class Plan {
    private String name;
    private String disc;
    private Calendar created;
    private Calendar finish;
    private boolean done;


    public Plan(String name, String disc){
        this.name = name;
        this.disc = disc;
        this.created = Calendar.getInstance();
        this.done = false;
    }

    public String getName() {
        return name;
    }

    public String getDiscription() {
        return disc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
        if (done == true){
            this.finish = Calendar.getInstance();
        }
        else {
            this.finish = null;
        }
    }

    public Calendar getFinish() {
        return finish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return Objects.equals(name, plan.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
