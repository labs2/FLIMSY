package main.java;

import main.scala.ICalculatorClientProxy;
import main.scala.LookupClientProxy;

public class MainTest {
	public static void main(String[] args) throws InterruptedException {
		LookupClientProxy lookup = new LookupClientProxy();
		ICalculatorClientProxy clientProxy = (ICalculatorClientProxy) lookup.bind("Calculator");
		
		System.out.println(clientProxy.sum(10, 20));
		System.out.println(clientProxy.minus(10, 20));
		System.out.println(clientProxy.divide(20, 10));
		System.out.println(clientProxy.multiply(5, 2));
	}
}

