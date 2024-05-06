package trains;

import java.sql.Connection;

public class EquipSnowplough extends TrainDecoratorBase {
    public EquipSnowplough(TrainBase train) {
        super(train);
    }
    @Override
    public int getId() {
        return train.getId();
    }

    @Override
    public String getModel() {
        String superModel = super.getModel();
        String addon = " with snowplough";
        return superModel + addon;
    }

    @Override
    public int GetAverageSpeed() {
        return super.GetAverageSpeed() + 20;
    }

    @Override
    public int GetSafetyLevel() {
        return super.GetSafetyLevel() + 2;
    }

    @Override
    public void saveToDatabase() {
        TrainDatabaseManager.updateToDatabase(getId(), getModel(), this.GetAverageSpeed(),
                this.GetSafetyLevel(), this.GetNumberOfWagons());
    }

    @Override
    public void loadFromDatabase(int id) {
        super.train.loadFromDatabase(id);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
