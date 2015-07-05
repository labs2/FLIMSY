package main.scala

class Request extends Serializable {
    var ip:String = "";
    var port:Int = 0;
  
	var remoteObjectName: String= "";
    var remoteObjectId: Int = -1;
    var remoteObjectOperationName: String= "";
    var remoteObjectArgumentList: Array[Any] = null;
}