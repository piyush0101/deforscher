package com.thoughtworks.util.json

import org.scalatest.FunSuite

class DependenciesJsonTest extends FunSuite {

  test("should form json from a static list of dependencies") {
        val jsonProducer = new DependenciesJson
        val dependencies = Map("com.thoughtworks.analysis.A" -> List("com.thoughtworks.analysis.B"))
        jsonProducer.toJson()
  }

}
