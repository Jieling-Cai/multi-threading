import java.util.HashMap;
import java.util.Map;


public class Train extends Entity {
  private Train(String name) { super(name); }
  static public Map<String, Train> trains = new HashMap<>();
  public int direction = 1;
  public volatile Station curr_station;
  public Station next_station;

  public static Train make(String name) {
    if(!trains.containsKey(name)) {
      trains.put(name,new Train(name));
    }
    return trains.get(name);
    // Change this method!
  }


}
