import java.util.*;

public class MoveEvent implements Event {
  //static { LogJson.registerEvent(MoveEvent.class, "Move"); }
  public final Train t; public final Station s1, s2;
  public MoveEvent(Train t, Station s1, Station s2) {
    this.t = t; this.s1 = s1; this.s2 = s2;
  }
  public boolean equals(Object o) {
    if (o instanceof MoveEvent e) {
      return t.equals(e.t) && s1.equals(e.s1) && s2.equals(e.s2);
    }
    return false;
  }
  public int hashCode() {
    return Objects.hash(t, s1, s2);
  }
  public String toString() {return "Train " + t + " moves from " + s1 + " to " + s2;}
  public List<String> toStringList() {
    return List.of(t.toString(), s1.toString(), s2.toString());
  }
  public void replayAndCheck(MBTA mbta) {
    for(Line line: mbta.all_line){
      if(line.train_name == t ) {
        if (t.curr_station == s1 && t.next_station == s2 && s2.isEmpty_station) {
          t.curr_station = s2;
          s1.isEmpty_station = true;
          s2.isEmpty_station = false;
          if (line.station_name.indexOf(s2) == line.station_name.size() - 1) t.direction = -1;
          if (line.station_name.indexOf(s2) == 0) t.direction = 1;
          t.next_station = line.station_name.get(line.station_name.indexOf(s2) + t.direction * 1);
          return;
        }
      }

    }
    throw new UnsupportedOperationException();
  }
}
