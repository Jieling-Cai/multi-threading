import java.io.*;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Sim {

  public static void run_sim(MBTA mbta, Log log) {

    StopSignal stop = new StopSignal(mbta);
    List<Thread> trainthread = new ArrayList<>();
    List<Thread> passengerthread = new ArrayList<>();
    for(Line line: mbta.all_line){
      trainthread.add(new Thread(new TrainThread(log,mbta,line.train_name,stop)));
    }
    for(Journey journey: mbta.all_journey){
      passengerthread.add(new Thread(new PassengerThread(log,mbta,journey.passenger_name,stop)));
    }
    for(Thread t1:trainthread){
      t1.start();
    }
    for(Thread t2:passengerthread){
      t2.start();
    }
    Thread t3 = new Thread(stop);
    t3.start();
    try {
      while (true) {
        if (stop.stopsignal == true) {
          for (Thread t1 : trainthread) {
            t1.interrupt();
          }
          for (Thread t2 : passengerthread) {
            t2.interrupt();
          }
          t3.interrupt();
          break;
        } else {
          try {
            sleep(5000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }}

      }
    catch(Exception e1){
        for (Thread t1 : trainthread) {
          t1.stop();
        }
        for (Thread t2 : passengerthread) {
          t2.stop();
        }
        t3.stop();
        throw new RuntimeException(e1);
      }


  }

  public static void main(String[] args) throws Exception {

      if (args.length != 1) {
        System.out.println("usage: ./sim <config file>");
        System.exit(1);
      }

      MBTA mbta = new MBTA();
      mbta.loadConfig(args[0]);

      Log log = new Log();
      run_sim(mbta, log);

      String s = new LogJson(log).toJson();
      PrintWriter out = new PrintWriter("log.json");
      out.print(s);
      out.close();
      mbta.reset();
      mbta.loadConfig(args[0]);
      Verify.verify(mbta, log);


  }
}
