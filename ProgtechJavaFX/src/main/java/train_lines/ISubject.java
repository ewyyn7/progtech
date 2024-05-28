package train_lines;

public interface ISubject {
    void RegisterObserver(IObserver observer);

    void RemoveObserver(IObserver observer);

    String NotifyObservers();

}
