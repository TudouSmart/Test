import java.util.concurrent.*;

/**
 * Created by hugang on 2018/1/3.
 */
public class RunnableTest {

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<String> future = executorService.submit(() -> {System.out.println("funtest");return "test";});
        executorService.shutdown();
        for (;;) {
            if (future.isDone()) {
                System.out.println(future.get(100, TimeUnit.MILLISECONDS));
                System.out.println(future.get(100, TimeUnit.MILLISECONDS));
                break;
            }
        }
    }
}
