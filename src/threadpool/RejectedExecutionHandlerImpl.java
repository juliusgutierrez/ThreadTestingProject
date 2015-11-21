package threadpool;

import sun.tools.jar.resources.jar;

import java.util.concurrent.*;

/**
 * Created by usegutierrez on 11/19/15.
 */
public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {

    public static int REJECTED_TASK = 0;

    @Override
    public void rejectedExecution(Runnable job, ThreadPoolExecutor executor) {
        /*System.out.println(job + " is rejected");
        System.out.println("Retrying to Execute");*/

        if (!executor.isShutdown()) {
            job.run();
        }

        REJECTED_TASK++;
        /*try {
           JobProcessor.alternateExecutor.execute(job);
            System.out.println(job + "execution started");
        } catch (Exception e) {
            System.out.println("Failure to Re-execute "+e.getMessage());
        }*/
    }
}
