package main.java.shopsardine.tools;

public abstract class ObjectAction<T> implements Runnable {

	public T object;
	
	public ObjectAction(T o) {
		object = o;
	}
	
}
