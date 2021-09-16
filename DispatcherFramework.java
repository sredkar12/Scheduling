package timerexample;

import java.text.SimpleDateFormat;
import java.util.*;

public class DispatcherFramework {

    boolean firstJob = true;
    private JobDetails jobDetails;

    Queue<JobDetails> runningQueue = new LinkedList<>();
    JobContainer jobContainer = new JobContainer();
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
       jobContainer.setJobDetails(job);
        te1 = new TimerExample(jobContainer);
        t.schedule(te1, date);
        try {
            Thread.sleep(1000l);
        synchronized (jobContainer) {
                jobContainer.wait();
                if (job.getStatus() == "QUEUED") {
                    runningQueue.add(jobContainer.getJobDetails());
                }
                jobContainer.notify();
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (firstTime && !runningQueue.isEmpty()) {
            producer.start();
            firstTime = false;
        } else if (!producer.isAlive() && !runningQueue.isEmpty()) {
            System.out.println(" .. WOW ..Producer is not alive at " + new Date() + "  starting Producer");
            producer = new Producer(runningQueue);
            producer.start();
        }
        else;


    }



}
