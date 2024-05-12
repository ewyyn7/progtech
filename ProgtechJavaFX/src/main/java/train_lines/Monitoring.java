package train_lines;

public class Monitoring implements IObserver{

    private String num;


    public Monitoring(String num){this.num=num;}

    @Override
    public void Update(double temperature, double condition_percentage, double rainfall_ammount, double seconds) {
        if (temperature < 10 || condition_percentage < 80 || rainfall_ammount > 2){
            System.out.println("A várható menetidő " + seconds*2 + " !");
        }

    }



}
