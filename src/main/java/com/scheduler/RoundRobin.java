package com.scheduler;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobin extends Scheduler {
    private final int timeQuantum;

    public RoundRobin(List<Job> jobList, int timeQuantum) {
        super(jobList);
        this.timeQuantum = timeQuantum;
    }

    @Override
    public void schedule() {
        System.out.println("Round Robin Scheduling:");
        Queue<Job> queue = new LinkedList<>(jobList);
        while (!queue.isEmpty()) {
            Job job = queue.poll();
            updateJobStatus(job, JobStatus.RUNNING);
            System.out.println(job);
            try {
                Thread.sleep(Math.min(job.getBurstTime(), timeQuantum) * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            job = new Job(job.getId(), Math.max(job.getBurstTime() - timeQuantum, 0), job.getPriority());
            if (job.getBurstTime() > 0) {
                queue.add(job);
            } else {
                updateJobStatus(job, JobStatus.COMPLETED);
                System.out.println(job);
            }
        }
    }
}
