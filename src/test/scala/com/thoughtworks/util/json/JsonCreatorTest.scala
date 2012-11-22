package com.thoughtworks.util.json

import org.scalatest.{FlatSpec, FunSuite}
import net.liftweb.json._
import net.liftweb.json.JsonDSL._
import net.liftweb.json.JsonAST._
import net.liftweb.json.JsonAST.{JString, JValue, JField}
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

}
