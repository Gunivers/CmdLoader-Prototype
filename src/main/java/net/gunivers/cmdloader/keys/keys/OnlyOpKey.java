package net.gunivers.cmdloader.keys.keys;

import java.util.function.Predicate;

import net.gunivers.cmdloader.keys.structure.abstracts.SimpleValueKey;
import net.gunivers.cmdloader.keys.structure.classes.Context;
import net.gunivers.cmdloader.keys.structure.enums.ValueType;

/**
 * 
 * @author A~Z
 *
 */
public class OnlyOpKey extends SimpleValueKey<Boolean>
{
	protected OnlyOpKey()
	{
		super("only_op", true, true);
	}

	@Override
	public Predicate<Boolean> getValider()
	{
		return b -> true;
	}

	@Override
	public KeyInstance<Boolean> parse(String value, KeyInstance<?> source)
	{
		return this.newInstance(Boolean.valueOf(value), new Context(source, ValueType.Unknown, value));
	}

	@Override
	public boolean trigger(Boolean value, KeyInstance<Boolean> instance)
	{
		instance.setValue(value);
		return true;
	}
}
