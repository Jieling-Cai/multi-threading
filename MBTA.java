import com.google.gson.Gson;

import java.util.*;

public class MBTA {

  // Creates an initially empty simulation
  public MBTA() {
    Train.trains.clear();
    Passenger.passengers.clear();
    Station.stations.clear();
  }
  public Set <Line> all_line = new HashSet<>();
  public List<Passenger> passengers_finished = new ArrayList<>();
  public Set <Journey> all_journey = new HashSet<>();


  // Adds a new transit line with given name and stations
  public void addLine(String name, List<String> stations) {
    Line line = new Line();
    line.setTrain_name(name,stations);
    line.setStation_name(stations);
    all_line.add(line);
  }

  // Adds a new planned journey to the simulation
  public void addJourney(String name, List<String> stations) {
    Journey journey = new Journey();
    journey.setPassenger_name(name);
    journey.setStation_name(stations);
    all_journey.add(journey);
  }

  // Return normally if initial simulation conditions are satisfied, otherwise
  // raises an exception
  public void checkStart() {
    for(Line line: all_line){
      if(line.train_name.curr_station != line.station_name.get(0)) throw new UnsupportedOperationException();
      if(line.train_name.direction != 1) throw new UnsupportedOperationException();
      if(line.station_name.get(0).isEmpty_station == true) throw new UnsupportedOperationException();
    }
    for(Journey journey: all_journey){
      if(journey.passenger_name.curr_station != journey.station_name.get(0)) {
        throw new UnsupportedOperationException();}
      if(journey.passenger_name.next_station != journey.station_name.get(1)) throw new UnsupportedOperationException();
      if(journey.passenger_name.onTrain != null) throw new UnsupportedOperationException();
    }
  }

  // Return normally if final simulation conditions are satisfied, otherwise
  // raises an exception
  public void checkEnd() {
    for(Journey journey: all_journey){
      if(journey.passenger_name.curr_station != journey.station_name.get(journey.station_name.size()-1)) throw new UnsupportedOperationException();
      if(journey.passenger_name.next_station != null) throw new UnsupportedOperationException();
      if(journey.passenger_name.onTrain != null) throw new UnsupportedOperationException();
    }
  }

  // reset to an empty simulation
  public void reset() {
    for(Line line: all_line){
      line.train_name.direction = 1;
      line.train_name.curr_station = null;
      line.train_name.next_station = null;
      for(Station s: line.station_name){
        s.isEmpty_station = true;
      }
    }
    for(Journey journey: all_journey){
      journey.passenger_name.onTrain = null;
      journey.passenger_name.next_station = null;
      journey.passenger_name.curr_station = null;

    }
    all_line.clear();
    all_journey.clear();
    passengers_finished.clear();
  }

  // adds simulation configuration from a file
  public void loadConfig(String filename) {
    Gson gson = new Gson();
    String s = Jsonclass.readJson(filename);
    Jsonclass c = gson.fromJson(s, Jsonclass.class);
    for(String name:c.lines.keySet()){
      this.addLine(name,c.lines.get(name));

    }
    for(String name:c.trips.keySet()){
      this.addJourney(name,c.trips.get(name));

    }
  }
}
