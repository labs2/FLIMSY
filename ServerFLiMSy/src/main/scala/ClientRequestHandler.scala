package main.scala

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.net.Socket
import java.io.ByteArrayOutputStream

class ClientRequestHandler {
  
	def execRemoteObject(data: Array[Byte], socket: Socket): Array[Byte] = {
	    var is:BufferedInputStream = new BufferedInputStream(socket.getInputStream());
        var os:BufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
	  
		sendData(data, os);
		
		var result: Array[Byte] = recieveDataFromServer(is);
		closeConnection(socket, is, os);
		
		result;
	}
	
	def sendData(data: Array[Byte], os:BufferedOutputStream) {
      os.write(data, 0, data.length);
      os.flush();
	}
	
	def recieveDataFromServer(is:BufferedInputStream): Array[Byte] = {
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
	
	def closeConnection(socket: Socket, is:BufferedInputStream, os:BufferedOutputStream) {
	  if (is != null) {
		  is.close();
	  }
	  
	  if (os != null) {
		  os.close();
	  }
	  
      if (socket != null) {
    	  socket.close();
      }
	}
}