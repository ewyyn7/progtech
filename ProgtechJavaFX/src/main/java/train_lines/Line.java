package train_lines;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.jar.Manifest;

public class Line implements ISubject, IDisplay{


    public IntegerProperty idProperty() {
        return new SimpleIntegerProperty(getId());
    }
    public IntegerProperty lengthProperty() {
        return new SimpleIntegerProperty(getLength());
    }
    public IntegerProperty baseProperty() {
        return new SimpleIntegerProperty(getBase_station_id());
    }
    public IntegerProperty finalProperty() {
        return new SimpleIntegerProperty(getFinal_station_id());
    }

    private List<IObserver> monitors = new ArrayList<>();
    private List<IObserver> maintenances = new ArrayList<>();


    private Random random=new Random();

    private int length;
    private int id;
    private int base_station_id;
    private int final_station_id;
    public Line(int id, int length,int base_station_id, int final_station_id){
        this.length=length;
        this.id=id;
        this.base_station_id=base_station_id;
        this.final_station_id=final_station_id;
    }
    public int getId() {
        return id;
    }
    public int getLength() {
        return length;
    }
    public int getBase_station_id() {
        return base_station_id;
    }
    public int getFinal_station_id() {
        return final_station_id;
    }



    public String Check(){
        double temperature= random.nextDouble(-20,40);

        double rainfall_ammount=random.nextDouble(10);




        for(IObserver monitor:monitors){
            return monitor.Update(temperature,rainfall_ammount);
        }

        if (temperature>35||temperature<2||rainfall_ammount>3){
            System.out.println ("observer Maintenance alert.");
            return NotifyObservers();

        }
        return "Temperature (C) : " + temperature + " Rainfall (mm) :" + rainfall_ammount;

    }

    @Override
    public void Display(double temperature ,double rainfall_ammount) {
        System.out.println("Hőfok (C)" + temperature +" Csapadék (mm)" + rainfall_ammount );
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
    public String NotifyObservers() {

        for (IObserver maintenance:maintenances){
            return maintenance.Update(0,0);
        }
        return "Maintenance needed!";


    }
}
