package com.thoughtworks.util;

import org.objectweb.asm.ClassReader;

import java.util.List;

public class DependencyFinder {

    private final DependencyVisitor visitor;
    private final ClassReader reader;

    public DependencyFinder(DependencyVisitor visitor, ClassReader reader) {
        this.visitor = visitor;
        this.reader = reader;
    }

    public List<String> findDependencies() {
        reader.accept(visitor, 0);
        return visitor.getDependencies();
    }
}
