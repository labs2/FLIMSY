package main.scala

import scala.collection.mutable._

class Lookup {
  def register(request: Request) {
      var remoteObjectName: String = request.remoteObjectArgumentList(0).asInstanceOf[String];
      if (!LookupTableList.lookupTable.contains(remoteObjectName)) {
        LookupTableList.lookupTable.put(remoteObjectName, request.remoteObjectArgumentList);
      }
  }

  def bind(remoteObjectName: String): Response = {
      var response: Response = new Response();
      response.result  = LookupTableList.lookupTable.get(remoteObjectName).get;
      response
  }
  
  object LookupTableList {
    val lookupTable = new HashMap[String, Array[Any]] with SynchronizedMap[String, Array[Any]]
  }
}

