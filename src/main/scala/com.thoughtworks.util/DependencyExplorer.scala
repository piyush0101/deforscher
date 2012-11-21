package com.thoughtworks.util

import java.util
import collection.mutable

class DependencyExplorer(classFinder: ClassFinder, dependencyFinder: DependencyFinder) {

  def findDependencies : mutable.HashMap[String, util.List[String]] = {
    var dependencies = new mutable.HashMap[String, util.List[String]]()
    val classes = classFinder.findClasses()

    classes.foreach((clazz: String) => dependencies += clazz -> dependencyFinder.findDependencies())

    dependencies
  }

}
