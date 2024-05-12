package train_lines;

public interface IObserver {

    void Update(double temperature, double condition_percentage, double rainfall_ammount, double seconds);

}
