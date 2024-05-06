package trains;

public abstract class TrainDecoratorBase extends TrainBase {
    protected TrainBase train;

    public TrainDecoratorBase(TrainBase train){
        this.train = train;
    }

    @Override
    public String getModel() {
        return train.getModel();
    }

    @Override
    public int GetAverageSpeed() {
        return train.GetAverageSpeed();
    }

    @Override
    public int GetSafetyLevel() {
        return train.GetSafetyLevel();
    }

    @Override
    public int GetNumberOfWagons() {
        return train.GetNumberOfWagons();
    }

    @Override
    public String toString() {
        return train.toString();
    }
}
