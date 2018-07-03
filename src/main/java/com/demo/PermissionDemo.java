package com.demo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;


import static java.nio.file.attribute.PosixFilePermission.*;

/**
 * Created by hugang on 2017/7/14.
 */
public class PermissionDemo {
    public static void main(String[] args) {
        try {
            String dir = System.getProperty("user.dir");
            Path path = Paths.get(append(dir, "/.profile"));
            PosixFileAttributes attrs = Files.readAttributes(path, PosixFileAttributes.class);
            Set<PosixFilePermission> posixFilePermissions = attrs.permissions();
            posixFilePermissions.clear();
            String perms = PosixFilePermissions.toString(posixFilePermissions);
            String owner = attrs.owner().getName();
            System.out.format("%s %s%n", owner, perms);
            posixFilePermissions.add(OWNER_READ);
            posixFilePermissions.add(GROUP_READ);
            posixFilePermissions.add(OTHERS_READ);
            posixFilePermissions.add(OWNER_WRITE);
            Files.setPosixFilePermissions(path, posixFilePermissions);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public static String append(String source, String... appends) {
        StringBuilder sb = new StringBuilder(source);
        for (String append:appends) {
            sb.append(append);
        }
        return sb.toString();
    }
}
