package main.scala

class EchoClientProxy() extends IEchoClientProxy {
    var request: Request = null;
    
    def createRequest(remoteObjectName:String, ip:String, port:Int) {
      request = new Request();
      //network informations
	  request.ip = ip; 
	  request.port = port;
	  
	  //remote object informations
	  request.remoteObjectId = 1;
	  request.remoteObjectName = remoteObjectName;
	  request.remoteObjectOperationName = "msg";
    }
  
	def msg(value: String): String = {
	  var requestor: Requestor = new Requestor();
	  
	  if (request == null) {
		  createRequest("Echo", "localhost", 9090);
	  }
	  
	  request.remoteObjectArgumentList = Array.ofDim[Any](1);
	  request.remoteObjectArgumentList(0) = value;
	  requestor.execRemoteObject(request).asInstanceOf[Response].result.asInstanceOf[String];
	}
}