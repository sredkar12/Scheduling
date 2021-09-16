package timerexample;

import java.util.Date;
import java.util.List;
import java.util.Queue;

public class Producer extends Thread {
    private final Queue<JobDetails> taskQueue;

    public Producer(Queue<JobDetails> runningQueue) {
        this.taskQueue = runningQueue;
    }

    @Override
    public void run() {
        JobDetails jobDetails = null;

            synchronized (taskQueue) {
                while (!taskQueue.isEmpty()) {
                    try {
                        System.out.println("Producer: current Q size before start propagation is " + taskQueue.size());
                        jobDetails = taskQueue.peek();
                        jobDetails.setStatus("RUNNING");
                        System.out.println("==============Producer: job with " + jobDetails.getJobName() + " started at " + new Date());
                        Thread.sleep(130000L);
                        jobDetails.setStatus("COMPLETED");
                        System.out.println("==============Producer: job with " + jobDetails.getJobName() + " completed at " + new Date());
                        System.out.println("Producer: current Q size after completion  is " + taskQueue.size());
                        taskQueue.remove();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }


            }


    }
}



