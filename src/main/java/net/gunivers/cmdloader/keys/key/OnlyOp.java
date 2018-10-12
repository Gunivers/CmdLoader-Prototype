package net.gunivers.cmdloader.keys.key;

import java.util.function.Predicate;

import net.gunivers.cmdloader.keys.structure.SimpleValueKey;

public class OnlyOp extends SimpleValueKey<Boolean>
{
	protected OnlyOp()
	{
		super("only_op", true, true);
	}

	@Override
	public Predicate<Boolean> getValider()
	{
		return b -> true;
	}

	@Override
	public KeyInstance<Boolean> parse(String value)
	{
		if (!(value.equals("true") || value.equals("false")))
			throw new ClassCastException("Couldn't convert '" + value + "' to boolean");
		
		return this.newInstance(Boolean.valueOf(value));
	}

	@Override
	public boolean trigger(Boolean value, KeyInstance<Boolean> instance)
	{
		instance.setValue(value);
		return true;
	}
}
