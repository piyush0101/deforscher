package com.thoughtworks.util;

import org.junit.Test;
import org.objectweb.asm.ClassReader;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;


public class DependencyFinderTest {

    @Test
    public void shouldFindAllFieldDependenciesOfAGivenClass() throws Exception {
        FieldDependencyVisitor visitor = new FieldDependencyVisitor();
        ClassReader reader = new ClassReader("com.thoughtworks.analysis.A");

        DependencyFinder finder = new DependencyFinder(visitor, reader);

        List<String> dependencies = finder.findDependencies();

        assertThat(dependencies, hasSize(2));
        assertThat(dependencies,
                containsInAnyOrder
                        ("com.thoughtworks.analysis.B",
                         "com.thoughtworks.analysis.C"));
    }

    @Test
    public void shouldReportIllegalityWhenCaptainIsOverAge60AndFlightOfficerIsBelowAge60AndTheTripHasAnInternationalLeg() {


    }

    @Test
    public void shouldReportIllegalityWhenFlightOfficerIsOverAge60AndCaptainIsBelowAge60AndTheTripHasAnInternationalLeg() {

    }

    @Test
    public void shouldReturnALegalCrewMemberWhenTripIsNotInternationalAndBothFlightOfficersAreBelowAge60() {

    }

    @Test
    public void shouldReturnALegalCrewMemberWhenBothOfTheFlightOfficersAreBelowAge60() {

    }

    @Test
    public void shouldReturnALegalCrewMemberWhen() {

    }
}
