import java.util.*;

public class DeboardEvent implements Event {
  public final Passenger p; public final Train t; public final Station s;
  public DeboardEvent(Passenger p, Train t, Station s) {
    this.p = p; this.t = t; this.s = s;
  }
  public boolean equals(Object o) {
    if (o instanceof DeboardEvent e) {
      return p.equals(e.p) && t.equals(e.t) && s.equals(e.s);
    }
    return false;
  }
  public int hashCode() {
    return Objects.hash(p, t, s);
  }
  public String toString() {
    return "Passenger " + p + " deboards " + t + " at " + s;
  }
  public List<String> toStringList() {
    return List.of(p.toString(), t.toString(), s.toString());
  }
  public void replayAndCheck(MBTA mbta) {
    for(Journey journey: mbta.all_journey){
      if(journey.passenger_name == p && p.onTrain == t && p.curr_station == s){
        for(Line line: mbta.all_line){
          if(line.train_name == t && t.curr_station == s){
            p.onTrain = null;
            return;
          }
        }
      }
    }
    throw new UnsupportedOperationException();
  }
}
