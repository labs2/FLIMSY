package main.scala

trait ICalculatorClientProxy {
  
  def sum(x: Int, y: Int): Int;
  
  def minus(x: Int, y: Int): Int;
  
  def divide(x: Int, y: Int): Int;
  
  def multiply(x: Int, y: Int): Int;

}