package com.salarycap.utilities;

public class JsonUtilities {
	/**
	 * Split an array of json object arrays by
	 * splitting the string on each closing "]," 
	 * @param str
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public static String[] splitJsonArray(String str) throws IndexOutOfBoundsException {
		return str.subSequence(1, str.length() - 2).toString().split("],");
	}

	/**
	 * Strip out [, ], ", and replace null with 0
	 * @param s
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public static String stripAndFormat(String s) throws IndexOutOfBoundsException {
		return s.replace("[", "").replace("]", "").replace("null", "0").replace("\"", "");
	}

	/**
	 * Given a string in key:value format, split on the first
	 * colon and return the first element in the resulting array
	 * which is the key
	 * @param json
	 * @return
	 */	
	public static String getKey(String json) throws IndexOutOfBoundsException {
		return json.split(":")[0];
	}
	
	/**
	 * Given a string in key:value format, split on the first
	 * colon and return everything after
	 * @param json
	 * @return
	 */
	public static String getValue(String json) throws IndexOutOfBoundsException{
		return json.substring(json.indexOf(":") + 1,
				json.length());
	}

	public static boolean isEmpty(String importType, String httpResponse) {
		Boolean validHttpResponse;
		switch (importType) {
		case "PlayerImporter":
			validHttpResponse = httpResponse.startsWith("{\"Notes\":\"\",\"Name\":\"\"}") ? true : false;
			break;
		case "ContractImporter":
			validHttpResponse = httpResponse.startsWith("{\"cap\":[],\"contract\":[],\"dead\":[]}") ? true : false;
			break;
		default:
			validHttpResponse = true;
			break;
		}
		return validHttpResponse;
	}		
}
