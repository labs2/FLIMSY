package main.scala

import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream

class Marshaller extends IMarshaller {
  
  	def marshall(obj: Any): Array[Byte] = {
		var byteOutput: ByteArrayOutputStream = new ByteArrayOutputStream();
		var stream: ObjectOutputStream = new ObjectOutputStream(byteOutput);
		
		stream.writeObject(obj);
		stream.flush();
		stream.close();
		
		var result:Array[Byte] = byteOutput.toByteArray();
		byteOutput.close();
		result;
	}

	def unMarshall(byteArray: Array[Byte]): Any = {
		var byteInput: ByteArrayInputStream = new ByteArrayInputStream(byteArray);
		var stream: ObjectInputStream = new ObjectInputStream(byteInput);
		
		var result: Any = stream.readObject();
		stream.close();
		byteInput.close();
		result;
	}
}