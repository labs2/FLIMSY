package main.scala

trait IMarshaller {
  
  	def marshall(obj: Any): Array[Byte];

	def unMarshall(byteArray: Array[Byte]): Any;

}