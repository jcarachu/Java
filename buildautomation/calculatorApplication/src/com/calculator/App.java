package com.calculator;


public class App {
	public static void main(String[] args){
		
		System.out.println(sum(10,5));
		System.out.println(subtraction(10,5));
		System.out.println(division(10,5));
		System.out.println(multiplication(10,5));

	}
	private static int sum(int a, int b)
	{
		return a + b;
	}
	private static int subtraction(int a, int b)
	{
		return a - b;
	}
	private static int division(int a, int b)
	{
		return a / b;
	}
	private static int multiplication(int a, int b)
	{
		return a * b;
	}
	
}
