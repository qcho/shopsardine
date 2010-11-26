package main.java.shopsardine.embassy;

public class CommonRequest extends Request {

	public static final String COMMON_URL = "http://eiffel.itba.edu.ar/hci/service/Common.groovy?method=";
	
	public CommonRequest(String method) {
		this(method, null);
	}

	public CommonRequest(String method, String tosend) {
		super(COMMON_URL + method, tosend);
	}
	
}
