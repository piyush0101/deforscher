package com.thoughtworks.util.json

import org.scalatest.{FlatSpec, FunSuite}
import net.liftweb.json._
import net.liftweb.json.JsonDSL._
import net.liftweb.json.JsonAST.{JInt, JString, JValue, JField}
import org.scalatest.matchers.ShouldMatchers
import scala.Some

class JsonCreatorTest extends FlatSpec with ShouldMatchers {

  "JsonCreator" should "produce json that has a field with key as name" in {
    val jsonProducer = new JsonCreator
    val dependencies = Map("com.thoughtworks.analysis.A" -> List("com.thoughtworks.analysis.B"))
    val json = jsonProducer.toJson(dependencies)

    val key: Option[JValue] = json find {
      case JField("name", _) => true
      case _ => false
    }

    key should be ('defined)
  }

  "JsonCreator" should "produce json that has a field with key as nodes" in {
    val jsonProducer = new JsonCreator
    val dependencies = Map("com.thoughtworks.analysis.A" -> List("com.thoughtworks.analysis.B"))
    val json = jsonProducer.toJson(dependencies)

    val key: Option[JValue] = json find {
      case JField("nodes", _) => true
      case _ => false
    }

    key should be ('defined)
  }

  "JsonCreator" should "produce json that has a field with key as links" in {
    val jsonProducer = new JsonCreator
    val dependencies = Map("com.thoughtworks.analysis.A" -> List("com.thoughtworks.analysis.B"))
    val json = jsonProducer.toJson(dependencies)

    val key: Option[JValue] = json find {
      case JField("links", _) => true
      case _ => false
    }

    key should be ('defined)
  }

  "JsonCreator" should "produce json that has a field with key as source" in {
    val jsonProducer = new JsonCreator
    val dependencies = Map("com.thoughtworks.analysis.A" -> List("com.thoughtworks.analysis.B"))
    val json = jsonProducer.toJson(dependencies)

    val key: Option[JValue] = json find {
      case JField("source", _) => true
      case _ => false
    }

    key should be ('defined)
  }

  "JsonCreator" should "produce json that has a field with key as target" in {
    val jsonProducer = new JsonCreator
    val dependencies = Map("com.thoughtworks.analysis.A" -> List("com.thoughtworks.analysis.B"))
    val json = jsonProducer.toJson(dependencies)

    val key: Option[JValue] = json find {
      case JField("target", _) => true
      case _ => false
    }

    key should be ('defined)
  }

  "JsonCreator" should "produce json that has two nodes given a class which has one dependency" in {
    val jsonProducer = new JsonCreator
    val dependencies = Map("com.thoughtworks.analysis.A" -> List("com.thoughtworks.analysis.B"))
    val json = jsonProducer.toJson(dependencies)

    val nodes: List[JValue] = json filter {
      case JField("name", _) => true
      case _ => false
    }

    nodes should have size(2)

    assert(nodes.contains(JField("name", JString("com.thoughtworks.analysis.A"))))
    assert(nodes.contains(JField("name", JString("com.thoughtworks.analysis.B"))))

  }

  "JsonCreator" should "produce json that has one link given a class which has one dependency" in {
    val jsonProducer = new JsonCreator
    val dependencies = Map("com.thoughtworks.analysis.A" -> List("com.thoughtworks.analysis.B"))
    val json = jsonProducer.toJson(dependencies)

    val links: List[JValue] = json filter {
      case JField("source", _) => true
      case _ => false
    }

    links should have size(1)
  }

  "JsonCreator" should "produce json with three nodes when one node has dependencies as well as is a dependency of some other node" in {
    val jsonProducer = new JsonCreator
    val dependencies = Map("com.thoughtworks.analysis.A" -> List("com.thoughtworks.analysis.B"),
                           "com.thoughtworks.analysis.C" -> List("com.thoughtworks.analysis.A"))
    val json = jsonProducer.toJson(dependencies)

    val nodes: List[JValue] = json filter {
      case JField("name", _) => true
      case _ => false
    }

    nodes should have size(3)

  }

  "JsonCreator" should "produce json with two links when one node has dependencies as well as is a dependency of some other node" in {
    val jsonProducer = new JsonCreator
    val dependencies = Map("com.thoughtworks.analysis.A" -> List("com.thoughtworks.analysis.B"),
                           "com.thoughtworks.analysis.C" -> List("com.thoughtworks.analysis.A"))
    val json = jsonProducer.toJson(dependencies)

    val links: List[JValue] = json filter {
      case JField("source", _) => true
      case _ => false
    }

    links should have size(2)

  }

  "Link" should "have correct source and target values" in {
    val jsonProducer = new JsonCreator
    val dependencies = Map("com.thoughtworks.analysis.A" -> List("com.thoughtworks.analysis.B"))
    val json = jsonProducer.toJson(dependencies)

    val source: List[JValue] = json filter {
      case JField("source", _) => true
      case _ => false
    }

    val target: List[JValue] = json filter {
      case JField("target", _) => true
      case _ => false
    }

    assert(source.contains(JField("source", JInt(1))))
    assert(target.contains(JField("target", JInt(0))))
  }

}
