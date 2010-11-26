package main.java.shopsardine.tools;

public abstract class ObjectAction implements Runnable {

	public Object object;
	
	public ObjectAction(Object o) {
		object = o;
	}
	
}
