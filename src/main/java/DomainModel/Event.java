package main.java.DomainModel;

public class Event {

    // event data
    private int code; // TODO - implement code generation (the code is a unique identifier for the event)
    private String name, description, location, date, time;
    private int maxParticipants;
    private boolean refundable;
    private float fee;

    // constructors
    public Event(String name, String description, String location, String date, String time, int maxParticipants, boolean refundable, float fee) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
        this.maxParticipants = maxParticipants;
        this.refundable = refundable;
        this.fee = fee;
    }
    public Event(String name, String description, String location, String date, String time, int maxParticipants, float fee) {
        this(name, description, location, date, time, maxParticipants, false, fee);
    }

    // getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public int getCode() { return code; }
    public int getMaxParticipants() { return maxParticipants; }
    public boolean isRefundable() { return refundable; }
    public float getFee() { return fee; }

    public String getEventData() {
        return "Event: " + name + "\nDescription: " + description + "\nLocation: " + location + "\nDate: " + date + "\nTime: " + time + "\nMax participants: " + maxParticipants + "\nRefundable: " + refundable + "\nFee: " + fee;
    }

}