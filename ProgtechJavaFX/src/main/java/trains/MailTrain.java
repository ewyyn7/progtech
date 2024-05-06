package trains;

public class MailTrain extends TrainBase {
    protected int id;
    private String model = "Mail Train";
    private int averageSpeed = 80;
    private int safetyLevel = 6;
    private int numberOfWagons = 6;

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
        return "MailTrain{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", averageSpeed=" + averageSpeed +
                ", safetyLevel=" + safetyLevel +
                ", numberOfWagons=" + numberOfWagons +
                '}';
    }
}
