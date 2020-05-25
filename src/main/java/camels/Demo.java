package camels;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {

    public static class ListNode {
        int val;

        ListNode next;

        ListNode(int x) { val = x;
        }
    }

    public static void main(String[] args) {
        new Demo().permutation("abc");
    }

    private List<String> res = new ArrayList<>();

    public String[] permutation(String s) {
        help(0, s.toCharArray(), new char[s.length()], new boolean[s.length()]);
        return res.toArray(new String[0]);
    }

    public void help(int index, char[] arr, char[] hh, boolean[] used) {
        if (index == arr.length) {
            res.add(new String(hh));
        }

        for (int i = 0; i < arr.length; i++) {
            if (! used[i]) {
                hh[index] = arr[i];
                used[index] = true;
                help(index+1, arr, hh, used);
                used[index] = false;
            }
        }
    }

}
