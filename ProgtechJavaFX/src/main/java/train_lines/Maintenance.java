package train_lines;

public class Maintenance implements IObserver{

   private String num;

   public Maintenance(String num){
       this.num=num;
   }

    @Override
    public void Update(double temperature, double condition_percentage, double rainfall_ammount, double seconds) {
        System.out.println("Karbantartók értesítve!");

    }
}
