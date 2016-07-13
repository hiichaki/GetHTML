package com.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class HTMLContentGetter {

	@SuppressWarnings("resource")
	public static String getContent(String url) {
		String content = null;
		URLConnection connection = null;
		try {
			connection = new URL(url).openConnection();
			Scanner scanner = new Scanner(connection.getInputStream());
			scanner.useDelimiter("\\Z");
			content = scanner.next();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return content;
	}

	public static void bufferWriter(String story) {
		try {

			File file = new File("E:/1/1.txt");

			if (!file.exists()) {
				file.createNewFile();
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
//				begin of the file
				bw.write(
						"<html><head><title></title></head><body style = \'background-color:#0B162C; font-family: cursive;  font-size:35; color:#C5FFBB;\'><div align=justify style=\'padding:0 320;  \'>");
				bw.close();
			} 

			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);

			out.println(story);

			bw.close();
			System.out.println("Done");
		} catch (IOException e) {
			
		}
	}

	public static void main(String[] args) {
		
		for (int i = 37; i < 56; ++i) {
			String content = getContent("https://www.fictionpress.com/s/2961893/" + i + "/Mother-of-Learning");
			int beginIndex = content.indexOf("<div class='storytext");
			int endIndex = content.indexOf("</div><div style='height:5px'></div><");
			System.out.println(beginIndex + " " + endIndex);
			String story = content.substring(beginIndex, endIndex);
			bufferWriter(story);
		}
//		end of the file
		bufferWriter("</div></body></html>");

	}

}
