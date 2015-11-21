package threadpool;

import sun.print.resources.serviceui;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by usegutierrez on 11/19/15.
 */
public class MonitorThread implements Runnable {

    private ThreadPoolExecutor executor;
    private int seconds;
    private boolean isRunning = true;

    public MonitorThread(ThreadPoolExecutor executor, int delay) {
        this.executor = executor;
        seconds = delay;
    }

    public void shutdown() {
        this.isRunning = false;
    }

    @Override
    public void run() {

        while(isRunning) {
            log();
            try {
                Thread.sleep(seconds*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

                if(this.executor.getActiveCount() == 0) {

                    log();
                    for(int x = 1; x <= 3; x++) {
                        try {
                            System.err.println("Monitoring thread will now shutdown");
                            System.err.println("... in " + x);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        this.executor.shutdown();
                        this.shutdown();

                        this.executor.awaitTermination(3, TimeUnit.SECONDS);
                        if(this.executor.isShutdown()) {
                            log();
                            break;
                        } else {
                            System.out.println("stuck on limbo");
                        }



                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        System.out.println("done killing the thread pool and monitoring");
    }

    public void log() {
        System.out.println(String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                this.executor.getPoolSize(),
                this.executor.getCorePoolSize(),
                this.executor.getActiveCount(),
                this.executor.getCompletedTaskCount(),
                this.executor.getTaskCount(),
                this.executor.isShutdown(),
                this.executor.isTerminated()));
    }
}
