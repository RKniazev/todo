package ru.rkniazev.todo;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private static final Store INST = new Store();

    private final List<Plan> items = new ArrayList<>();

    private Store(){

    }

    public static Store getStore() {
        return INST;
    }

    public void add(Plan plan){
        this.items.add(plan);
    }

    public List<Plan> getAll(){
        return this.items;
    }

    public int getSize(){
        return this.items.size();
    }

    public Plan get(int index){
        return this.items.get(index);
    }

    public void update(int index, String name, String disc){
        this.items.get(index).setName(name);
        this.items.get(index).setDisc(disc);
    }
}
