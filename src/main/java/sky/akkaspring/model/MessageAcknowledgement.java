package sky.akkaspring.model;

public class MessageAcknowledgement {
    private String payload;
    private Long id;
    private String acknowledgement;

    public MessageAcknowledgement(String payload, Long id, String acknowledgement) {
        this.payload = payload;
        this.id = id;
        this.acknowledgement = acknowledgement;
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

    public String getAcknowledgement() {
        return acknowledgement;
    }

    public void setAcknowledgement(String acknowledgement) {
        this.acknowledgement = acknowledgement;
    }
}
