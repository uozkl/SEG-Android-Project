package net.simplifiedcoding.bottomnavigationexample;

public class ItemAppointment {
    private int id;
    private String time;
    private String doctor;
    private String location;
    public ItemAppointment() {
        super();
    }


    public ItemAppointment(int id, String time, String doctor, String location) {
        super();
        this.id = id;
        this.time = time;
        this.doctor = doctor;
        this.location = location;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
