import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;



public class ConnectWithSSL {
	
	private final static String keyStorePath = "ressources\\mykeystore.jks";
	private final static String keyStorePassword = "mypassword";
	private final static String keyStoreType = "JKS";
	private final static String urlString = "https://raw.githubusercontent.com/thami78/useful-stuff/master/README.md";
	
	/* Note : mykeystore.jks is just a dummy keystore not containing any information for the purpose of this example
	 * The password used for it is "mypassword"
	 * Don't forget to put it in a valid path !!
	 */
	
	// example inspired from : http://docs.oracle.com/javase/1.5.0/docs/guide/security/jsse/samples/urls/URLReader.java
	
	public static void main(String[] args) {
		System.out.println(connectViaSSL(keyStorePath, keyStorePassword, keyStoreType, urlString));
		
	}
	
	public static String connectViaSSL(String keyStorePath, String keyStorePassword, String keyStoreType, String urlString){
		
		System.setProperty("javax.net.ssl.keyStore",keyStorePath);
		System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
		System.setProperty("javax.net.ssl.keyStoreType", keyStoreType);
		
		String content = "";
		try {
			URL verisign = new URL(urlString);
			BufferedReader in = new BufferedReader(
						new InputStreamReader(
						verisign.openStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null){
//			    System.out.println(inputLine);
			    content += inputLine + "\n";
			}

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

}
