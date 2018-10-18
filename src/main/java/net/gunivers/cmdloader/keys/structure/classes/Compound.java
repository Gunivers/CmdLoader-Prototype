package net.gunivers.cmdloader.keys.structure.classes;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import net.gunivers.cmdloader.keys.Key;
import net.gunivers.cmdloader.keys.Key.KeyInstance;
import net.gunivers.cmdloader.keys.keys.KeyRegister;
import net.gunivers.cmdloader.keys.structure.enums.ValueType;
import net.gunivers.cmdloader.keys.structure.interfaces.Parser;

/**
 * 
 * @author A~Z
 *
 */
@SuppressWarnings("rawtypes")
public class Compound<S extends Key<?>> implements Parser<KeyInstance[]>
{
	private final ArrayList<KeyInstance<Object>> keys = new ArrayList<>();
	private final S source;
	
	public Compound(S source)
	{
		this.source = source;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public KeyInstance[] parse(String string)
	{
		string = "{" + string + "}";
		
		HashMap<String, Object> hm = new HashMap<>();
		final ArrayList<KeyInstance<Object>> keys = new ArrayList<>();
		
		try (StringReader fr = new StringReader(string))
		{
			hm.putAll((JSONObject) new JSONParser().parse(fr));
		} catch (IOException | ParseException e)
		{
			e.printStackTrace();
		}
		
		for (Entry<String, ?> entry : hm.entrySet())
		{
			keys.add(KeyRegister.keys.get(entry.getKey()).newInstance(entry.getValue(), new Context(source, ValueType.Compound, string)));
		}
		
		this.keys.clear();
		this.keys.addAll(keys);
		
		return (KeyInstance[]) keys.toArray();
	}
	
	public KeyInstance[] getCompound()
	{
		return (KeyInstance[]) this.keys.toArray();
	}
	
	public int size()
	{
		return keys.size();
	}
}
