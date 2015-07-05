package main.scala

import java.net.ServerSocket
import java.net.Socket

object StartServerFLiMSy {

  def main(args: Array[String]): Unit = {
    inicializeServerSocket(9090);
  }
  
  def inicializeServerSocket(port: Int) { 
    println("Start server socket ...");
    var lookup:ILookup = new LookupClientProxy();
    //lookup.register("Echo", "localhost", port);
    lookup.register("Calculator", "localhost", port);
    
	val serverSocket = new ServerSocket(port, 1000);
	
	while (true) {
	  Log.print("Waiting by client");
	  var connection: Socket = serverSocket.accept();
	  connection.setSoTimeout(0);
	  connection.setReuseAddress(true);
	  Log.print("Recieve a client");
	  var serverRequestHandler: ServerRequestHandler = new ServerRequestHandler();
	  serverRequestHandler.start
	  Log.print("Process a client");
	  serverRequestHandler ! ManagerSocket(connection)
	}
  }
}