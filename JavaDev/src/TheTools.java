import java.util.ArrayList;

//import org.json.JSONObject;

public class TheTools {
	
	public static boolean doesTContain(String [] T, String value){
		boolean result = false;
			if(null!=T){
				for(String s : T){
					if(s.contains(value)){
						result=true;
					}
				}
			}
		return result;
	}
	
	public static String jsonifyAStringWithoutArrays(String jsonLikeString, String assignmentOp, String separator, String objectOpeningDelimiter, String objectClosingDelimiter, String arrayOpeningDelimiter, String arrayClosingDelimiter){
		
		String jsonString = jsonLikeString;
		
		
		jsonString = jsonString.replaceAll(assignmentOp, "\":\"");
		jsonString = jsonString.replaceAll("\""+objectOpeningDelimiter, "{");
		jsonString = jsonString.replaceAll("\""+arrayOpeningDelimiter, "[");
		jsonString = jsonString.replaceAll(objectOpeningDelimiter, "{\"");
		jsonString = jsonString.replaceAll(objectClosingDelimiter, "\"}");
		jsonString = jsonString.replaceAll(separator+" ", ", \"");
		jsonString = jsonString.replaceAll(arrayOpeningDelimiter, "[\"");
		jsonString = jsonString.replaceAll(arrayClosingDelimiter, "\"]");
		jsonString = jsonString.replaceAll(separator, "\",");
		jsonString = jsonString.replaceAll(objectClosingDelimiter+"\"", "}");
		jsonString = jsonString.replaceAll(arrayClosingDelimiter+"\"", "]");
		jsonString = jsonString.replaceAll(assignmentOp, "\":");
		
		
		return jsonString;
	}
	
	public static String jsonifyAString(String jsonLikeString, String assignmentOp, String separator, String objectOpeningDelimiter, String objectClosingDelimiter, String arrayOpeningDelimiter, String arrayClosingDelimiter){
		
		String jsonString = jsonLikeString;
		
		String finalJsonString = "";
		String temp = "";
		
		jsonString = jsonString.replaceAll(assignmentOp, "\":\"");
		jsonString = jsonString.replaceAll("\""+objectOpeningDelimiter, "{");
		jsonString = jsonString.replaceAll("\""+arrayOpeningDelimiter, "[");
		jsonString = jsonString.replaceAll(objectOpeningDelimiter, "{\"");
		jsonString = jsonString.replaceAll(objectClosingDelimiter, "\"}");
		jsonString = jsonString.replaceAll(separator, ", \"");
//		jsonString = jsonString.replaceAll(separator+" ", ", \"");
		jsonString = jsonString.replaceAll(arrayOpeningDelimiter, "[\"");
		jsonString = jsonString.replaceAll(arrayClosingDelimiter, "\"]");
		jsonString = jsonString.replaceAll(separator, "\",");
		jsonString = jsonString.replaceAll(objectClosingDelimiter+"\"", "}");
		jsonString = jsonString.replaceAll(arrayClosingDelimiter+"\"", "]");
		jsonString = jsonString.replaceAll(assignmentOp, "\":");
		
		finalJsonString = jsonString;
		
		while(jsonString.indexOf("[") != -1){
			finalJsonString = jsonString.substring(0, jsonString.indexOf("["));
			jsonString = jsonString.substring(jsonString.indexOf("["));
			temp = jsonString.substring(0, jsonString.indexOf("]") + 1);
			temp = temp.replaceAll("\\[", "[{");
			temp = temp.replaceAll("\\]", "}]");
			temp = temp.replaceAll(",", "}, {");
			temp = temp.replaceAll("\\{ \"", "{\"");
			finalJsonString += temp;
			finalJsonString += jsonString.substring(jsonString.indexOf("]") + 1);
			jsonString = jsonString.substring(jsonString.indexOf("]"));
		}
		
		return finalJsonString;
	}
	
	public static ArrayList<String> stringToArrayList(String stringToConvert, String separator){
		String temp [] = stringToConvert.split(separator);
		ArrayList<String> al = new ArrayList<String>();
		for(String s : temp){
			al.add(s);
		}
		return al;
	}

}
