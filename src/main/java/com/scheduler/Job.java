package com.scheduler;

public class Job {
    private final int id;
    private final int burstTime;
    private final int priority;
    private String status;

    public Job(int id, int burstTime, int priority) {
        this.id = id;
        this.burstTime = burstTime;
        this.priority = priority;
        this.status = "Pending";
    }

    public int getId() {
        return id;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Job{id=" + id + ", burstTime=" + burstTime + ", priority=" + priority + ", status=" + status + '}';
    }
}
