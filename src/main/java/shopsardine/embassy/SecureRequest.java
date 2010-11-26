package main.java.shopsardine.embassy;

public class SecureRequest extends Request {
	
	public static final String SECURE_URL = "http://eiffel.itba.edu.ar/hci/service/Security.groovy?method=";
	
	public SecureRequest(String method) {
		this(method, null);
	}

	public SecureRequest(String method, String tosend) {
		super(SECURE_URL + method, tosend);
	}
	
}
