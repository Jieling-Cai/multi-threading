import java.util.*;

public class Passenger extends Entity {
  private Passenger(String name) { super(name); }
  public static Map<String, Passenger> passengers = new HashMap<>();
  public Station curr_station;
  public Station next_station;
  public Train onTrain = null;

  public static Passenger make(String name) {
    if(!passengers.containsKey(name)){
      passengers.put(name, new Passenger(name));
    }
    // Change this method!
    return passengers.get(name);
  }
}
