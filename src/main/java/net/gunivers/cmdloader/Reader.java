package net.gunivers.cmdloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Reader {

	public static String get(InputStream is) {
		String result = "";
		
		try (BufferedReader in = new BufferedReader(new InputStreamReader(is))) {
			String str;
		
			while ((str = in.readLine()) != null)
				result += str + '\n';
			result = result.substring(0, result.length() - 1);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return result.equals("") ? null : result;
	}
	
}
