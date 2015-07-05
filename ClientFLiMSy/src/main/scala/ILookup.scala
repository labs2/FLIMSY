package main.scala

trait ILookup {
  
   def register(remoteObjectName: String, ip:String, port:Int);
   
   def bind(objName: String): Any;
}