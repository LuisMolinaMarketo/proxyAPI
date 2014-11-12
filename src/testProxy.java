import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Properties;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class testProxy
{
	public static void testProxyOnly()
	{
		String url = "http://app.marketo.com/soap/mktows/2_4?WSDL",
			       proxy = "183.179.15.124",
			       port = "8080",
			       username = "",
			       password = "";
			URL server = null;
			HttpURLConnection connection = null;
			
			try {
				server = new URL(url);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Properties systemProperties = System.getProperties();
			systemProperties.setProperty("http.proxyHost",proxy);
			systemProperties.setProperty("http.proxyPort",port);
			try {
			connection = (
			    HttpURLConnection)server.openConnection();
				connection.connect();
				InputStream in = connection.getInputStream();
				System.out.println(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
			//readResponse(in);
	}
	public static URL getSOAPEndpointViaProxy(
			//String proxyAddress , 
			//int proxyPort, 
			//String SOAPMessageURL
			) 
	{
		String SOAPMessageURL = "http://app.marketo.com/soap/mktows/2_4?WSDL",
				proxyAddress = "112.17.0.211",
			       username = "",
			       password = "";
		int proxyPort = 80;

		Socket socket = new Socket();
	    boolean proxyReachable = false;
	    HttpURLConnection uc = null;
	    
	    URL url = null;
	
	    SocketAddress sockaddr = new InetSocketAddress(proxyAddress, proxyPort);
	
	    try {
	        socket.connect(sockaddr, 10000);
	        proxyReachable = true;
	    } catch (SocketTimeoutException e) {
	        proxyReachable = false;
	
	    } catch (IOException e) {
	        proxyReachable = false;
	    }
	    
	        
	    if (proxyReachable) {
	    	// Proxy is reachable, attempt to call URL
	        try {
	            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
	                    socket.getInetAddress(), proxyPort));
	
	            url = new URL(SOAPMessageURL);
	
	            uc = (HttpURLConnection) url
	                    .openConnection(proxy);
	            System.out.println("Content: " + uc.toString());
	            //String encoded = new String
	            //	      (Base64.encode(new String("username:password").getBytes()));
	            //	uc.setRequestProperty("Proxy-Authorization", "Basic " + encoded);
	            uc.connect();
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    try {
	        socket.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    
	    return(url);
	}
}