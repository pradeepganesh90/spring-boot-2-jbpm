package com.sb2;

public class HelloService {
	private static final HelloService INSTANCE = new HelloService();

	  public static HelloService getInstance() {

	    return INSTANCE;

	  }

	  public String sayHello(String name) {

	    return "Hello " + name;

	  }
}
