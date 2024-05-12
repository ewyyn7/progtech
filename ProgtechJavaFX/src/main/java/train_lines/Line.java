package train_lines;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.jar.Manifest;

public class Line implements ISubject, IDisplay{

    private List<IObserver> monitors = new ArrayList<>();
    private List<IObserver> maintenances = new ArrayList<>();
    private String line_number;

    private Random random=new Random();

    public Line(String line_number){
        this.line_number=line_number;
    }

    public void Check(){
        double temperature= random.nextDouble(15);
        double condition_percentage=random.nextDouble(100);
        double rainfall_ammount=random.nextDouble(10);
        double seconds=random.nextDouble(10);

        Display(temperature,condition_percentage,rainfall_ammount, seconds);

        for(IObserver monitor:monitors){
            monitor.Update(temperature,condition_percentage,rainfall_ammount,seconds);
        }

        if (temperature<5||condition_percentage<50||rainfall_ammount>5){
            NotifyObservers();
        }

    }

    @Override
    public void Display(double temperature, double condition_percentage, double rainfall_ammount, double seconds) {
        System.out.println("Hőfok (C)" + temperature + "Állapot (%)" + condition_percentage + " Csapadék (mm)" + rainfall_ammount + "Menetrendi menetidő (mp) "+ seconds);
    }

    @Override
    public void RegisterObserver(IObserver observer) {
        if(observer instanceof Monitoring){
            monitors.add(observer);
        }
        else if(observer instanceof Maintenance){
            maintenances.add(observer);
        }
    }

    @Override
    public void RemoveObserver(IObserver observer) {
        monitors.remove(observer);
        maintenances.remove(observer);
    }

    @Override
    public void NotifyObservers() {
        System.out.println("!");
        for (IObserver maintenance:maintenances){
            maintenance.Update(0,0,0,0);
        }


    }
}
