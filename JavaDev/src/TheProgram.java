import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;


public class TheProgram {
	
	private final static String urlString = "https://oreo.wsecure.schneider-electric.com/files?p_Doc_Oid=4510969";
	private final static String filePath = "C:\\Users\\tis\\Downloads";
	private final static String fileName = "file_4510969.html";
	
	public static void main(String[] args){
		System.out.println("This is supposed to be a program for calling / testing other tools.");
		
		int testNumber = 3;
		/*
		 * 1: testing download file.
		 * 2: testing if a string of a string array contains a value
		 * 3: testing to transform a json like string to a json string
		 */
		
		
		switch (testNumber) {
		
		case 1:
			DownloadFile.downloadFile(filePath, fileName, urlString);
			break;
			
		case 2:
			String [] table = {"abc", "bcd", "cde"};
			String v1 = "bc";
			String v2 = "ac";
			String v3 = "bcd";
			System.out.println("Table = "+ Arrays.toString(table));
			System.out.println("Result for "+ v1 +" = "+ TheTools.doesTContain(table, v1));
			System.out.println("Result for "+ v2 +" = "+ TheTools.doesTContain(table, v2));
			
			System.out.println("Now trying Arrays.asList contains (not exactly what I want)\n but could be useful with v3 !");
			System.out.println("Result for "+ v1 +" = "+ Arrays.asList(table).contains(v1));
			System.out.println("Result for "+ v2 +" = "+ Arrays.asList(table).contains(v2));
			System.out.println("Result for "+ v3 +" = "+ Arrays.asList(table).contains(v3));
			
			break;
			
		case 3:
			String jls = "{Global={label=Global, value=0@Global/, isSelected=true, excluded=null, count=325576, hasChildren=true, "
					+ "isParent=false}, Local={label=Local, value=[fiest=0@Local/, isSelected=false], excluded=null, count=3265,"
					+ " hasChildren=true, isParent=false}}";
			
			String jls1 = "{label=Sources, values={Global={label=Global, value=0@Global/, isSelected=false, "
					+ "excluded=null, count=325576, hasChildren=true, isParent=false}, Local={label=Local, value=0@Local/, "
					+ "isSelected=false, excluded=null, count=3265, hasChildren=true, isParent=false}}, "
					+ "hasMoreValues=false, rendering=null}";
			
			String jls2 = "{label=Sources,?values={Global={label=Global,?value=0@Global/,?isSelected=false,?excluded=null,?count=325576,?hasChildren=true,?isParent=false},?Local={label=Local,?value=0@Local/,?isSelected=false,?excluded=null,?count=3265,?hasChildren=true,?isParent=false}},?hasMoreValues=false,?rendering=null}";
			System.out.println("JsonLikeString =\n" + jls + "\n");
			
			System.out.println("result of JLS as a well formed json String =\n" + TheTools.jsonifyAString(jls, "=", ",", "\\{", "\\}", "\\[", "\\]"));
			
			System.out.println("result of JLS1 as a well formed json String =\n" + TheTools.jsonifyAString(jls1, "=", ",", "\\{", "\\}", "\\[", "\\]"));
			
			System.out.println("result of JLS2 as a well formed json String =\n" + TheTools.jsonifyAString(jls2, "=", ",", "\\{", "\\}", "\\[", "\\]"));
			
			JSONObject jsonObj = null;
			
			try {
				jsonObj = new JSONObject(TheTools.jsonifyAString(jls2, "=", ",", "\\{", "\\}", "\\[", "\\]"));
				System.out.println(jsonObj.getJSONObject("values").getJSONObject("Global").getString("isSelected"));
				
			} catch (JSONException e) {
				e.printStackTrace();
			}

			
			break;
		default:
			System.out.println("No test chosen, bye !");
			break;
		}
		
		
		
		
	}
}
