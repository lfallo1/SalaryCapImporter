package com.salarycap.importers;

import java.io.IOException;
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
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import com.salarycap.annotations.Name;
import com.salarycap.models.Player;
import com.salarycap.service.PlayerService;
import com.salarycap.utilities.Constants;
import com.salarycap.utilities.JsonUtilities;

public class PlayerImporter implements Importer {

	private PlayerService playerService;
	private static Integer importSuccessCount = 0;
	private static Integer importFailureCount = 0;
	private final static Logger logger = Logger.getLogger(PlayerImporter.class);
	
	public PlayerImporter(PlayerService playerService){
		this.playerService = playerService;
	}
	
	@Override
	public void doImport() {
		logger.info("\r\nPlayer import starting at " + new LocalDateTime().toString()
				+ "\r\n" + Constants.DASHED_LINE);
		System.out.println("\r\nImporting Players...");
		for (int i = Constants.STARTING_IMPORT_INDEX; i <= Constants.ENDING_IMPORT_INDEX; i++) {
		        CloseableHttpClient httpClient = HttpClients.createDefault();
		        HttpPost post = new HttpPost(Constants.OVERTHECAP_BASE_URL);
		        List<NameValuePair> params = new ArrayList<>();
		        params.add(new BasicNameValuePair("type", "player"));
		        params.add(new BasicNameValuePair("player_id", String.valueOf(i)));
		        CloseableHttpResponse response = null;
		        Scanner in = null;
		        try
		        {
		            post.setEntity(new UrlEncodedFormEntity(params));
		            response = httpClient.execute(post);
		            HttpEntity entity = response.getEntity();
		            StringBuilder sb = new StringBuilder();
		            in = new Scanner(entity.getContent());
		            while (in.hasNext())
		            {
		            	sb.append(in.next() + " ");
		            }
		            EntityUtils.consume(entity);
		            if(!JsonUtilities.isEmpty("PlayerImporter", sb.toString())){
			    		String[] parts = sb.toString().replaceAll("null","\"0\"").split("\",");
			    		Player p = new Player();
			    		for (String string : parts) {
			    			string = string.replaceAll("[\"{}]+", "");
			    			String[] s = string.split(":");
			    			for (Field f : p.getClass().getDeclaredFields()) {
				    			for (Annotation a : f.getDeclaredAnnotations()) {
				    				if(a instanceof Name && ((Name)a).value().equals(s[0])){
				    					f.setAccessible(true);
				    					try {
											f.set(p, customConverter(f.getType().getSimpleName(), s[1]));
										} catch (IllegalArgumentException
												| IllegalAccessException 
												| ArrayIndexOutOfBoundsException e) {
											try {
												f.set(p, customConverter(f.getType().getSimpleName(), null));
											} catch (IllegalArgumentException
													| IllegalAccessException e1) {
											}
											logger.warn(string + ": " + e.toString() + "(" + sb.toString() +")");
										}
				    				}
								}
			    			}
			    		}
			    		playerService.add(p);
			    		importSuccessCount++;
		            }
		            else{
		            	importFailureCount++;
		            	logger.warn(sb.toString() + " failed to import");
		            }
		    		System.out.println(String.valueOf((Double.parseDouble(String.valueOf(i-Constants.STARTING_IMPORT_INDEX)) / Double.parseDouble(String.valueOf(Constants.ENDING_IMPORT_INDEX - Constants.STARTING_IMPORT_INDEX))) * 100.0) + "% complete");
		        } catch (IOException e) {
		        	logger.warn(e.getMessage());
				} finally
		        {
		            in.close();
		            try {
						response.close();
					} catch (IOException e) {
						logger.warn(e.getMessage());
					}
		        }		
		}
		logger.info("Player import completed on " + new LocalDateTime().toString());
		logger.info(importSuccessCount + " records imported successfully. " 
				+ importFailureCount + " records failed to import");
    }

	private Object customConverter(String type, String val) {
		Object value = null;
		switch (type) {
			case "Integer":
				value = Integer.parseInt(val);
				break;
			case "LocalDate":
				value = convertStringToJodaTime(val);
				break;
			case "String":
				value = val;
				break;			
			default:
				break;
			}
		return value;
	}	
	
	/**
	 * Convert string in format of YYYY-MM-DD to a jodatime object 
	 */
	private LocalDate convertStringToJodaTime(String date){
		try{
			String[] parts = date.split("[-]");
			Integer year = Integer.parseInt(parts[0]);
			Integer month = Integer.parseInt(parts[1]);
			Integer day = Integer.parseInt(parts[2]);
			return new LocalDate(year, month, day);
		}
		catch(ArrayIndexOutOfBoundsException | NullPointerException e){
			return new LocalDate(1900, 1, 1);
		}
		catch(Exception e){
			return new LocalDate(1900, 1, 1);
		}
	}
}