package sky.akkaspring.model;

public class Message {
    private String payload;
    private Long id;

    public Message(String payload, Long id) {
        this.payload = payload;
        this.id = id;
    }

    public Message() {
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
