import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.sun.xml.ws.util.ByteArrayBuffer;

@SuppressWarnings({"unused"})
public class DownloadFile {
	
	private final static String urlString = "https://raw.githubusercontent.com/thami78/useful-stuff/master/README.md";
	private final static String filePath = "C:\\Users\\tis\\Downloads";
	private final static String fileName = "README.md";
	private final static String fileSeparator = "\\";
	private static final int BUFFER_SIZE = 15360;
	private static final int MAX_FILE_SIZE = 2048000;
	
	
	public static void main(String[] args) {

		
		int choice = 2;
		
		switch (choice) {
			case 1:
				// Example of using donwloadFile
				downloadFile(filePath, fileName, urlString);
				break;
				
			case 2:
				// Download file and getting it as bytes and printing it
				byte[] fileAsByteArray = downloadFileAsBytes(urlString);
				break;
	
			default:
				
				System.out.println("No function chosed !");
				break;
		}
	}
	
	public static void downloadFile(String filePath, String fileName, String urlString){
		InputStream is = null;
		OutputStream os = null;
		URL url;
		HttpURLConnection connection = null;
		
		try {
			url = new URL(urlString);
			connection = (HttpURLConnection) url.openConnection();
			int response = connection.getResponseCode();
			
			if(response == HttpURLConnection.HTTP_OK){
				is = connection.getInputStream();
				os = new FileOutputStream(filePath + fileSeparator + fileName);
				
				int bytesRead = -1;
				byte[] buffer = new byte[BUFFER_SIZE];
				while((bytesRead = is.read(buffer)) != -1){
					os.write(buffer, 0, bytesRead);
				}
				
				System.out.println("File downloaded and saved to " + filePath + fileSeparator + fileName);
			}else{
				System.out.println("No file downloaded. Response code : " + response);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				os.close();
				is.close();
				connection.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static byte[] downloadFileAsBytes(String urlString){
		InputStream is = null;
		URL url;
		HttpURLConnection connection = null;
		byte[] buffer = new byte[BUFFER_SIZE];
		byte[] fileContentAsBytes = new byte[MAX_FILE_SIZE];
		ByteArrayBuffer bab = new ByteArrayBuffer(fileContentAsBytes);
		
		try {
			url = new URL(urlString);
			connection = (HttpURLConnection) url.openConnection();
			int response = connection.getResponseCode();
			
			if(response == HttpURLConnection.HTTP_OK){
				is = connection.getInputStream();
				
				
				int bytesRead = -1;
				
				while((bytesRead = is.read(buffer)) != -1){
					bab.write(buffer, 0, bytesRead);
				}
				
				System.out.println("Here's the content of fileContentAsBytes :" + fileContentAsBytes.toString());
			}else{
				System.out.println("No file downloaded. Response code : " + response);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				is.close();
				bab.close();
				connection.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return fileContentAsBytes;
	}
}
