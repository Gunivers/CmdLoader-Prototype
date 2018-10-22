package net.gunivers.cmdloader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.common.base.Strings;
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
	public final static boolean TEST_JSON = true;

	// À revoir
	@SuppressWarnings("unchecked")
	public static void readJSONObjectAndDisp(HashMap<String, Object> hm, int numberTab) throws ParseException {
		for (Entry<String, Object> entry : hm.entrySet()) { //Tant qu'il y a des éléments sur un même niveau
			System.out.println(Strings.repeat("\t", numberTab) + entry.getKey()); //On print dans tout les cas la clé
			if (entry.getValue() instanceof JSONObject) { //Si c'est un JSON Object, on convertir en HashMap, puis on applique la récursive dessus
				HashMap<String, Object> hm2 = new HashMap<>();
				hm2.putAll((JSONObject) entry.getValue());
				readJSONObjectAndDisp(hm2, numberTab + 1);
			} else if (entry.getValue() instanceof JSONArray)
				readJSONArrayAndDisp((JSONArray) entry.getValue(), numberTab + 1); //Sinon, si c'est un JSON Array, on appelle la méthode faite pour les lire
			else
				System.out.println(Strings.repeat("\t", numberTab + 1) + entry.getValue()); //Sinon, on print la valeur
		}
	}

	@SuppressWarnings("unchecked")
	public static void readJSONArrayAndDisp(JSONArray array, int numberTab) {
		for (Object o : array) { //On boucle sur le tableau
			if (o instanceof JSONObject) { //Si c'est un JSON Object, on appelle la méthode les traitants
				try {
					readJSONObjectAndDisp((HashMap<String, Object>) o, numberTab);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else //Sinon, on print la valeur
				System.out.println(Strings.repeat("\t", numberTab + 1) + o);
		}
	}

	@SuppressWarnings({ "unused", "unchecked" })
	public static void main(String[] args) throws CommandSyntaxException {
		if (TEST_JSON) {
			HashMap<String, Object> hm = new HashMap<>();

			try (FileReader fr = new FileReader(new File(Main.class.getResource("/Test.json").toURI()))) {
				hm.putAll((JSONObject) new JSONParser().parse(fr));
			} catch (URISyntaxException | IOException | ParseException e) {
				e.printStackTrace();
			}

			try {
				readJSONObjectAndDisp(hm, 0);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		CloneAndRotate cc = new CloneAndRotate();
		ParseResults<Sender> parse = dispatcher.parse("cloneandrotate 1 2 3 4 5 6 7 8 9 360", new Sender());
		dispatcher.execute(parse);
	}
}
