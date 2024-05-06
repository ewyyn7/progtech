package trains;

public abstract class TrainBase {
    public abstract int getId();
    public abstract String getModel();
    public abstract int GetAverageSpeed();
    public abstract int GetSafetyLevel();
    public abstract int GetNumberOfWagons();
    public abstract void saveToDatabase();
    public abstract void loadFromDatabase(int id);
}
