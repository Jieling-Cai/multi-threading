import java.util.ArrayList;
import java.util.List;

public class Journey {
    public Passenger passenger_name;
    public List<Station> station_name = new ArrayList<>();

    public void setPassenger_name(String name){
        this.passenger_name = Passenger.make(name);
    }
    public void setStation_name(List<String> stations){
        for (String s:stations){
            this.station_name.add(Station.make(s));
        }
        this.passenger_name.curr_station = Station.make(stations.get(0));
        this.passenger_name.next_station = Station.make(stations.get(1));
        this.passenger_name.onTrain = null;
    }
}
