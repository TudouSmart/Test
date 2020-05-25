package org.tudou.test;


import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Queue;

public class Demo {


    public static void main(String[] args) throws Throwable {
//        invokeReflection();
//        invokeMethodHandle();
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }

    public static boolean isPalindrome(String s) {
        int first = 0;
        int end = s.length()-1;

        while (first < end) {
            while (first < s.length()
                && ! Character.isAlphabetic(s.charAt(first))
                && ! Character.isDigit(s.charAt(first)) ) {
                first ++;
            }

            while (end > 0
                && ! Character.isAlphabetic(s.charAt(end))
                && ! Character.isDigit(s.charAt(end)) ) {
                end --;
            }

            if (first >= s.length() || end < 0) {
                return true;
            }

            if (Character.toLowerCase(s.charAt(first++)) != Character.toLowerCase(s.charAt(end--)) ) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public static void invokeReflection() throws Exception {
        Demo demo = new Demo();
        Class clazz = Demo.class;
        Method method = clazz.getDeclaredMethod("test");
        method.invoke(demo);
    }

    public static void invokeMethodHandle() throws Throwable {
        MethodType methodType = MethodType.methodType(Void.class);

        Demo demo = new Demo();
        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(Demo.class, "test", methodType);
        new ConstantCallSite(methodHandle).dynamicInvoker().invoke(demo);
    }

    public void test() {
        System.out.println("test");
    }
}
