package main.scala

class CalculatorClientProxy extends ICalculatorClientProxy {
  var request: Request = null;

  def sum(x: Int, y: Int): Int = {
    var requestor: Requestor = new Requestor();
    if (request == null) {
      createRequest("Calculator", "localhost", 9090);
    }

    request.remoteObjectArgumentList = Array.ofDim[Any](2);
    request.remoteObjectArgumentList(0) = x;
    request.remoteObjectArgumentList(1) = y;
    request.remoteObjectOperationName = "sum";

    requestor.execRemoteObject(request).asInstanceOf[Int];
  }

  def minus(x: Int, y: Int): Int = {
    var requestor: Requestor = new Requestor();
    if (request == null) {
      createRequest("Calculator", "localhost", 9090);
    }

    request.remoteObjectArgumentList = Array.ofDim[Any](2);
    request.remoteObjectArgumentList(0) = x;
    request.remoteObjectArgumentList(1) = y;
    request.remoteObjectOperationName = "minus";

    requestor.execRemoteObject(request).asInstanceOf[Int];
  }

  def divide(x: Int, y: Int): Int = {
    var requestor: Requestor = new Requestor();
    if (request == null) {
      createRequest("Calculator", "localhost", 9090);
    }

    request.remoteObjectArgumentList = Array.ofDim[Any](2);
    request.remoteObjectArgumentList(0) = x;
    request.remoteObjectArgumentList(1) = y;
    request.remoteObjectOperationName = "divide";

    requestor.execRemoteObject(request).asInstanceOf[Int];
  }

  def multiply(x: Int, y: Int): Int = {
    var requestor: Requestor = new Requestor();
    if (request == null) {
      createRequest("Calculator", "localhost", 9090);
    }

    request.remoteObjectArgumentList = Array.ofDim[Any](2);
    request.remoteObjectArgumentList(0) = x;
    request.remoteObjectArgumentList(1) = y;
    request.remoteObjectOperationName = "multiply";

    requestor.execRemoteObject(request).asInstanceOf[Int];
  }

  def createRequest(remoteObjectName: String, ip: String, port: Int) {
    request = new Request();
    //network informations
    request.ip = ip;
    request.port = port;

    //remote object informations
    request.remoteObjectId = 2;
    request.remoteObjectName = remoteObjectName;
  }

}