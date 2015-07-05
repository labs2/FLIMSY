package main.scala

object Invoker {
    var echo: Echo = null;
    var calculator: Calculator = null;
    var marshaller: IMarshaller = null;
    var response: Response = null;
    
	def execRemoteObject(data: Array[Byte]): Array[Byte] = { 
	    if (marshaller == null) {
	    	marshaller = new Marshaller();
	    }
	  
	    var requestObject = castFromAnyToRequest(marshaller.unMarshall(data));
	    
		if (requestObject.remoteObjectName == "Echo" && requestObject.remoteObjectId == 1) {
		    if (echo == null) {
		    	echo = new Echo();
		    }
		  
			if (requestObject.remoteObjectOperationName == "msg") {
				 if (response == null) {
					 response = new Response();
				 }
			     response.result = echo.msg(requestObject.remoteObjectArgumentList(0).asInstanceOf[String]);
			}
		} else if (requestObject.remoteObjectName == "Calculator" && requestObject.remoteObjectId == 2) {
		    if (calculator  == null) {
		    	calculator  = new Calculator();
		    }
		    
		    if (response == null) {
		    	response = new Response();
		    }
		    
		    var x:Int = requestObject.remoteObjectArgumentList(0).asInstanceOf[Int];
		    var y:Int = requestObject.remoteObjectArgumentList(1).asInstanceOf[Int];
		    
		    if (requestObject.remoteObjectOperationName == "sum") {
		        response.result = calculator.sum(x, y);
		    } else if (requestObject.remoteObjectOperationName == "minus") {
		        response.result = calculator.minus(x, y);
		    } else if (requestObject.remoteObjectOperationName == "divide") {
		        response.result = calculator.divide(x, y);
		    } else if (requestObject.remoteObjectOperationName == "multiply") {
		        response.result = calculator.multiply(x, y);
		    }
		}
		marshaller.marshall(response);
	}
	
	def castFromAnyToRequest(obj: Any): Request = obj match {
		case obj: Request => obj
		case _ => throw new ClassCastException
	}
}