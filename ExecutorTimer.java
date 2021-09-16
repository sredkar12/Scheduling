package timerexample;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class ExecutorTimer {



    JobDetails jobDetails;

    private int counter = 1;
    static DispatcherFramework dispatcher;


    public Queue<JobDetails> startTimer(String timestr) throws ParseException {

        String dest = "mytemplate";
        String taskname = "Task" + "_" + counter;

        String jobname = "UIjob" + "_" + counter;
        this.jobDetails = new JobDetails(jobname, dest);
        ++counter;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(timestr);
        dispatcher.addJob(jobDetails, date);

            return null;

    }




    public static void main(String[] args) throws ParseException {

        //this will mimic the job creation times from the end users in the UI .
        String[] timesarr = {"2021-09-15 22:56:00", "2021-09-15 22:57:00", "2021-09-15 22:58:00"};
         ExecutorTimer executorTimer = new ExecutorTimer();
        Queue<JobDetails> dispList = new LinkedList<>();

        dispatcher = new DispatcherFramework();

        // this will not be an array but will be on an as come basis
        // for every new job creation from UI, the executorTime.startTimer will be called .
        for(String time: timesarr) {
            dispList = executorTimer.startTimer(time);
            System.out.println("Returned ");
        }

    }

}