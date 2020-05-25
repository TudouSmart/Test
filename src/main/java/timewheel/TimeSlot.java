package timewheel;

public class TimeSlot {

    private TaskNode head;

    public TimeSlot(TaskNode head) {
        this.head = head;
    }

    public TaskNode getHead() {
        return head;
    }

    public TimeSlot setHead(TaskNode head) {
        this.head = head;
        return this;
    }

    public synchronized void add(TaskNode task) {
        TaskNode curr = head.getNext();
        TaskNode pre = head;
        while (curr != null && curr.getTime() <= task.getTime()) {
            pre = curr;
            curr = curr.getNext();
        }

        pre.setNext(task);
        task.setNext(curr);
    }

    public static TimeSlot[] init(int n) {
        TimeSlot[] slots = new TimeSlot[n];
        for (int i = 0; i < n; i ++) {
            slots[i] = new TimeSlot(new TaskNode());
        }

        return slots;
    }
}
