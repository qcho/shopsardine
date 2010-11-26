package main.java.shopsardine.embassy;

public class CatalogRequest extends Request {

	public static final String COMMON_URL = "http://eiffel.itba.edu.ar/hci/service/Catalog.groovy?method=";
	
	public CatalogRequest(String method) {
		this(method, null);
	}

	public CatalogRequest(String method, String tosend) {
		super(COMMON_URL + method, tosend);
	}
	
}