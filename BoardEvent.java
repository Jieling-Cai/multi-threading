import java.util.*;

public class BoardEvent implements Event {
  //static { LogJson.registerEvent(BoardEvent.class, "Board"); }
  public final Passenger p; public final Train t; public final Station s;
  public BoardEvent(Passenger p, Train t, Station s) {
    this.p = p; this.t = t; this.s = s;
  }
  public boolean equals(Object o) {
    if (o instanceof BoardEvent e) {
      return p.equals(e.p) && t.equals(e.t) && s.equals(e.s);
    }
    return false;
  }
  public int hashCode() {
    return Objects.hash(p, t, s);
  }
  public String toString() {
    return "Passenger " + p + " boards " + t + " at " + s;
  }
  public List<String> toStringList() {
    return List.of(p.toString(), t.toString(), s.toString());
  }
  public void replayAndCheck(MBTA mbta) {
    for(Journey journey: mbta.all_journey){
      if(journey.passenger_name == p && p.curr_station == s && p.onTrain == null) {
        for(Line line: mbta.all_line){
          if(line.train_name == t && t.curr_station == s){
            if(journey.station_name.indexOf(s) < journey.station_name.size() - 1){
              p.curr_station = p.next_station;
              if(journey.station_name.indexOf(p.next_station) == journey.station_name.size() - 1){
                p.next_station = null;
              }
              else{
                p.next_station = journey.station_name.get(journey.station_name.indexOf(p.next_station)+1);
              }
              p.onTrain = t;
              return;
            }
          }
        }
      }
    }
    throw new UnsupportedOperationException();
  }
}
