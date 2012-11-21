package com.thoughtworks.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class DependencyFinderTest {

    @Test
    public void shouldFindAllFieldDependenciesOfAGivenClass() {
        DependencyFinder finder = new DependencyFinder(new DependencyVisitor());
        List<String> dependencies = finder.findDependencies("com.thoughtworks.analysis.A");

        assertEquals(dependencies.size(), 2);
        assertTrue(dependencies.contains("com.thoughtworks.analysis.B"));
        assertTrue(dependencies.contains("com.thoughtworks.analysis.C"));
    }
}
