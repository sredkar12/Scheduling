package timerexample;

public class JobDetails {
    String jobName;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String destinationTemplate;
    String status;
    // have all the job schedule details below

    public String getJobName() {
        return jobName;
    }

    public String getDestinationTemplate() {
        return destinationTemplate;
    }


    // String propagationTargets ..
    // .........

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setDestinationTemplate(String destinationTemplate) {
        this.destinationTemplate = destinationTemplate;
    }


    public JobDetails(String jname, String dname) {
        this.jobName = jname;
        this.destinationTemplate = dname;
    }
}
