package main.scala

object Log {
  val debugEnable:Boolean = false;
  
  def print(msg:String) {
    if (debugEnable) {
    	println(msg);  
    }  
  }

}