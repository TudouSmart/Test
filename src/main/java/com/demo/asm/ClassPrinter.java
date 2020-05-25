package com.demo.asm;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassAdapter;
import jdk.internal.org.objectweb.asm.*;

import java.util.HashMap;
import java.util.Map;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class ClassPrinter extends ClassVisitor {

    public ClassPrinter() {
        super(ASM4);
    }

    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }

    public void visitSource(String source, String debug) {
    }

    public void visitOuterClass(String owner, String name, String desc) {
    }

    public AnnotationVisitor visitAnnotation(String desc,
                                             boolean visible) {
        return null;
    }

    public void visitAttribute(Attribute attr) {
    }

    public void visitInnerClass(String name, String outerName,
                                String innerName, int access) {
    }

    public FieldVisitor visitField(int access, String name, String desc,
                                   String signature, Object value) {
        System.out.println(" " + desc + " " + name);
        return null;
    }

    public MethodVisitor visitMethod(int access, String name,
                                     String desc, String signature, String[] exceptions) {
        System.out.println(" " + name + desc);
        return null;
    }

    public void visitEnd() {
        System.out.println("}");
    }

    public static class TClassAdapter extends ClassAdapter {
        public TClassAdapter(com.sun.xml.internal.ws.org.objectweb.asm.ClassVisitor cv) {
            super(cv);
        }
    }

    public static void main(String[] args) throws Exception{
//        ClassPrinter printer = new ClassPrinter();
//        ClassReader classReader = new ClassReader("com.demo.CountTask");
//
//        classReader.accept(printer, 0);
    }
}