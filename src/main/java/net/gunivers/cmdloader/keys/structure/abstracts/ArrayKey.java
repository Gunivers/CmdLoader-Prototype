package net.gunivers.cmdloader.keys.structure.abstracts;

import java.util.function.Predicate;

import net.gunivers.cmdloader.keys.Key;
import net.gunivers.cmdloader.keys.structure.interfaces.SubValider;

public abstract class ArrayKey<T> extends Key<T[]> implements SubValider<T[]>
{
	private int min = 0;
	private int max = Integer.MAX_VALUE;
	
	public ArrayKey(String name, T[] defaultValue, boolean singleton)
	{
		super(name, defaultValue, singleton);
	}
	
	public ArrayKey(String name, T[] defaultValue, boolean singleton, int min, int max)
	{
		super(name, defaultValue, singleton);
	}

	@Override
	public Predicate<T[]> getValider()
	{
		return array -> min <= array.length && array.length <= max && getSubValider().test(array);
	}
}
