import java.awt.*;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class TrainThread implements Runnable {
    private Log log;
    private Train t;
    private MBTA mbta;
    private StopSignal stop;

    public TrainThread(Log log, MBTA mbta, Train train, StopSignal stop) {
        this.log = log;
        this.mbta = mbta;
        this.t = train;
        this.stop = stop;
    }

    public void run() {
        try {

            while (true) {

                Station s1 = t.next_station;
                synchronized (s1) {
                    if (t.next_station.isEmpty_station == false) {
                        try {
                            t.next_station.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().stop();
                            return;
                        }
                    }
                    //log.train_moves(t, t.curr_station, t.next_station);
                    t.next_station.isEmpty_station = false;
                    //t.curr_station = t.next_station;
                    t.curr_station.isEmpty_station = true;
                    Station s = t.curr_station;
                    synchronized (s) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            currentThread().stop();
                        }
                        log.train_moves(t, t.curr_station, t.next_station);
                        t.curr_station.notify();
                        t.curr_station = t.next_station;
                    }
                    for (Line line : mbta.all_line) {
                        if (line.train_name == t) {
                            if (line.station_name.indexOf(t.curr_station) == line.station_name.size() - 1)
                                t.direction = -1;
                            if (line.station_name.indexOf(t.curr_station) == 0) t.direction = 1;
                            t.next_station = line.station_name.get(line.station_name.indexOf(t.curr_station) + t.direction * 1);
                        }
                    }
                }
                if (stop.stopsignal) break;

            }
        }catch (RuntimeException e){
            Thread.interrupted();
        }
            //Thread.interrupted();

    }
}

