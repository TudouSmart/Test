package threads;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureTest {

    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new Call());

        new Thread(futureTask).start();
    }

    private static class Call implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("call");
            return 1;
        }
    }
}
