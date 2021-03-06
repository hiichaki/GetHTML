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
				// begin of the file
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

		for (int i = 1; i < 42; ++i) {
//			String content = getContent("https://www.fictionpress.com/s/2961893/" + i + "/Mother-of-Learning");
			String content = null;
			if(i<11) {
				if(i==2) {
					content = getContent("http://samlib.ru/d/dworcowa_i/a0" + i + "-1.shtml");
				} else {
					content = getContent("http://samlib.ru/d/dworcowa_i/a0" + i + ".shtml");
				}
				 
			} else {
				 content = getContent("http://samlib.ru/d/dworcowa_i/a" + i + ".shtml");
			}
//			System.out.println(content);
			int beginIndex = content.indexOf("<hr size=2 noshade>",1000 );
			int endIndex = content.indexOf("<hr size=2 noshade>",beginIndex+100);
			System.out.println(beginIndex + " " + endIndex);
			String story = content.substring(beginIndex, endIndex);
			bufferWriter(story);
		}
		// end of the file
		bufferWriter("</div></body></html>");
		

	}

}
