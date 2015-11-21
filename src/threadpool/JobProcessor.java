package threadpool;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by usegutierrez on 11/19/15.
 */
public class JobProcessor {


    public static final ThreadPoolExecutor executorPool = new ErrorReportingThreadPoolExecutor(2);
            /*new ThreadPoolExecutor(20, 40, 1000, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10), threadFactory, rejectionHandler);*/

    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();

        final MonitorThread monitor = new MonitorThread(executorPool, 3);
        final Thread monitorThread = new Thread(monitor);
        monitorThread.start();

        for(int index = 0; index < 300; index++) {
            executorPool.submit(new Job("Job-" + index));
            //new Job("Job-" + index).process();
        }
    }
}

