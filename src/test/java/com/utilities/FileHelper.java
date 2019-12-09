package com.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class FileHelper {

	public static boolean IsRegistrationComplete(Reader file,String text) throws IOException
	{
		boolean flag = false;
		BufferedReader reader = new BufferedReader(file);
		String currentLine=reader.readLine();
		while (currentLine!=null) {
			
			if(currentLine.contains(text)) {
				
				flag=true;
				break;
			}
			currentLine=reader.readLine();
		}
		return flag;
	}
	
	
}
