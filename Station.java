import java.util.*;

public class Station extends Entity {
  private Station(String name) { super(name); }
  public static Map<String,Station> stations = new HashMap<>();
  public boolean isEmpty_station = true;

  public static Station make(String name) {
    if(!stations.containsKey(name)){
      stations.put(name,new Station(name));
    }
    // Change this method!
    return stations.get(name);
  }

}
