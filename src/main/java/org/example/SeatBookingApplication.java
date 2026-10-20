package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SeatBookingApplication {
    public static void main(String[] args) {

        Event ev = new Event("Community event", 10);
        BookingService service = new BookingService(ev);

        StatusMonitor statusMonitor = new StatusMonitor();
        Thread monitorThread = new Thread(statusMonitor);
        monitorThread.start();

        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            executor.submit(new BookingTask(service, "Anaya", 3));
            executor.submit(new BookingTask(service, "Ravi", 3));
            executor.submit(new BookingTask(service, "Meera", 5));
            executor.submit(new BookingTask(service, "Dev", 11));

        } finally {
            executor.shutdown();

        }
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();

                // Ensure the monitor is stopped before waiting again.
                statusMonitor.stopMonitoring();
                monitorThread.interrupt();

                executor.awaitTermination(5, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        } finally {
            statusMonitor.stopMonitoring();
            monitorThread.interrupt();

            try {
                monitorThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        service.displaySummary();
    }
}
