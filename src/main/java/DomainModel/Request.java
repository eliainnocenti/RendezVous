package main.java.DomainModel;

public class Request {

    // unique identifier
    private int user_id;
    private String timestamp;

    // request data
    private String description;

    // constructors
    public Request(int user_id, String timestamp, String description) {
        this.user_id = user_id;
        this.timestamp = timestamp;
        this.description = description;
    }

    // getters
    public int getUserId() { return user_id; }
    public String getTimestamp() { return timestamp; }
    public String getDescription() { return description; }

    // setters
    public void setUserId(int user_id) { this.user_id = user_id; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public void setDescription(String description) { this.description = description; }

}