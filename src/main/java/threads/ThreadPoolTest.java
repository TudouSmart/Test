package threads;


import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {


    public static void main(String[] args) {

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(2);

        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        }, 1000, TimeUnit.MILLISECONDS);


    }
}
