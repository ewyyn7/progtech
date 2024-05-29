package trains;

public class EquipDiningCar extends TrainDecoratorBase {
    public EquipDiningCar(TrainBase train) {
        super(train);
    }
    @Override
    public int getId() {
        return train.getId();
    }

    @Override
    public String getModel() {
        String superModel = super.getModel();
        String addon = " with dining car";
        return superModel + addon;
    }

    @Override
    public int GetAverageSpeed() {
        if (super.GetAverageSpeed() <= 20) {
            throw new NotValidSpeedException("Speed can't go under 0.");
        }
        return super.GetAverageSpeed() - 20;
    }

    @Override
    public int GetSafetyLevel() {
        return super.GetSafetyLevel() - 1;
    }

    @Override
    public int GetNumberOfWagons() {
        return super.GetNumberOfWagons() + 1;
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
