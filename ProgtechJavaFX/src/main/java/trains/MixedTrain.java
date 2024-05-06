package trains;

public class MixedTrain extends TrainBase {
    protected int id;
    private String model = "Mixed Train";
    private int averageSpeed = 90;
    private int safetyLevel = 3;
    private int numberOfWagons = 4;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int GetAverageSpeed() {
        return averageSpeed;
    }

    @Override
    public int GetSafetyLevel() {
        return safetyLevel;
    }

    @Override
    public int GetNumberOfWagons() {
        return numberOfWagons;
    }

    @Override
    public void saveToDatabase() {
        TrainDatabaseManager.saveToDatabase(model, averageSpeed, safetyLevel, numberOfWagons);
    }

    @Override
    public void loadFromDatabase(int id) {
        String[] result = TrainDatabaseManager.loadFromDatabase(id);
        if (result != null) {
            this.id = id;
            this.model = result[0];
            this.averageSpeed = Integer.parseInt(result[1]);
            this.safetyLevel = Integer.parseInt(result[2]);
            this.numberOfWagons = Integer.parseInt(result[3]);
        }
    }

    @Override
    public String toString() {
        return "MixedTrain{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", averageSpeed=" + averageSpeed +
                ", safetyLevel=" + safetyLevel +
                ", numberOfWagons=" + numberOfWagons +
                '}';
    }
}
