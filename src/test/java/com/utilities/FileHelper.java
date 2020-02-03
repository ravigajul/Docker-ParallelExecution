package com.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.io.FileUtils;

public class FileHelper {


	public static boolean IsRegistrationComplete(Reader file, String text) throws IOException {
		boolean flag = false;
		BufferedReader reader = new BufferedReader(file);
		String currentLine = reader.readLine();
		while (currentLine != null) {

			if (currentLine.contains(text)) {

				flag = true;
				break;
			}
			currentLine = reader.readLine();
		}
		return flag;
	}
	
	public static void DeleteFile(String reportsPath)
	{
		File file = new File(reportsPath);

		if (file.exists()) {
			FileUtils.deleteQuietly(file);
			System.out.println(("**** " + reportsPath+ "  file deleted ***"));
		}
	}

}
