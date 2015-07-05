package main.scala

import java.net.ServerSocket
import java.net.Socket

object StartLookupFLiMSy {

  def main(args: Array[String]): Unit = {
    inicializeServerSocket(9080);
  }
  
  def inicializeServerSocket(port: Int) {
    println("Start lookup socket ...");
	val serverSocket = new ServerSocket(port, 1000);
	
	while (true) {
	  Log.print("Waiting by server");
	  var connection: Socket = serverSocket.accept();
	  connection.setSoTimeout(0);
	  connection.setReuseAddress(true);
	  Log.print("Recieve a server");
	  var serverRequestHandler: ServerRequestHandler = new ServerRequestHandler();
	  serverRequestHandler.start
	  Log.print("Process a server");
	  serverRequestHandler ! ManagerSocket(connection)
	}
  }

}