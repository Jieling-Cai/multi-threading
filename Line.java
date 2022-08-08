import java.util.ArrayList;
import java.util.List;

public class Line {
    public Train train_name;
    public List<Station> station_name = new ArrayList<>();

    public void setTrain_name(String name,List<String> stations){
        this.train_name = Train.make(name);
        this.train_name.curr_station = Station.make(stations.get(0));
        this.train_name.next_station = Station.make(stations.get(1));
        this.train_name.direction = 1;
    }
    public void setStation_name(List<String> stations){
        for (String s:stations){
            this.station_name.add(Station.make(s));
        }
        Station.make(stations.get(0)).isEmpty_station = false;
    }
}
