package com.thoughtworks.util.json

import net.liftweb.json._
import net.liftweb.json.JsonDSL._
import collection.mutable
import java.util.Map.Entry
import java.util

class DependenciesJson {

  case class Node(name: String, group: Int)

  case class Link(source: Int, target: Int, value: Int)

  def toJson(dependencies: java.util.HashMap[String, List[String]]) {

    val nodes = extractNodes(dependencies)
    val links = extractLinks(dependencies)

    val json =
      (("nodes" -> nodes.map {
        n: Node =>
          (("name" -> n.name) ~
            ("group" -> n.group))
      }) ~

        ("links" -> links.map {
          l: Link =>
            ("source" -> l.source) ~
              ("target" -> l.target) ~
              ("value" -> l.value)
        }))

    json
  }


  def extractLinks(dependencies: util.HashMap[String, List[String]]): Any = {
    dependencies.entrySet().toArray()[String].map((e: Entry) => (java.util.List(e.getValue)))
  }

  def extractNodes(dependencies: util.HashMap[String, List[String]]): Any = {
    dependencies.keySet().toArray[String]().map((n: String) => Node(n, 1))
  }

}
