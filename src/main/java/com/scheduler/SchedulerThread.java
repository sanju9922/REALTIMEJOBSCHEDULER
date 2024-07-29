package com.scheduler;

public class SchedulerThread extends Thread {
    private final Scheduler scheduler;

    public SchedulerThread(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run() {
        scheduler.schedule();
    }
}
