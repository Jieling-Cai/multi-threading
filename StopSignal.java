import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class StopSignal implements Runnable{
    public volatile boolean stopsignal = false;
    private MBTA mbta;
    private List<Passenger> deboard_passagers = new ArrayList<>();
    public StopSignal(MBTA mbta){
        this.mbta = mbta;
    }
    public int time_counter = 0;
    @Override
    public void run() {
        try {

            while (!stopsignal) {
                int passengers_finished_old = 0;
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time_counter++;
                if ((mbta.passengers_finished.size() == mbta.all_journey.size())||time_counter>=120) {
                    stopsignal = true;
                    System.out.println(" All Finish");
                    break;
                }
            }
        }
            catch(RuntimeException e){
                Thread.interrupted();
            }
    }
}
