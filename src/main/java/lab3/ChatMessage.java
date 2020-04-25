package lab3;

public class ChatMessage {
    private String from;
    private String to;
    private String content;

    public String getSender() {
        return from;
    }

    public void setSender(String from) {
        this.from = from;
    }

    public String getReceiver() {
        return to;
    }

    public void setReceiver(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}