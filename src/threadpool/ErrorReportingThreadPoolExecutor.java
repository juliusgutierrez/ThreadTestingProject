package threadpool;

import java.util.concurrent.*;

/**
 * Created by usegutierrez on 11/19/15.
 */
public class ErrorReportingThreadPoolExecutor extends ThreadPoolExecutor {

    public static final RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
    public static final ThreadFactory threadFactory = Executors.defaultThreadFactory();

    public ErrorReportingThreadPoolExecutor(int nThreads) {
        super(20, 40,
                1000, TimeUnit.MILLISECONDS,
                //new LinkedBlockingQueue<Runnable>()
                new ArrayBlockingQueue<Runnable>(10), threadFactory,
                rejectionHandler
        );
    }

    @Override
    protected void afterExecute(Runnable task, Throwable thrown) {
        super.afterExecute(task, thrown);

        if (thrown != null) {
            // an unexpected exception happened inside ThreadPoolExecutor
            thrown.printStackTrace();
        }

        if (task instanceof Future<?>) {
            // try getting result
            // if an exception happened in the job, it'll be thrown here
            try {
                Object result = ((Future<?>)task).get();
            } catch (CancellationException e) {
                // the job get canceled (may happen at any state)
                e.printStackTrace();
            } catch (ExecutionException e) {
                // some uncaught exception happened during execution
                e.printStackTrace();
                //JobProcessor.executorPool.shutdownNow();
            } catch (InterruptedException e) {
                // current thread is interrupted
                // ignore, just re-throw
                Thread.currentThread().interrupt();
                //JobProcessor.executorPool.shutdownNow();

            }
        }
    }

}
