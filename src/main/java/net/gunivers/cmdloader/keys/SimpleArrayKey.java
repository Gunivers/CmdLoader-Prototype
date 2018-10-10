package net.gunivers.cmdloader.keys;

import java.util.function.Predicate;

public class SimpleArrayKey extends Key<Object[]>
{

	public SimpleArrayKey(String name, Object[] defaultValue, boolean singleton)
	{
		super(name, defaultValue, singleton);
	}

	@Override
	public Predicate<Object[]> getValider()
	{
		return null;
	}

}
