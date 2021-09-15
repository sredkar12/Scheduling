package timerexample;

import java.util.*;

public class TimerExample extends TimerTask {

    private static String name;
    List<JobDetails> jobsQueue = new LinkedList<>();
    private JobDetails jobDetails;

    public TimerExample(JobDetails jobDetails, List<JobDetails> que) {

        this.jobsQueue = que;
        this.jobDetails = jobDetails;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + jobDetails.getJobName() + " will be added to Queue at " + new Date());


            synchronized (jobsQueue) {
                jobDetails.setStatus("Queued");
                jobsQueue.add(jobDetails);
                jobsQueue.notifyAll();
            }


        System.out.println("Leaving Timer  " + jobDetails.getJobName() + " at " + new Date() + " with " + jobDetails.getStatus());

    }

   public static String getAnyObject() {
       return "my name is " + name ;
    }
}