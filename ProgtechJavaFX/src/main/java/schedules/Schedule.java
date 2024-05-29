package schedules;

import trains.TrainDatabaseManager;

public class Schedule {
    private int id;
    private int trainId;
    private int lineId;
    private int time;

    public Schedule(){}

    public Schedule(int id, int trainId, int lineId, int time) {
        this.id = id;
        this.trainId = trainId;
        this.lineId = lineId;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void loadFromDatabase(int id) {
        String[] result = ScheduleDatabaseManager.loadFromDatabase(id);
        if (result != null) {
            this.id = id;
            this.trainId = Integer.parseInt(result[0]);
            this.lineId = Integer.parseInt(result[1]);
            this.time = Integer.parseInt(result[2]);
        }
    }

    public void saveToDatabase() {
        ScheduleDatabaseManager.saveToDatabase(trainId, lineId, time);
    }

    public void deleteFromDatabase() {
        ScheduleDatabaseManager.deleteSchedule(id);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", trainId=" + trainId +
                ", lineId=" + lineId +
                ", time=" + time +
                '}';
    }
}
