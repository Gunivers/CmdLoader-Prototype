package net.gunivers.cmdloader.keys;

import java.util.function.Predicate;

public class SimpleValueKey<T> extends Key<T>
{

	public SimpleValueKey(String name, T defaultValue, boolean singleton)
	{
		super(name, defaultValue, singleton);
	}

	@Override
	public Predicate<T> getValider()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
