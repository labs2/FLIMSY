package main.scala

object Invoker {
    var lookup: Lookup = null;
    var marshaller: IMarshaller = null;
    var response: Response = null;
    
	def execRemoteObject(data: Array[Byte]): Array[Byte] = { 
	    if (marshaller == null) {
	    	marshaller = new Marshaller();
	    }
	    
	    var response: Response = new Response();
	    var requestObject = castFromAnyToRequest(marshaller.unMarshall(data));
	    
		if (requestObject.remoteObjectName == "Lookup" && requestObject.remoteObjectId == 2) {
		    if (lookup == null) {
		    	lookup = new Lookup();
		    }
		  
			if (requestObject.remoteObjectOperationName == "register") {
			    lookup.register(requestObject);
			} else if (requestObject.remoteObjectOperationName == "binder") {
			    response = lookup.bind(requestObject.remoteObjectArgumentList(0).asInstanceOf[String]);
			}
		}
		marshaller.marshall(response);
	}
	
	def castFromAnyToRequest(obj: Any): Request = obj match {
		case obj: Request => obj
		case _ => throw new ClassCastException
	}
}