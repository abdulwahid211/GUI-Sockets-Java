import java.awt.*;
import java.io.Serializable;

public class Message implements Serializable {

    private String name;
    private String message;
    private String toWho;

    public Message(String _name, String _message, String _toWho) {
        this.name = _name;
        this.message = _message;
        this.toWho = _toWho;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getToWho() {
        return toWho;
    }

    public void setToWho(String toWho) {
        this.toWho = toWho;
    }

    public String toString() {
        return this.name + ": " + this.message;
    }
}
