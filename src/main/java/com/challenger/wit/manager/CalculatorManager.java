package com.challenger.wit.manager;

import org.springframework.stereotype.Component;

@Component
public class CalculatorManager {
	
	public Integer sum(Integer a, Integer b)  {
		return a + b;
	}
	
	public Integer sub(Integer a, Integer b)  {
		return a - b;
	}
	
	public Integer mult(Integer a, Integer b)  {
		return a * b;
	}
	
	public Integer div(Integer a, Integer b)  {
		return a / b;
	}
}
