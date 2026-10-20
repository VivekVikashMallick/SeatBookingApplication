package org.example;

public class StatusMonitor implements Runnable{
    private volatile boolean running = true;

    @Override
    public void run(){
        while (running) {
            System.out.println("[MONITOR] Booking service is running.");

            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Monitor stopped.");
    }
    public void stopMonitoring(){
        running = false;
    }
}
