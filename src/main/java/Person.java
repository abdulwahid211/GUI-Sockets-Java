import java.io.Serializable;

public class Person implements Serializable {

    private String name;
    private String message;

    public Person(String _name){
        this.name = _name;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
