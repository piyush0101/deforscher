package com.thoughtworks.util

import org.scalatest.FunSuite

class ClassFinderTest extends FunSuite {

  test("should find all classes in a given package") {
    val classFinder = new ClassFinder("com.thoughtworks.analysis")
    val files = classFinder.findClasses()
    assert(files.contains("A.class"))
    assert(files.contains("B.class"))
    assert(files.contains("C.class"))
    assert(files.size == 3)
  }

}
