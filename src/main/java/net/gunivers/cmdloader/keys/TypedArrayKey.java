package net.gunivers.cmdloader.keys;

import java.util.function.Predicate;

public class TypedArrayKey<T> extends Key<T[]>
{

	public TypedArrayKey(String name, T[] defaultValue, boolean singleton)
	{
		super(name, defaultValue, singleton);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Predicate<T[]> getValider()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
