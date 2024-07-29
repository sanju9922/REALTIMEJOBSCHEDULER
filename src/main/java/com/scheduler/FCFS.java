package com.scheduler;

import java.util.List;

public class FCFS extends Scheduler {

    public FCFS(List<Job> jobList) {
        super(jobList);
    }

    @Override
    public void schedule() {
        System.out.println("FCFS Scheduling:");
        for (Job job : jobList) {
            updateJobStatus(job, JobStatus.RUNNING);
            System.out.println(job);
            try {
                Thread.sleep(job.getBurstTime() * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            updateJobStatus(job, JobStatus.COMPLETED);
            System.out.println(job);
        }
    }
}
