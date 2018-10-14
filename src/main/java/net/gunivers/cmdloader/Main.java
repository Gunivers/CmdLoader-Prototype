package net.gunivers.cmdloader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

/**
 * 
 * @author Oromis
 *
 */
public class Main {

	public static CommandDispatcher<Sender> dispatcher = new CommandDispatcher<>();
	public final static boolean TEST_JSON = false;

	@SuppressWarnings({ "unused", "unchecked" })
	public static void main(String[] args) throws CommandSyntaxException
	{	
		if (TEST_JSON)
		{
			HashMap<String, Object> hm = new HashMap<>();
			
			try (FileReader fr = new FileReader(new File("/test.json")))
			{
				hm.putAll((JSONObject) new JSONParser().parse(fr));
			} catch (IOException | ParseException e)
			{
				e.printStackTrace();
			}
			
			System.out.println(hm);
		}
		
		CloneAndRotate cc = new CloneAndRotate();
		ParseResults<Sender> parse = dispatcher.parse("cloneandrotate 1 2 3 4 5 6 7 8 9 360", new Sender());
		dispatcher.execute(parse);
	}

}
