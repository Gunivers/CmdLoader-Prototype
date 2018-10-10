package net.gunivers.cmdloader;

import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class Main {

	public static CommandDispatcher<Sender> dispatcher = new CommandDispatcher<>();
	public final static boolean TEST_JSON = false;

	@SuppressWarnings("unused")
	public static void main(String[] args) throws CommandSyntaxException {

		if (TEST_JSON) {
			JSONParser parser = new JSONParser();
			Object obj;
			try {
				obj = parser.parse(Reader.get(Main.class.getResourceAsStream("/Test.json")));
				JSONObject jsonObject = (JSONObject) obj;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		CloneAndRotate cc = new CloneAndRotate();
		ParseResults<Sender> parse = dispatcher.parse("cloneandrotate 1 2 3 4 5 6 7 8 9 360", new Sender());
		dispatcher.execute(parse);
	}

}
