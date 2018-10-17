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
public class DescriptionKey extends SimpleValueKey<String>
{
	protected DescriptionKey()
	{
		super("description", "There was no description provided nor found", true);
	}

	@Override
	public Predicate<String> getValider()
	{
		return s -> s != null;
	}

	@Override
	public KeyInstance<String> parse(String value, KeyInstance<?> source)
	{
		return this.newInstance(value, new Context(source, ValueType.Unknown, value));
	}

	@Override
	public boolean trigger(String value, KeyInstance<String> instance)
	{
		instance.setValue(value);
		return true;
	}
}
