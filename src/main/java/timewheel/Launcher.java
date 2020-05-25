package timewheel;

public class Launcher {

    public static void main(String[] args) throws Exception{
        TimeSlot[] slots = TimeSlot.init(60);
        TimeWheel timeWheel = new TimeWheel(slots, 0);

        timeWheel.add(new TaskNode(2, "task1"));
        timeWheel.add(new TaskNode(5, "task2"));
        timeWheel.add(new TaskNode(5, "task3"));
        timeWheel.add(new TaskNode(5, "task3"));
        timeWheel.add(new TaskNode(40, "task4"));
        timeWheel.add(new TaskNode(30, "task5"));
        timeWheel.add(new TaskNode(30, "task6"));

        for (;;) {
            System.out.println(System.currentTimeMillis()+ ", " +timeWheel.getCurrentTime());
            TaskNode[] tasks = timeWheel.nextTask();
            if (tasks != null) {
                for (TaskNode task : tasks) {
                    System.out.println(System.currentTimeMillis()+ "," +task.getTaskName());
                }
            }
            Thread.sleep(1000);
        }
    }
}
