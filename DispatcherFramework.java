package timerexample;

import java.text.SimpleDateFormat;
import java.util.*;

public class DispatcherFramework {

    boolean firstJob = true;
    private JobDetails jobDetails;

    Queue<JobDetails> runningQueue = new LinkedList<>();
    List<JobDetails> jobList = new ArrayList<>();
    Producer producer ;
    TimerExample te1;
    Timer t ;
    private int counter;
    private boolean firstTime = true;

    DispatcherFramework()
    {
        producer = new Producer(runningQueue);
        t = new Timer();
    }
    void addJob(JobDetails job, Date date) {

        te1 = new TimerExample(job, jobList);
        t.schedule(te1, date);
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (jobList) {
            try {
                jobList.wait();
                if (!jobList.isEmpty()) {
                    job = jobList.remove(0);
                    runningQueue.add(job);
                }
                jobList.notify();
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (firstTime && !runningQueue.isEmpty()) {
                producer.start();
                firstTime = false;
            } else if (!producer.isAlive() && !runningQueue.isEmpty()) {
                System.out.println("Prodcuer is not alive at " + new Date());
                producer = new Producer(runningQueue);
                producer.start();
            } else {
                if (producer.isAlive())
                    System.out.println("Producer  is still running at  " + new Date());
            }

        }


    }



}
