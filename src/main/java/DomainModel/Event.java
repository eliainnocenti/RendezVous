package main.java.DomainModel;

public class Event {

    // event data
    private int code;
    private String name, description, location, date, time;
    private boolean refundable;
    private float fee;
    private int createdBy;

    // constructors
    public Event(String name, String description, String location, String date, String time, boolean refundable, float fee, int createdBy) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
        this.refundable = refundable;
        this.fee = fee;
        this.createdBy = createdBy;
    }
    public Event(String name, String description, String location, String date, String time, float fee, int createdBy) {
        this(name, description, location, date, time, false, fee, createdBy);
    }

    // getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public int getCode() { return code; }
    public boolean isRefundable() { return refundable; }
    public float getFee() { return fee; }
    public int getCreatedBy() { return createdBy; }

    public String getEventData() {
        return "Event: " + name + "\nDescription: " + description + "\nLocation: " + location + "\nDate: " + date + "\nTime: " + time + "\nRefundable: " + refundable + "\nFee: " + fee;
    }

    // setters
    public void setCode(int code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setLocation(String location) { this.location = location; }
    public void setDate(String date) { this.date = date; }
    public void setTime(String time) { this.time = time; }
    public void setRefundable(boolean refundable) { this.refundable = refundable; }
    public void setFee(float fee) { this.fee = fee; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }

}