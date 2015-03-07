package com.salarycap.importers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;

import com.lance.salarycap.service.ContractOverviewService;
import com.lance.salarycap.service.DeadMoneyService;
import com.lance.salarycap.service.YearlyContractService;
import com.salarycap.annotations.Name;
import com.salarycap.models.ContractOverview;
import com.salarycap.models.DeadMoney;
import com.salarycap.models.YearlyContract;
import com.salarycap.utilities.Constants;
import com.salarycap.utilities.JsonUtilities;

public class ContractImporter implements Importer {
	
	private ContractOverviewService contractOverviewService;
	private YearlyContractService yearlyContractService;
	private DeadMoneyService deadMoneyService;
	private static Integer importSuccessCount = 0;
	private static Integer importFailureCount = 0;
	private static final Logger logger = Logger.getLogger(ContractImporter.class);

	public ContractImporter(ContractOverviewService contractOverviewService, 
			YearlyContractService yearlyContractService, DeadMoneyService deadMoneyService) {
		this.contractOverviewService = contractOverviewService;
		this.yearlyContractService = yearlyContractService;
		this.deadMoneyService = deadMoneyService;
	}

	@Override
	public void doImport() {
		logger.info("\r\nContract import starting at " + new LocalDateTime().toString()
				+ "\r\n" + Constants.DASHED_LINE);
		System.out.println("\r\nImporting Contract Objects...");
		for(int i = Constants.STARTING_IMPORT_INDEX; i <=Constants.ENDING_IMPORT_INDEX; i++){
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost post = new HttpPost(Constants.OVERTHECAP_BASE_URL);
			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("type", "contract"));
			params.add(new BasicNameValuePair("player_id", String.valueOf(String.valueOf(i))));
			CloseableHttpResponse response = null;
			Scanner in = null;
			try {
				post.setEntity(new UrlEncodedFormEntity(params));
				response = httpClient.execute(post);
				HttpEntity entity = response.getEntity();
				StringBuilder sb = new StringBuilder();
				in = new Scanner(entity.getContent());
				while (in.hasNext()) {
					sb.append(in.next() + " ");
				}
				EntityUtils.consume(entity);
				// Split into the three objects (cap, contracts, dead).
				// Note: each object has capacity to be an array itself		
				if(!JsonUtilities.isEmpty("ContractImporter", sb.toString())){
					String[] objects = JsonUtilities.splitJsonArray(sb.toString());
					List<String> formattedObjects = new ArrayList<>();
					for (String s : objects) {
						formattedObjects.add(JsonUtilities.stripAndFormat(s));
					}
					// Iterate over each object (cap, contract, dead)
					for (String json : formattedObjects) {
						//Get a string representation of the object type
						String key = JsonUtilities.getKey(json);
						String value = null;
						try {
							//Separate value of object from name (key)
							value = JsonUtilities.getValue(json);
						} catch (IndexOutOfBoundsException e) {
							logger.warn(e.getMessage());
						}
						// Qualifier (object.length() > 2)
						//Determine (by key/objectType) which class to use
						if(value.length() > 2){
							switch (key) {
								case "cap":
									importObject(YearlyContract.class, value);
									break;
								case "contract":
									importObject(ContractOverview.class, value);
									break;
								case "dead":
									importObject(DeadMoney.class, value);
									break;
								default:
									logger.error("Error has occurred. Contract import reached "
											+ "default block of switch statement.");
									break;
							}
						}
					}
					importSuccessCount++;
				}
				else{
	            	importFailureCount++;
	            	logger.warn(sb.toString() + " failed to import");					
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally{
				in.close();
			}
			System.out.println(String.valueOf((Double.parseDouble(String.valueOf(i - Constants.STARTING_IMPORT_INDEX)) / Double.parseDouble(String.valueOf(Constants.ENDING_IMPORT_INDEX - Constants.STARTING_IMPORT_INDEX))) * 100.0) + "% complete");
		}
		logger.info("Contract import completed on " + new LocalDateTime().toString());
		logger.info(importSuccessCount + " records imported successfully. " 
				+ importFailureCount + " records failed to import");		
	}


	
	private <T> void importObject(Class<T> clazz, String object) {
		//Get array of objects of type T
		String[] jsonObjects = object.split("}");
		for (String obj : jsonObjects) {
			T instance = null;
			try {
				instance = (T) clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			String formattedObject = obj.replace("\"","").replace("{", "").replace("}", "");
			//for each property in the current object
			String[] properties = formattedObject.split(",");
			for (String property : properties) {
				//Split object to get key value pair of current property
				String[] s = property.split(":");
				String key = s[0];
				String value = null;
				try{
					value = s[1];
				}
				catch(IndexOutOfBoundsException e){
					//e.printStackTrace();
				}
				for (Field f : instance.getClass().getDeclaredFields()) {
	    			for (Annotation a : f.getDeclaredAnnotations()) {
	    				if(a instanceof Name && ((Name)a).value().equals(key)){
	    					f.setAccessible(true);
	    					try {
								f.set(instance, customConverter(f.getType().getSimpleName(), value));
							} catch (IllegalArgumentException
									| IllegalAccessException 
									| ArrayIndexOutOfBoundsException e) {
								try {
									f.set(instance, customConverter(f.getType().getSimpleName(), null));
								} catch (IllegalArgumentException
										| IllegalAccessException e1) {
								}
								logger.warn(f.getName() + ": " + e.toString() + "(" + property + ")");
							}
	    				}
					}
				}					
			}
			addNewObject(instance);
		}
	}
	
	private void addNewObject(Object obj){
		String className = obj.getClass().getSimpleName();
		switch (className) {
			case "ContractOverview":
				contractOverviewService.add((ContractOverview) obj);
				break;
			case "YearlyContract":
				yearlyContractService.add((YearlyContract) obj);
				break;
			case "DeadMoney":
				deadMoneyService.add((DeadMoney) obj);
			default:
				break;
		}
	}

	private Object customConverter(String type, String val) {
		Object value = null;
		switch (type) {
			case "Integer":
				value = Integer.parseInt(val);
				break;
			case "Double":
				value = Double.parseDouble(val);
				break;
			case "String":
				value = val;
				break;			
			default:
				break;
			}
		return value;
	}		

}
