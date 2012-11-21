package com.thoughtworks.util;

import org.junit.Test;
import org.objectweb.asm.ClassReader;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class DependencyFinderTest {

    @Test
    public void shouldFindAllFieldDependenciesOfAGivenClass() throws Exception {
        DependencyVisitor visitor = new DependencyVisitor();
        ClassReader reader = new ClassReader("com.thoughtworks.analysis.A");

        DependencyFinder finder = new DependencyFinder(visitor, reader);

        List<String> dependencies = finder.findDependencies();

        assertEquals(dependencies.size(), 2);
        assertTrue(dependencies.contains("com.thoughtworks.analysis.B"));
        assertTrue(dependencies.contains("com.thoughtworks.analysis.C"));
    }
}
