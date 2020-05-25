package timewheel;

import java.util.ArrayList;
import java.util.List;

public class TimeWheel {

    private TimeSlot[] slots;

    private int currentTime;

    public TimeWheel(TimeSlot[] slots, int currentTime) {
        this.slots = slots;
        this.currentTime = currentTime;
    }

    public void add(TaskNode task) {
        int index = (int) task.getTime() % slots.length;
        slots[index].add(task);
    }

    public TimeSlot[] getSlots() {
        return slots;
    }

    public TimeWheel setSlots(TimeSlot[] slots) {
        this.slots = slots;
        return this;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public TimeWheel setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
        return this;
    }

    public TaskNode[] nextTask() {
        TimeSlot slot = slots[currentTime++ % slots.length];
        List<TaskNode> tasks = new ArrayList<>();
        TaskNode head = slot.getHead().getNext();
        while (head != null) {
            tasks.add(head);
            head = head.getNext();
        }
        slot.setHead(null);

        return tasks.toArray(new TaskNode[0]);
    }
}
