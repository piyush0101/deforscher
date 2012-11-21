package com.thoughtworks.util

import java.io.File
import java.net.URL
import collection.mutable

class ClassFinder(packageName: String) {

  def findClasses(): mutable.LinkedList[String] = {
    val classLoader = getClassLoader
    val resources = getResources(classLoader)
    val files = getFiles(resources)
    val classes = getClasses(files)

    classes
  }

  private def getClasses(files: mutable.LinkedList[String]): mutable.LinkedList[String] = {
      var filesList = new mutable.LinkedList[String]()
      files.foreach((f:String) => {
        new File(f).list().foreach((c: String) => filesList = filesList :+ c)
      })
      filesList
  }

  def getFiles(resources: java.util.Enumeration[URL]): mutable.LinkedList[String] = {
    var dirs = new mutable.LinkedList[String]
    while (resources.hasMoreElements) {
      val resource = resources.nextElement()
      dirs = dirs :+ resource.getFile
    }
    dirs
  }

  private def getClassLoader: java.lang.ClassLoader = {
    Thread.currentThread().getContextClassLoader
  }

  private def getResources(classLoader: java.lang.ClassLoader): java.util.Enumeration[java.net.URL] = {
    classLoader.getResources(packageName.replace('.', '/'))
  }
}