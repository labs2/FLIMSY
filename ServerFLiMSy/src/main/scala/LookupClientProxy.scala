package main.scala


class LookupClientProxy extends ILookup {
  
   def register(remoteObjectName: String, ip:String, port:Int) {
      var requestor: Requestor = new Requestor();
	  
	  var request: Request = new Request();
     
	  //network informations
	  request.ip = "localhost"; 
	  request.port = 9080;
	  
	  //remote object informations
	  request.remoteObjectId = 2;
	  request.remoteObjectName = "Lookup";
	  request.remoteObjectOperationName = "register";
	  request.remoteObjectArgumentList = Array.ofDim[Any](3);
	  request.remoteObjectArgumentList(0) = remoteObjectName;
	  request.remoteObjectArgumentList(1) = ip;
	  request.remoteObjectArgumentList(2) = port;
	  
	  requestor.execRemoteObject(request);
   }
   
   def bind(remoteObjectName: String): IEchoClientProxy = {
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
	  
	  var clientProxy: EchoClientProxy = new EchoClientProxy();
	  clientProxy.createRequest(result(0).asInstanceOf[String], result(1).asInstanceOf[String], result(2).asInstanceOf[Int]);
	  clientProxy;
   }
   
   def castFromAnyToRequest(obj: Any): Response = obj match {
		case obj: Response => obj
		case _ => throw new ClassCastException
   }
}