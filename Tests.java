import static org.junit.Assert.*;

import org.junit.*;

import java.util.Arrays;
import java.util.List;

public class Tests {
  @Test public void testPass() {
    assertTrue("true should be true", true);
  }
  @Test
  public void testVerify(){
    MBTA mbta = new MBTA ();
    Log log = new Log();
    List<String> stations = Arrays.asList("A","B","C");
    mbta.addLine("purple",stations);
    Train purple = Train.make("purple");
    Station a = Station.make("A");
    Station b = Station.make("B");
    Station c = Station.make("C");
    List<String> journey = Arrays.asList("A","C");
    log.train_moves(purple, c, a);
    Passenger kitty = Passenger.make("Kitty");
    mbta.addJourney("Kitty", journey);
    log.passenger_boards(kitty, purple, a);
    log.train_moves(purple, a, c);
    log.passenger_deboards(kitty, purple, c);
    Verify.verify(mbta, log);
  }
  @Test
  public void test1(){
    assertTrue(Passenger.make("red") == Passenger.make("red"));
  }

  @Test
  public void testloadConfig(){
    MBTA mbta = new MBTA();
    mbta.addLine("red",Arrays.asList("s","a","b"));
    mbta.addJourney("bob",Arrays.asList("d","s"));
    mbta.loadConfig("sample.json");
  }

  @Test
  public void testMoveEvent(){
    MBTA mbta = new MBTA();
    /*
    mbta.loadConfig("sample.json");
    MoveEvent moveEvent1 = new MoveEvent(Train.make("red"),Station.make("Davis"),Station.make("Harvard"));
    moveEvent1.replayAndCheck(mbta);
    MoveEvent moveEvent2 = new MoveEvent(Train.make("red"),Station.make("Harvard"),Station.make("Kendall"));
    moveEvent2.replayAndCheck(mbta);

     */
    List<String> stations = Arrays.asList("A","B","C");
    mbta.addLine("purple",stations);
    Train purple = Train.make("purple");
    Station a = Station.make("A");
    Station b = Station.make("B");
    Station c = Station.make("C");
    mbta.addJourney("kitty",Arrays.asList("B","C"));
    MoveEvent moveEvent1 = new MoveEvent(Train.make("purple"),Station.make("A"),Station.make("B"));
    BoardEvent boardEvent1 = new BoardEvent(Passenger.make("kitty"),Train.make("purple"),Station.make("B"));
    MoveEvent moveEvent2 = new MoveEvent(Train.make("purple"),Station.make("B"),Station.make("C"));
    MoveEvent moveEvent3 = new MoveEvent(Train.make("purple"),Station.make("C"),Station.make("B"));
    MoveEvent moveEvent4 = new MoveEvent(Train.make("purple"),Station.make("B"),Station.make("A"));
    MoveEvent moveEvent5 = new MoveEvent(Train.make("purple"),Station.make("A"),Station.make("B"));
    moveEvent1.replayAndCheck(mbta);
    boardEvent1.replayAndCheck(mbta);
    moveEvent2.replayAndCheck(mbta);
    moveEvent3.replayAndCheck(mbta);
    moveEvent4.replayAndCheck(mbta);
    moveEvent5.replayAndCheck(mbta);

  }

  @Test
  public void testBoardEvent(){
    MBTA mbta = new MBTA();
    List<String> stations = Arrays.asList("A","B","C");
    mbta.addLine("purple",stations);
    Train purple = Train.make("purple");
    Station a = Station.make("A");
    Station b = Station.make("B");
    Station c = Station.make("C");
    mbta.addJourney("kitty",Arrays.asList("A","C"));
    MoveEvent moveEvent1 = new MoveEvent(Train.make("purple"),Station.make("A"),Station.make("B"));
    BoardEvent boardEvent1 = new BoardEvent(Passenger.make("kitty"),Train.make("purple"),Station.make("A"));
    MoveEvent moveEvent2 = new MoveEvent(Train.make("purple"),Station.make("B"),Station.make("C"));
    DeboardEvent deboardEvent1 = new DeboardEvent(Passenger.make("kitty"),Train.make("purple"),Station.make("C"));
    MoveEvent moveEvent3 = new MoveEvent(Train.make("purple"),Station.make("C"),Station.make("B"));
    MoveEvent moveEvent4 = new MoveEvent(Train.make("purple"),Station.make("B"),Station.make("A"));
    MoveEvent moveEvent5 = new MoveEvent(Train.make("purple"),Station.make("A"),Station.make("B"));
    System.out.println(boardEvent1);
    boardEvent1.replayAndCheck(mbta);
    System.out.println(moveEvent1);
    moveEvent1.replayAndCheck(mbta);
    System.out.println(deboardEvent1);
    deboardEvent1.replayAndCheck(mbta);
    //moveEvent2.replayAndCheck(mbta);
    //boardEvent1.replayAndCheck(mbta);
    //moveEvent2.replayAndCheck(mbta);
   // moveEvent3.replayAndCheck(mbta);
    //boardEvent1.replayAndCheck(mbta);
    //moveEvent4.replayAndCheck(mbta);
    //moveEvent5.replayAndCheck(mbta);
  }

  @Test
  public void testBoardEvent21(){
    MBTA mbta = new MBTA();
    List<String> stations = Arrays.asList("A","B","C");

    mbta.addLine("purple",stations);
    Train purple = Train.make("purple");
    Station a = Station.make("A");
    Station b = Station.make("B");
    Station c = Station.make("C");
    mbta.addJourney("kitty",Arrays.asList("A","C"));
    MoveEvent moveEvent1 = new MoveEvent(Train.make("purple"),Station.make("A"),Station.make("B"));
    BoardEvent boardEvent1 = new BoardEvent(Passenger.make("kitty"),Train.make("purple"),Station.make("A"));
    MoveEvent moveEvent2 = new MoveEvent(Train.make("purple"),Station.make("B"),Station.make("C"));
    DeboardEvent deboardEvent1 = new DeboardEvent(Passenger.make("kitty"),Train.make("purple"),Station.make("C"));
    MoveEvent moveEvent3 = new MoveEvent(Train.make("purple"),Station.make("C"),Station.make("B"));
    MoveEvent moveEvent4 = new MoveEvent(Train.make("purple"),Station.make("B"),Station.make("A"));
    MoveEvent moveEvent5 = new MoveEvent(Train.make("purple"),Station.make("A"),Station.make("B"));
    System.out.println(boardEvent1);
    boardEvent1.replayAndCheck(mbta);
    System.out.println(moveEvent1);
    moveEvent1.replayAndCheck(mbta);
    System.out.println(deboardEvent1);
    deboardEvent1.replayAndCheck(mbta);

  }

  @Test
  public void testDeBoardEvent(){
    var log = new Log();
    var mbta = new MBTA();
    mbta.loadConfig("sample2.json");
    Sim.run_sim(mbta, log);

  }
  /*

  @Test

  public void testlog(){
    MBTA mbta = new MBTA();
    Log log = new Log();
    Passenger bob = Passenger.make("Bob");
    Passenger rob = Passenger.make("Rob");
    Train red = Train.make("Red");
    Station davis = Station.make("Davis");
    Station porter = Station.make("Porter");
    Station harvard = Station.make("Harvard");
    Station central = Station.make("Central");
    Station kendall = Station.make("Kendall");
    Station park = Station.make("Park");
    mbta.addLine("Red",Arrays.asList("Davis","Porter","Harvard","Central","Kendall","Park"));
    mbta.addJourney("Bob", Arrays.asList("Porter","Davis"));
    mbta.addJourney("Rob", Arrays.asList("Davis","Kendall"));
    log.passenger_boards(rob,red,davis);
    log.train_moves(red,davis,porter);
    // bob boards at porter
    //log.passenger_boards(bob,red,porter);
    log.train_moves(red,porter,harvard);
    // rob boards at harvard
    log.train_moves(red,harvard,central);
    log.train_moves(red,central,kendall);
    // rob deboards at kendall
    log.passenger_deboards(rob,red,kendall);
    log.train_moves(red,kendall,park);
    log.train_moves(red,park,kendall);
    log.train_moves(red,kendall,central);
    log.train_moves(red,central,harvard);
    log.train_moves(red,harvard,porter);
    log.passenger_boards(bob,red,porter);
    log.train_moves(red,porter,davis);
    //bob deboards at davis
    log.passenger_deboards(bob,red,davis);
    // result should be valid
    Verify.verify(mbta,log);

  }

   */

  /*

  @Test
  public void testTrainThread1(){
    MBTA mbta = new MBTA();
    mbta.loadConfig("sample.json");
    Log log = new Log();
    //TrainThread trainThread = new TrainThread(log,mbta,Train.make("red"));
    Thread t1 = new Thread(new TrainThread(log,mbta,Train.make("orange")));
    Thread t2 = new Thread(new TrainThread(log,mbta,Train.make("red")));
    Thread t3 = new Thread(new TrainThread(log,mbta,Train.make("blue")));
    Thread t4 = new Thread(new TrainThread(log,mbta,Train.make("green")));
    //Thread t5 = new Thread(new PassengerThread(log,mbta,Passenger.make("Bob")));
    //Thread t6 = new Thread(new PassengerThread(log,mbta,Passenger.make("Alice")));
    Thread t7 = new Thread(new PassengerThread(log,mbta,Passenger.make("Carol")));

    //Thread t2 = new Thread(trainThread);
    t1.start();
    t2.start();
    t3.start();
    t4.start();
    //t5.start();
    //t6.start();
    t7.start();

    try {
      t1.join();
      t2.join();
      t3.join();
      t4.join();
      //t5.join();
      //t6.join();
      t7.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

   */

  @Test
  public void testTrainThread2(){
    MBTA mbta = new MBTA();
    mbta.loadConfig("sample.json");
    Log log = new Log();
    StopSignal stop = new StopSignal(mbta);
    //TrainThread trainThread = new TrainThread(log,mbta,Train.make("red"));
    Thread t1 = new Thread(new TrainThread(log,mbta,Train.make("orange"),stop));
    Thread t2 = new Thread(new TrainThread(log,mbta,Train.make("red"),stop));
    Thread t3 = new Thread(new TrainThread(log,mbta,Train.make("blue"),stop));
    Thread t4 = new Thread(new TrainThread(log,mbta,Train.make("green"),stop));
    Thread t5 = new Thread(new PassengerThread(log,mbta,Passenger.make("Bob"),stop));
    Thread t6 = new Thread(new PassengerThread(log,mbta,Passenger.make("Alice"),stop));
    Thread t7 = new Thread(new PassengerThread(log,mbta,Passenger.make("Carol"),stop));
    Thread t8 = new Thread(stop);

    //Thread t2 = new Thread(trainThread);
    t1.start();
    t2.start();
    t3.start();
    t4.start();
    t5.start();
    t6.start();
    t7.start();
    t8.start();


    try {
      t1.join();
      t2.join();
      t3.join();
      t4.join();
      t5.join();
      t6.join();
      t7.join();
      t8.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }



    mbta.reset();
    mbta.loadConfig("sample.json");
    Verify.verify(mbta, log);

  }

  public void testTrainThread3(){
    MBTA mbta = new MBTA();
    mbta.loadConfig("sample.json");
    Log log = new Log();
    StopSignal stop = new StopSignal(mbta);
    Thread t2 = new Thread(new TrainThread(log,mbta,Train.make("red"),stop));
    Thread t5 = new Thread(new PassengerThread(log,mbta,Passenger.make("Bob"),stop));

    Thread t8 = new Thread(stop);

    //Thread t2 = new Thread(trainThread);

    t2.start();

    t5.start();

    t8.start();


    try {

      t2.join();

      t5.join();

      t8.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    mbta.reset();
    mbta.loadConfig("sample.json");
    Verify.verify(mbta, log);

  }



}
