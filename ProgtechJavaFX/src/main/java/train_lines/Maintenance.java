package train_lines;

public class Maintenance implements IObserver{

   private String num;

   public Maintenance(String num){
       this.num=num;
   }

    @Override
    public String Update(double temperature, double rainfall_ammount) {
        return ("Maintenance needed!");

    }
}
