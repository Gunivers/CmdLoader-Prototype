package net.gunivers.cmdloader.keys.structure.classes;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import net.gunivers.cmdloader.keys.structure.interfaces.Parser;

/**
 * @author A~Z
 */
public class Array<T> implements Parser<T[]>
{
	private static final String key = "array";
	T[] array;
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] parse(String string)
	{
		string = "{ \""+ key +"\": " + string + "}";
		
		try
		{
			JSONObject obj = (JSONObject) new JSONParser().parse(string);
			return array = (T[]) obj.get(key);
		} catch (ParseException | ClassCastException e) { e.printStackTrace(); }
		
		return null;
	}
	
	public int size()
	{
		return array.length;
	}
}
