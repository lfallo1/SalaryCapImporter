package com.salarycap.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.lance.salarycap.service.IRosterService;

public class FileUtilities {
	private static final String FILE_LOCATION = "C:\\Users\\lfallo1\\Desktop\\SalaryCapImport\\export\\";
	public static <T> void saveToFile(String fileName, IRosterService<T> rosterService) {
		StringBuilder sb = new StringBuilder();
		String fileLocation = FILE_LOCATION + fileName;
		File file = new File(fileLocation);
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			for (T obj : rosterService.getAll()) {
				sb.append(obj.toString());
			}
			writer.write(sb.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
