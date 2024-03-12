package main.java.DomainModel;

public class Request {

    // unique identifier
    private int code;

    // request data
    private int user_id;
    private String created_at;
    private String description;

    // constructors
    public Request(int code, int user_id, String created_at, String description) {
        this.code = code;
        this.user_id = user_id;
        this.created_at = created_at;
        this.description = description;
    }
    public Request(int user_id, String created_at, String description) {
        this.user_id = user_id;
        this.created_at = created_at;
        this.description = description;
    }

    // getters
    public int getCode() { return code; }
    public int getUserId() { return user_id; }
    public String getCreated_at() { return created_at; }
    public String getDescription() { return description; }

    // setters
    public void setCode(int code) { this.code = code; }
    public void setUserId(int user_id) { this.user_id = user_id; }
    public void setCreated_at(String created_at) { this.created_at = created_at; }
    public void setDescription(String description) { this.description = description; }

}