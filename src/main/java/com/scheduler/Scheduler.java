package com.scheduler;

import java.util.List;

public abstract class Scheduler {
    protected List<Job> jobList;

    public Scheduler(List<Job> jobList) {
        this.jobList = jobList;
    }

    public abstract void schedule();

    protected void printJobOrder(List<Job> order) {
        for (Job job : order) {
            System.out.println(job);
        }
    }

    protected void updateJobStatus(Job job, JobStatus status) {
        job.setStatus(status.name());
    }
}
