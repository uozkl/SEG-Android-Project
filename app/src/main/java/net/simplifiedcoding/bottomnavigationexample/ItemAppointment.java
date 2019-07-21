package net.simplifiedcoding.bottomnavigationexample;

public class ItemAppointment implements Comparable<ItemAppointment> {
    //Appointment item with four attributes and getter setter function
    private long id;
    private String time;
    private String doctor;
    private String location;
    public ItemAppointment() {
        super();
    }


    public ItemAppointment(long id, String time, String doctor, String location) {
        super();
        this.id = id;
        this.time = time;
        this.doctor = doctor;
        this.location = location;
    }
    public long getId() {
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

    @Override
    public String toString(){
        return "{\"id\":\""+this.id+"\",\"time\":\""+this.time+"\",\"doctor\":\""+this.doctor+"\",\"location\":\""+this.location+"\"}";
    }

    @Override
    public int compareTo(ItemAppointment arg0) {
        // TODO Auto-generated method stub
        if(id > arg0.id){
            return 1;
        }
        if (id < arg0.id) {
            return -1;
        }
        return 0;
    }

}
