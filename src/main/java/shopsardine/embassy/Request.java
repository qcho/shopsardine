package main.java.shopsardine.embassy;

import java.io.*;
import java.net.*;

import javax.xml.parsers.DocumentBuilderFactory;

public class Request {

    /* This class can be used to generate any kind of request to the HCI API.
     * Request classes should subclass this one.
     */
	
	private Thread thread;
	public String response;
    
	private static RequestErrback defaulterr;
	
	static {
		defaulterr =
		new RequestErrback() {
			public void handle(Exception e) {
				System.out.println("Error in request.");
				e.printStackTrace();
			}
		};
	}
    
    private URLConnection _connection;
    private String _urlstr;
    private String _send;
    
    /* Constructors: */
    public Request(String method) {
        this(method, null);
    }
    
    public Request(String urlstr, String tosend) {
        
		this._urlstr = urlstr;
        
        if (tosend != null)
            this._send = new String(tosend);
        
    }
    
    /* Accessors: */
    public String url() {
        return this._urlstr;
    }
    
    public URLConnection connection() {
        return this._connection;
    }
    
    /* Other methods: */
    public void make(RequestCallback rc) {
    	make(rc, null, true);
    }
    
    public void make(RequestCallback rc, RequestErrback ec) {
    	make(rc, ec, true);
    }
    
    public void makeSync(RequestCallback rc) {
    	make(rc, null, false);
    }
    
    public void makeSync(RequestCallback rc, RequestErrback ec) {
    	make(rc, ec, false);
    }
    
    public void make(final RequestCallback rc, final RequestErrback ec, boolean async) {
    
    	if (async) {
	    	thread = new Thread (
	    		new Runnable() {
	    			public void run() {
	    				domake(rc, ec);
	    			}
	    		}
	    	);
	    	
	    	thread.start();
	    	
    	} else
    		domake(rc, ec);
    	
    }
    
    private void domake(RequestCallback rc, RequestErrback ec) {
    
		if (ec == null)
			ec = defaulterr;
    	
    	URL url;
		
		try {
			url = new URL(_urlstr);
			
		} catch (MalformedURLException e) {
			ec.handle(e);
			return;
		}

    	try {
	        _connection = url.openConnection();
	        _connection.setDoOutput(true);
	        
	        if (_send != null) {
	            OutputStreamWriter writer = new OutputStreamWriter(_connection.getOutputStream());
	            writer.write(_send);
	            writer.close();
	        }
	        
	        
	        BufferedReader reader = new BufferedReader(
	                                    new InputStreamReader(_connection.getInputStream())
	                                );
	        
	        String line;
	        StringBuffer buffer = new StringBuffer();
	
	        while ((line = reader.readLine()) != null) {
	        	buffer.append(line);
	        	buffer.append("\n");
	        }
	       
	        reader.close();

	        this.response = buffer.toString();
	        
	        rc.handle(DocumentBuilderFactory.newInstance()
	        		  .newDocumentBuilder()
	        		  .parse(new ByteArrayInputStream(this.response.getBytes("UTF-8")))
	        		  );

	        /*synchronized(this) {
	        	notifyAll();
	        }*/
	        
    	} catch (Exception e) {
    		ec.handle(e);
    		return;
    	}
        
    }
    
}
