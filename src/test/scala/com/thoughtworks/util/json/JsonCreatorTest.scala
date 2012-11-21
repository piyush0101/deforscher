package com.thoughtworks.util.json

import org.scalatest.FunSuite

class JsonCreatorTest extends FunSuite {

  test("should form json from a static list of dependencies") {
        val jsonProducer = new JsonCreator
        val dependencies = Map("com.thoughtworks.analysis.A" -> List("com.thoughtworks.analysis.B"))
        jsonProducer.toJson(dependencies)
  }

}
