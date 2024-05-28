package train_lines;

public class Monitoring implements IObserver{

    private String num;


    public Monitoring(String num){this.num=num;}

    @Override
    public String Update(double temperature,double rainfall_ammount) {
        if (temperature < 30 || rainfall_ammount < 3){
            return ("Alert, but no service needed! " + "Temperature (C) : " + temperature + " Rainfall (mm) :" + rainfall_ammount);
        }
        return "Temperature (C) : " + temperature + " Rainfall (mm) :" + rainfall_ammount;

    }



}
