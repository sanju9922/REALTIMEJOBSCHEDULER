package com.scheduler;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SJN extends Scheduler {

    public SJN(List<Job> jobList) {
        super(jobList);
    }

    @Override
    public void schedule() {
        System.out.println("SJN Scheduling:");
        List<Job> sortedJobs = jobList.stream()
            .sorted(Comparator.comparingInt(Job::getBurstTime))
            .collect(Collectors.toList());
        for (Job job : sortedJobs) {
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
