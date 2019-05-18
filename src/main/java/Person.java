import java.awt.*;
import java.io.Serializable;

public class Person implements Serializable {

    private String name;
    private String message;
    private Color color;

    public Person(String _name, String _message) {
        this.name = _name;
        this.message = _message;
    }

    public String toString() {
        return this.name + ": " + this.message;
    }
}
