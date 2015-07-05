package main.scala


class LookupClientProxy {
   def bind(remoteObjectName: String): Any = {
      var requestor: Requestor = new Requestor();
	  
	  var request: Request = new Request();
     
	  //network informations
	  request.ip = "localhost"; 
	  request.port = 9080;
	  
	  //remote object informations
	  request.remoteObjectId = 2;
	  request.remoteObjectName = "Lookup";
	  request.remoteObjectOperationName = "binder";
	  request.remoteObjectArgumentList = Array.ofDim[Any](1);
	  request.remoteObjectArgumentList(0) = remoteObjectName;
	  
	  var result:Array[Any] = requestor.execRemoteObject(request).asInstanceOf[Array[Any]];
	  
	  var clientProxy:Any = null;
	  if (remoteObjectName == "Echo") {
	      clientProxy = new EchoClientProxy();
	      clientProxy.asInstanceOf[EchoClientProxy].createRequest(result(0).asInstanceOf[String], result(1).asInstanceOf[String], result(2).asInstanceOf[Int]);
	  } else if (remoteObjectName == "Calculator") {
	      clientProxy = new CalculatorClientProxy();
	      clientProxy.asInstanceOf[CalculatorClientProxy].createRequest(result(0).asInstanceOf[String], result(1).asInstanceOf[String], result(2).asInstanceOf[Int]);
	  }
	  clientProxy;
   }
}