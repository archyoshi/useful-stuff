
public class TheProgram {
	
	private final static String urlString = "https://oreo.wsecure.schneider-electric.com/files?p_Doc_Oid=4510969";
	private final static String filePath = "C:\\Users\\tis\\Downloads";
	private final static String fileName = "file_4510969.html";
	
	public static void main(String[] args){
		System.out.println("This is supposed to be a program for calling / testing other tools.");
		
		DownloadFile.downloadFile(filePath, fileName, urlString);
		
	}
}
