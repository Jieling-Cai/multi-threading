import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Test1 {

    @Test
    public void testTrainThread2(){
        MBTA mbta = new MBTA();
        mbta.loadConfig("sample2.json");
        Log log = new Log();
        StopSignal stop = new StopSignal(mbta);
        //TrainThread trainThread = new TrainThread(log,mbta,Train.make("red"));
        Thread t1 = new Thread(new TrainThread(log,mbta,Train.make("orange"),stop));
        Thread t2 = new Thread(new TrainThread(log,mbta,Train.make("red"),stop));
        //Thread t3 = new Thread(new TrainThread(log,mbta,Train.make("blue"),stop));
        //Thread t4 = new Thread(new TrainThread(log,mbta,Train.make("green"),stop));
        Thread t5 = new Thread(new PassengerThread(log,mbta,Passenger.make("Bob"),stop));
        //Thread t6 = new Thread(new PassengerThread(log,mbta,Passenger.make("Alice"),stop));
        //Thread t7 = new Thread(new PassengerThread(log,mbta,Passenger.make("Carol"),stop));
        Thread t8 = new Thread(stop);
        List<Thread> tt = new ArrayList<>();

        tt.add(t1);
        tt.add(t2);
        //tt.add(t3);
        //tt.add(t4);
        tt.add(t5);
        //tt.add(t6);
        //tt.add(t7);
        tt.add(t8);
        //Thread t2 = new Thread(trainThread);
        for(Thread t: tt){
            t.start();
        }
        while(true){
            if(stop.stopsignal == true){
                for(Thread t:tt){
                    t.stop();
                }
                break;
            }
            else{
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        mbta.reset();
        mbta.loadConfig("sample2.json");
        Verify.verify(mbta, log);

    }
}
