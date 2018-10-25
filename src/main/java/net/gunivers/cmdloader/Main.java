package net.gunivers.cmdloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.common.base.Strings;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.CommandNode;

import net.gunivers.cmdloader.commands.CloneAndRotate;
import net.gunivers.cmdloader.commands.Sender;
import net.gunivers.cmdloader.commands.TestCommand;
import net.gunivers.cmdloader.keys.Key;
import net.gunivers.cmdloader.keys.keys.KeyRegister;
import net.gunivers.cmdloader.keys.structure.exceptions.KeyNotFoundException;
import net.gunivers.cmdloader.keys.structure.exceptions.KeyNotRootException;
import net.gunivers.cmdloader.keys.structure.interfaces.Root;

/**
 * 
 * @author Oromis
 *
 */
public class Main
{
	public final static CommandDispatcher<Sender> dispatcher = new CommandDispatcher<>();
	public final static String folder = "/resource";
	
	public final static boolean TEST_JSON = false;
	public final static boolean TEST_PARSE = false;


	@SuppressWarnings({ "unused" })
	public static void main(String[] args) throws CommandSyntaxException, IOException, KeyNotFoundException, KeyNotRootException
	{
		if (TEST_PARSE)
			testParse();
		
		if (TEST_JSON)
			testJson();
		
		
		CloneAndRotate cc = new CloneAndRotate();
		TestCommand test = new TestCommand();
		
		ParseResults<Sender> parse = dispatcher.parse("cloneandrotate 1 2 3 4 5 6 7 8 9 360", new Sender());
		ParseResults<Sender> parse2 = dispatcher.parse("test .5", new Sender());
		dispatcher.execute(parse2);
		
		System.out.println(Main.cmdView(dispatcher));
	}
	
	/*/ 
	/* ==========================================================================================================
	/* ‼											TEST :: PARSE												‼
	/* ==========================================================================================================
	/*/
	
	public static void testParse() throws KeyNotFoundException, KeyNotRootException, IOException
	{
		org.json.JSONObject base = new org.json.JSONObject(Main.loadFile(new File("/test.json")));
		
		for (String name : org.json.JSONObject.getNames(base))
		{
			Key<?> key = KeyRegister.keys.get(name);
			
			if (key == null)
				throw new KeyNotFoundException(name);
			if (!(key instanceof Root))
				throw new KeyNotRootException("The key '" + key + "' should be root");
			
			((Root) key).rootAction(dispatcher, base.get(name).toString());
		}
	}

	public static String loadFile(File f) throws IOException 
	{
		if (!f.exists())
			f.createNewFile();
			
		String result = null;
		
		try
		{
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
			StringWriter out = new StringWriter();
			int b;
			
			while ((b = in.read()) != -1)
				out.write(b);
			
			out.flush();
			out.close();
			in.close();
			
			return result = out.toString();
		} catch (IOException ie)
		{
			ie.printStackTrace();
		}
		
		return result;
	}
	
	/*/ 
	/* ==========================================================================================================
	/* ‼											TEST :: JSON												‼
	/* ==========================================================================================================
	/*/
	
	@SuppressWarnings("unchecked")
	public static void testJson()
	{
		HashMap<String, Object> hm = new HashMap<>();

		try (FileReader fr = new FileReader(new File(Main.class.getResource("/Test.json").toURI())))
		{
			hm.putAll((JSONObject) new JSONParser().parse(fr));
		} catch (URISyntaxException | IOException | ParseException e)
		{
			e.printStackTrace();
		}

		try
		{
			readJSONObjectAndDisp(hm, 0);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
	}
	
	// À revoir
	@SuppressWarnings("unchecked")
	public static void readJSONObjectAndDisp(HashMap<String, Object> hm, int numberTab) throws ParseException
	{
		for (Entry<String, Object> entry : hm.entrySet())
		{
			System.out.println(Strings.repeat("\t", numberTab) + entry.getKey());

			if (entry.getValue() instanceof JSONObject)
			{
				HashMap<String, Object> hm2 = new HashMap<>();
				hm2.putAll((JSONObject) entry.getValue());

				readJSONObjectAndDisp(hm2, numberTab + 1);

			} else if (entry.getValue() instanceof JSONArray)
			{
				readJSONArrayAndDisp((JSONArray) entry.getValue(), numberTab + 1);
			} else
			{
				System.out.println(Strings.repeat("\t", numberTab + 1) + entry.getValue());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void readJSONArrayAndDisp(JSONArray array, int numberTab)
	{
		for (Object o : array)
		{
			if (o instanceof JSONObject)
			{
				try
				{
					readJSONObjectAndDisp((HashMap<String, Object>) o, numberTab);
				} catch (ParseException e)
					{ e.printStackTrace(); }
			} else
				System.out.println(Strings.repeat("\t", numberTab + 1) + o);
		}
	}
	
	public static <S> String cmdView(CommandDispatcher<S> dispatcher)
	{
		final StringBuilder string = new StringBuilder("---------------------------------- View ----------------------------------");
		cmdView_loop(dispatcher.getRoot()).forEach(s -> string.append("\n" + s));
		return string.toString();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<String> cmdView_loop(CommandNode root)
	{
		ArrayList<String> tree = new ArrayList<>();
		
		for (CommandNode child : (Collection<CommandNode>) root.getChildren())
		{
			tree.add("── " + child.getName());
			
			List<String> subTree = cmdView_loop(child);
			
			for (int i = 0; i < subTree.size(); i++)
			{
				tree.add("   " + (i == subTree.size()-1 ? "└" : i == 0 ? "├" : "│") + subTree.get(i));
			}
		}
		
		return tree;
	}
}
