package com.thoughtworks.util.json

import net.liftweb.json._
import net.liftweb.json.JsonDSL._
import collection.mutable
import java.util.Map.Entry
import java.util
import scala.collection.immutable._
import net.liftweb.json.JsonAST.JObject
import scala.collection
import scala.collection

class JsonCreator {

  case class Node(name: String, group: Int)

  case class Link(source: Int, target: Int, value: Int)

  def toJson(dependencies: Map[String, List[String]]) : JObject = {

    val nodes = extractNodes(dependencies)
    val links = extractLinks(dependencies, nodes.toList)

    val json: JObject =
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

  private def extractLinks(dependencies: Map[String, List[String]], nodes: List[Node]): Iterable[Link] = {
    for (i <- dependencies; j <- i._2) yield Link(nodes.indexOf(i._1), nodes.indexOf(j), 0)
  }

  private def extractNodes(dependencies: Map[String, List[String]]): List[Node] = {
    val nodes = dependencies.map(n => Node(n._1, 1))
    var moreNodes = new mutable.LinkedList[Node]
    dependencies.values.foreach((l: List[String]) =>
      l.foreach((d: String) =>
        if (!findNode(nodes, d))
          moreNodes = moreNodes :+ Node(d, 1)))
    nodes.foreach((n: Node) => moreNodes = moreNodes :+ n)
    moreNodes.toList
  }

  private def findNode(nodes: Iterable[Node], name: String) : Boolean = {
      nodes.find((n: Node) => n.name.equals(name)) match {
        case Some(n) => true
        case None => false
      }
  }

}
