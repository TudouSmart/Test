package zookeeper;

import org.apache.zookeeper.*;

import static org.apache.zookeeper.ZooDefs.Ids.*;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by hugang on 2017/11/18.
 *
 */
public class Begin {
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);
        String ipPort = in.nextLine();
        Master master = new Master(ipPort);
        master.startZK();
        Thread.sleep(60000);
        master.stopZK();
    }

    static boolean isLeader;
    private static Random random = new Random();
    static AsyncCallback.StringCallback masterCallback = new AsyncCallback.StringCallback() {
        public void processResult(int rc, String path, Object ctx, String name) {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    checkMaster();
                    break;
                case OK:
                    isLeader = true;
                    break;
                default:
                    isLeader = false;
            }
            System.out.println("I'm " + (isLeader ? "" : "not ") +
                    "the leader");
        }

    };

    private static void checkMaster() {

    }

    private static class Master implements Watcher {
        private ZooKeeper zk;
        private String hostPort;

        private String serverId = Integer.toHexString(random.nextInt());

        Master(String hostPort) {
            System.out.println("init hostPort:"+hostPort);
            this.hostPort = hostPort;
        }

        void runForMaster() throws InterruptedException, KeeperException{
            zk.create(
                    "/master",
                    serverId.getBytes(),
                    OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL
            );
        }

        void startZK() {
            try {
                zk = new ZooKeeper(hostPort, 15000, this);
            } catch (IOException e) {
                zk = null;
            }
        }

        void stopZK() throws InterruptedException{
            if (zk != null) {
                zk.close();
            }
        }

        public void process(WatchedEvent e) {
            System.out.println(e);
        }
    }


}
