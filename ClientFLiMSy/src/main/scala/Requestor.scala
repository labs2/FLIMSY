package main.scala

import java.net.InetAddress
import java.net.Socket

class Requestor {
  
	def execRemoteObject(request: Request): Any = {
		var marshaller: IMarshaller = new Marshaller();
		var clientRequestHandler: ClientRequestHandler = new ClientRequestHandler();
		
		var inetAddress:InetAddress = InetAddress.getByName(request.ip);
		var socket: Socket = new Socket(inetAddress, request.port);
		
		var data: Array[Byte] = marshaller.marshall(request);
		
	    var result: Array[Byte] = clientRequestHandler.execRemoteObject(data, socket);
	    
	    var response:Response = castFromAnyToResponse(marshaller.unMarshall(result));
	    
	    response.result;
	}
	
	def castFromAnyToResponse(obj: Any): Response = obj match {
		case obj: Response => obj
		case _ => throw new ClassCastException
	}
	
}