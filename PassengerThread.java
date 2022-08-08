import java.util.List;

import static java.lang.Thread.sleep;

public class PassengerThread implements Runnable{
    private Log log;
    private Passenger p;
    private MBTA mbta;
    private Train t;
    private List<Station> s;
    private StopSignal stop;

    public PassengerThread(Log log, MBTA mbta, Passenger p, StopSignal stop) {
        this.log = log;
        this.mbta = mbta;
        this.p = p;
        this.stop = stop;
        for(Journey journey: mbta.all_journey){
            if(journey.passenger_name == p) {
                s = journey.station_name;
                break;
            }
        }
    }

    public void run() {
        try {
            while (true) {
                if (p.onTrain == null) {
                    if (p.next_station != null) {
                        for (Line line : mbta.all_line) {
                            if (line.station_name.contains(p.next_station) && line.station_name.contains(p.curr_station)) {
                                t = line.train_name;
                                break;
                            }
                        }
                    }
                }
                if (t.curr_station == p.curr_station) {
                    if (p.onTrain == null) {
                        log.passenger_boards(p, t, p.curr_station);
                        p.curr_station = p.next_station;
                        if (s.indexOf(p.next_station) == s.size() - 1) {
                            p.next_station = null;
                        } else {
                            p.next_station = s.get(s.indexOf(p.next_station) + 1);
                        }

                        p.onTrain = t;
                    } else if (t.curr_station == p.curr_station && p.onTrain == t) {
                        log.passenger_deboards(p, t, p.curr_station);
                        p.onTrain = null;
                        if (s.indexOf(p.curr_station) == s.size() - 1) {
                            p.curr_station = null;
                            mbta.passengers_finished.add(p);
                            break;
                        }

                    }
                }

            }
        }
        catch (RuntimeException e){
            Thread.interrupted();
        }}

}
