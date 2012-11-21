package com.thoughtworks.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class DependencyVisitor extends ClassVisitor {

    private List<String> dependencies = new ArrayList<String>();

    public DependencyVisitor() {
        super(Opcodes.ASM4);
    }

    @Override
    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        System.out.println(name + "extends" + superName + "{");
    }

    @Override
    public void visitEnd() {
        System.out.println("}");
    }

    @Override
    public FieldVisitor visitField(int access, String name, String description,
                                   String signature, Object value) {
        String dependency = replaceSlashesWithDots(description);
        addToDependencyList(dependency);
        return null;
    }

    private void addToDependencyList(String description) {
        if (description.length() > 1) {
            dependencies.add(description.substring(1, description.length() - 1));
        }
    }

    private String replaceSlashesWithDots(String description) {
        return description.replace('/', '.');
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String description,
                                     String signature, String[] exceptions) {
        System.out.println("   " + name + description);
        return null;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

}
