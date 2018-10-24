package net.gunivers.cmdloader.keys.keys;

import java.util.ArrayList;
import java.util.function.Predicate;

import org.json.JSONObject;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.gunivers.cmdloader.keys.Key;
import net.gunivers.cmdloader.keys.structure.abstracts.CompoundKey;
import net.gunivers.cmdloader.keys.structure.classes.Compound;
import net.gunivers.cmdloader.keys.structure.classes.Context;
import net.gunivers.cmdloader.keys.structure.enums.ValueType;
import net.gunivers.cmdloader.keys.structure.exceptions.KeyNotFoundException;
import net.gunivers.cmdloader.keys.structure.interfaces.Root;

/**
 * 
 * @author A~Z
 *
 */
public class CommandKey extends CompoundKey implements Root
{
	protected CommandKey()
	{
		super("command", true);
	}

	@Override
	public Predicate<Compound<CompoundKey>> getSubValider() {return (Compound<CompoundKey> keys) -> this.validateKeys(keys.getCompound());}

	@Override
	public KeyInstance<Compound<CompoundKey>> parse(String value)
	{
		Compound<CompoundKey> c = new Compound<CompoundKey>(this);
		c.parse(value);
		
		return this.newInstance(c, new Context(this, ValueType.Compound, 0, value));
	}

	@Override
	public boolean trigger(Compound<CompoundKey> value, KeyInstance<Compound<CompoundKey>> instance)
	{
		// TODO Auto-generated method stub
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
