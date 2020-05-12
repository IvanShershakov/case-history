package ru.mirea.casehistory;

public class Model {
    String id,  name, illneses, addTimeStamp, updateTimeStamp;

    public Model(String id, String name, String illneses, String addTimeStamp, String updateTimeStamp) {
        this.id = id;
        this.name = name;
        this.illneses = illneses;
        this.addTimeStamp = addTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIllneses() {
        return illneses;
    }

    public String getAddTimeStamp() {
        return addTimeStamp;
    }

    public String getUpdateTimeStamp() {
        return updateTimeStamp;
    }
}
