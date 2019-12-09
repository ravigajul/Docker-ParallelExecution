package com.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class Docker {

	public static void StartDockerAndGrid() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c start " + System.getProperty("user.dir") + "\\DockerUp.bat");
		Thread.sleep(15000);
		if(checkDockerStatus("docker-compose.log")){
			System.out.println("Docker is up and running");
		}
	}

	public static boolean checkDockerStatus(String val) throws IOException {
		String fileName = null;
		boolean flag = false;
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.SECOND, 90);
		long stopTime = calender.getTimeInMillis();
		BufferedReader reader = null;
		FileReader file = null;
		while (System.currentTimeMillis() <= stopTime) {
			fileName = System.getProperty("user.dir") + "\\"+val;
			file = new FileReader(fileName);
			File fi = new File(fileName);
			if (!fi.exists()) {
				fi.createNewFile();
			}
			if (FileHelper.IsRegistrationComplete(file, "The node is registered to the hub and ready to use")) {
				System.out.println("Node registration is complete");
				flag = true;
				break;
			}	
			file.close();
		}
		//reader.close();
	return flag;

	}
	

	public static void StopDockerInstance() throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c start " + System.getProperty("user.dir") + "\\DockerDown.bat");
		Thread.sleep(3000);
		String fileName = System.getProperty("user.dir") + "\\docker-compose.log";
		FileReader file = new FileReader(fileName);
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.SECOND, 30);
		long stopTime = calender.getTimeInMillis();
		while (System.currentTimeMillis() <= stopTime) {
			if (FileHelper.IsRegistrationComplete(file, "selenium-hub exited with code")) {
				System.out.println("DockerShutdown");
				File fi = new File(fileName);
				if (fi.isFile()) {
					fi.deleteOnExit();
					System.out.println("Docker-compose.log File Deleted Successfully");
				}
				break;
			}
			
		}

		// reader.close();
		file.close();
	}
}
