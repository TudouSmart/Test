package timewheel;

public class TaskNode {

    private long time;

    private TaskNode next;

    private String taskName;

    public TaskNode() {}

    public TaskNode(long time, String taskName) {
        this.time = time;
        this.taskName = taskName;
    }

    public long getTime() {
        return time;
    }

    public TaskNode setTime(long time) {
        this.time = time;
        return this;
    }

    public TaskNode getNext() {
        return next;
    }

    public TaskNode setNext(TaskNode next) {
        this.next = next;
        return this;
    }

    public String getTaskName() {
        return taskName;
    }

    public TaskNode setTaskName(String taskName) {
        this.taskName = taskName;
        return this;
    }
}
