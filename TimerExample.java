package timerexample;

import java.util.*;

public class TimerExample extends TimerTask {

    private static String name;
    //List<JobDetails> jobsQueue = new LinkedList<>();
    private JobContainer jobContainer;

    public TimerExample(JobContainer jobContainer) {
        this.jobContainer = jobContainer;

    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + jobContainer.getJobDetails().getJobName() + " will be added to Queue at " + new Date());


            synchronized (jobContainer) {
                jobContainer.getJobDetails().setStatus("QUEUED");
                //jobsQueue.add(jobDetails);
                jobContainer.notifyAll();
            }


        System.out.println("Leaving Timer  " + jobContainer.getJobDetails().getJobName() + " at " + new Date() + " with " + jobContainer.getJobDetails().getStatus());

    }

   public static String getAnyObject() {
       return "my name is " + name ;
    }
}