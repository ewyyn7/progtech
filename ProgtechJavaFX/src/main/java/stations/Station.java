package stations;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Station {

    public IntegerProperty idProperty() {
        return new SimpleIntegerProperty(getId());
    }
    public StringProperty nameProperty() {
        return new SimpleStringProperty(getName());
    }

    private int id;
    private String name;


    public Station(int id, String name) {
        this.id=id;
        this.name = name;
    }
    public Station(String name) {
        this.name = name;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
