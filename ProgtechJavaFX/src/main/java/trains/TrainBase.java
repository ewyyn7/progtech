package trains;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class TrainBase {
    public abstract int getId();
    public abstract String getModel();
    public abstract int GetAverageSpeed();
    public abstract int GetSafetyLevel();
    public abstract int GetNumberOfWagons();
    public abstract void saveToDatabase();
    public abstract void loadFromDatabase(int id);

    public IntegerProperty idProperty() {
        return new SimpleIntegerProperty(getId());
    }
    public StringProperty modelProperty() {
        return new SimpleStringProperty(getModel());
    }

    public IntegerProperty averageSpeedProperty() {
        return new SimpleIntegerProperty(GetAverageSpeed());
    }

    public IntegerProperty safetyLevelProperty() {
        return new SimpleIntegerProperty(GetSafetyLevel());
    }

    public IntegerProperty numberOfWagonsProperty() {
        return new SimpleIntegerProperty(GetNumberOfWagons());
    }
}
