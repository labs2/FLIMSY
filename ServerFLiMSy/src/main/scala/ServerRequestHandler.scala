package main.scala

import scala.actors._
import java.net.Socket
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream

case class ManagerSocket(socket: Socket)

class ServerRequestHandler extends Actor {
  
	def callInvoker(data: Array[Byte]) : Array[Byte] = {
		Invoker.execRemoteObject(data);
	}
	
	@Override
	def act() {
		while(true) {
			receive {
				case ManagerSocket(socket) =>
					processRequestFromClient(socket)
			}
		}
	}
	
	def processRequestFromClient(socket: Socket) {
		var is: BufferedInputStream = new BufferedInputStream(socket.getInputStream());
		var os: BufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
		
		var data: Array[Byte] = callInvoker(readRequestFromClient(is));
	    writeResponseToClient(data, os);
	    closeConnection(socket, is, os);
	}
	
	def readRequestFromClient(is: BufferedInputStream) : Array[Byte] = {
		var byteOutput: ByteArrayOutputStream = new ByteArrayOutputStream();
	    var buffer: Array[Byte] = Array.ofDim[Byte](4096);
	
	    var len: Int = is.read(buffer);
	    while (len > 0) {
	    	byteOutput.write(buffer);
	    	if (is.available() > 0) {
	    		len = is.read(buffer);
	    	} else {
	            len = 0;
	        }
	    }
	    
	    var resultData: Array[Byte] = byteOutput.toByteArray();
	    byteOutput.close();
	    resultData;
	}
	
	def writeResponseToClient(data: Array[Byte], os: BufferedOutputStream) {
	  Thread.sleep(10);
	  os.write(data, 0, data.length);
          os.flush();
	}
	
	def closeConnection(socket: Socket, is: BufferedInputStream, os: BufferedOutputStream) {
	  if (is != null) {
		  is.close();
	  }
	  
	  if (os != null) {
		  os.close();
	  }
	  
      if (socket != null) {
    	  socket.close();
      }
      
      this.exit()
	}
}
