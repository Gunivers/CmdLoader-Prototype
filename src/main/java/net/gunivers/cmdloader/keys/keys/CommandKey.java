package net.gunivers.cmdloader.keys.keys;

import java.util.ArrayList;
import java.util.function.Predicate;

import org.json.JSONObject;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.gunivers.cmdloader.keys.Key;
import net.gunivers.cmdloader.keys.structure.abstracts.CompoundKey;
import net.gunivers.cmdloader.keys.structure.classes.Context;
import net.gunivers.cmdloader.keys.structure.exceptions.KeyNotFoundException;
import net.gunivers.cmdloader.keys.structure.interfaces.KeyContainer;
import net.gunivers.cmdloader.keys.structure.interfaces.Root;

/**
 * 
 * @author A~Z
 *
 */
public class CommandKey extends CompoundKey implements Root, KeyContainer
{
	protected CommandKey()
	{
		super("command", true);
	}

	@Override
	public Predicate<JSONObject> getSubValider() {return keys -> this.validateKeys(JSONObject.getNames(keys));}

	@Override
	public KeyInstance<JSONObject> parse(String value)
	{
		return this.newInstance(new JSONObject(value), new Context(null, this, 0, value));
	}

	@Override
	public <S> boolean trigger(CommandDispatcher<S> dispatcher, KeyInstance<JSONObject> instance)
	{
		return false;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public <S> void rootAction(CommandDispatcher<S> dispatcher, String raw) throws KeyNotFoundException
	{
		final ArrayList<Key> keys = new ArrayList<>();
		JSONObject compound = new JSONObject(raw);
		
		for (String name : JSONObject.getNames(compound))
		{
			Key k = KeyRegister.keys.get(name);
			
			if (k == null)
				throw new KeyNotFoundException(name);
			
			keys.add(k);
		}
		
		if (!this.validateKeys((Key[]) keys.toArray()))
			throw new AssertionError("Unmatching or missing key in 'command'");
		
		for (Key key : keys)
		{
			if (key == KeyRegister.name)
				dispatcher.register(LiteralArgumentBuilder.literal(compound.getString(key.getName())));
		}
		
		return;
	}

	@Deprecated
	public boolean validateKeys(String[] keys)
	{
		Key<?>[] array = new Key<?>[keys.length];
		
		for (int i = 0; i < keys.length; i++)
			array[i] = KeyRegister.keys.get(keys[i]);
		
		return this.validateKeys(array);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Key[] getNecessaryKeys()
	{
		return new Key[0];
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Key[] getOptionalKeys()
	{
		return new Key[0];
	}
}
