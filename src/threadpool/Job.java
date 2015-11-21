package threadpool;

/**
 * Created by usegutierrez on 11/19/15.
 */
public class Job implements Runnable {

    private String threadName;
    public static int countJob = 0;


    public Job(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {

        System.out.println("thread running name is : " + threadName);

        process();
        if(countJob == 3) {
            System.out.println(1/0);
        }
    }

    public void process() {
        try {
            Thread.sleep(250);
            countJob++;

            //System.err.println(">>>>>>> worker with " + threadName + " is processing");

        } catch (InterruptedException ie) {
            System.out.println("thread running name is : " + threadName + " is interrupted");
            ie.printStackTrace();
        }
    }


    public String toString() {
        return this.threadName;

    }

}
