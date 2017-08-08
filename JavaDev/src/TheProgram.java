import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONException;
import org.json.JSONObject;

public class TheProgram {

	private final static String urlString = "https://oreo.wsecure.schneider-electric.com/files?p_Doc_Oid=4510969";
	private final static String filePath = "C:\\Users\\tis\\Downloads";
	private final static String fileName = "file_4510969.html";

	private final static String pathToExcelFile = "ressources\\SampleExcelDoc.xlsx";

	public static void main(String[] args) {
		/* This is supposed to be a program for calling / testing other tools. */

		int testNumber = 8;
		/*
		 * 1: testing download file. 2: testing if a string of a string array
		 * contains a value 3: testing to transform a json like string to a json
		 * string 4: 5: 6: Testing hashmap and string conversions...
		 * 
		 * 8: Testing xml schema validation
		 */

		switch (testNumber) {

		case 1:
			DownloadFile.downloadFile(filePath, fileName, urlString);
			break;

		case 2:
			String[] table = { "abc", "bcd", "cde" };
			String v1 = "bc";
			String v2 = "ac";
			String v3 = "bcd";
			System.out.println("Table = " + Arrays.toString(table));
			System.out.println("Result for " + v1 + " = "
					+ TheTools.doesTContain(table, v1));
			System.out.println("Result for " + v2 + " = "
					+ TheTools.doesTContain(table, v2));

			System.out
					.println("Now trying Arrays.asList contains (not exactly what I want)\n but could be useful with v3 !");
			System.out.println("Result for " + v1 + " = "
					+ Arrays.asList(table).contains(v1));
			System.out.println("Result for " + v2 + " = "
					+ Arrays.asList(table).contains(v2));
			System.out.println("Result for " + v3 + " = "
					+ Arrays.asList(table).contains(v3));

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

			String jls3 = "{docTypeMultiValued:{label=Documents Types,values={},hasMoreValues=false,rendering=null}, Status:{label=Status,values={},hasMoreValues=false,rendering=null}, range:{label=Range,values={},hasMoreValues=false,rendering=null}, Sources:{label=Sources,values={},hasMoreValues=false,rendering=null}}";

			System.out.println("JsonLikeString =\n" + jls + "\n");

			System.out.println("result of JLS as a well formed json String =\n"
					+ TheTools.jsonifyAString(jls, "=", ",", "\\{", "\\}",
							"\\[", "\\]"));

			System.out
					.println("result of JLS1 as a well formed json String =\n"
							+ TheTools.jsonifyAString(jls1, "=", ",", "\\{",
									"\\}", "\\[", "\\]"));

			System.out
					.println("result of JLS2 as a well formed json String =\n"
							+ TheTools.jsonifyAString(jls2, "=", ",", "\\{",
									"\\}", "\\[", "\\]"));

			System.out
					.println("result of JLS3 as a well formed json String =\n"
							+ TheTools.jsonifyAString(jls3, "=", ",", "\\{",
									"\\}", "\\[", "\\]"));

			JSONObject jsonObj = null;

			try {
				jsonObj = new JSONObject(TheTools.jsonifyAString(jls3, "=",
						",", "\\{", "\\}", "\\[", "\\]"));
				System.out.println(jsonObj.getJSONObject("values")
						.getJSONObject("Global").getString("isSelected"));

			} catch (JSONException e) {
				e.printStackTrace();
			}
			break;

		case 4:

			ExcelTools et = null;
			try {
				et = new ExcelTools(pathToExcelFile);
			} catch (EncryptedDocumentException | InvalidFormatException
					| IOException e) {
				e.printStackTrace();
			}
			String docRef1 = "DOC-5-EN";
			String docRef2 = "DOC-2-EN";
			if (null != et) {
				System.out.println("For " + docRef1 + " the origin is :"
						+ et.searchForValue(docRef1));
				System.out.println("For " + docRef2 + " the origin is :"
						+ et.searchForValue(docRef2));
			} else {
				System.out.println("et might be null !");
			}
			break;

		case 5:
			System.out.println(Funcij.sumax(4));
			break;

		case 6:
			String DEFAULT_SCOPE = "UNDEFINED";
			String COUNTRIES_LIST = "US,CA,MX,KZ";
			String SCOPES_LIST = "NAM,NAM,NAM,CIS";
			
			HashMap<String,String> hm = new HashMap<String,String>();
			String[] countries_list = COUNTRIES_LIST.split(",");
			String[] scopes_list = SCOPES_LIST.split(",");
			for(int i = 0; i<countries_list.length; i++){
				hm.put(countries_list[i], scopes_list[i]);
			}
			
			String country = "KZ";

			if(hm.containsKey(country)){
				System.out.println(hm.get(country));
			}
			else{
				System.out.println(DEFAULT_SCOPE);
			}
			
			break;
			
		case 7:
			String strToAL = "this,is,an,example";
			String sep = ",";
			System.out.println(TheTools.stringToArrayList(strToAL, sep).toString());
			break;
			
		case 8:
		      System.out.println("EmployeeRequest.xml validates against Employee.xsd? "+XMLValidation.validateXMLSchema("ressources/Employee.xsd", "ressources/EmployeeRequest.xml"));
		      System.out.println("EmployeeResponse.xml validates against Employee.xsd? "+XMLValidation.validateXMLSchema("ressources/Employee.xsd", "ressources/EmployeeResponse.xml"));
		      System.out.println("employee.xml validates against Employee.xsd? "+XMLValidation.validateXMLSchema("ressources/Employee.xsd", "ressources/employee.xml"));
		      
		      System.out.println("EmployeeNoRequest.xml validates against Employee.xsd? "+XMLValidation.validateXMLSchema("ressources/Employee.xsd", "ressources/EmployeeNoRequest.xml"));
		      
		      System.out.println("shiporder.xml validates against shiporder.xsd? "+XMLValidation.validateXMLSchema("ressources/shiporder.xsd", "ressources/shiporder.xml"));
		      System.out.println("nonExistantFile.xml validates against shiporder.xsd? "+XMLValidation.validateXMLSchema("ressources/shiporder.xsd", "ressources/nonExistantFile.xml"));
		      System.out.println("emptyFile.xml validates against shiporder.xsd? "+XMLValidation.validateXMLSchema("ressources/shiporder.xsd", "ressources/emptyFile.xml"));
			break;
			
		default:
			System.out.println("No test chosen, bye !");
			break;
		}

	}
}
