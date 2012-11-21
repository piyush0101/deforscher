package com.thoughtworks.util.json

import net.liftweb.json._
import net.liftweb.json.JsonDSL._
import collection.mutable
import java.util.Map.Entry
import java.util
import scala.collection.immutable._

class JsonCreator {

  case class Node(name: String, group: Int)

  case class Link(source: Int, target: Int, value: Int)

  def toJson(dependencies: Map[String, List[String]]) {

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

  private def extractLinks(dependencies: Map[String, List[String]]): Iterable[Link] = {
    for (i <- dependencies; j <- i._2) yield Link(i._1.length, j.length, 0);
  }

  private def extractNodes(dependencies: Map[String, List[String]]): Iterable[Node] = {
    val nodes = dependencies.map(n => Node(n._1, 1))
    nodes
  }

}
