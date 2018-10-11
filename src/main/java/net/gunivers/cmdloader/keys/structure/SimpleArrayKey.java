package net.gunivers.cmdloader.keys.structure;

import java.util.function.Predicate;

public abstract class SimpleArrayKey extends Key<Object[]>
{
	int min = 0;
	int max = Integer.MAX_VALUE;
	
	public SimpleArrayKey(String name, Object[] defaultValue, boolean singleton)
	{
		super(name, defaultValue, singleton, false);
	}
	
	public SimpleArrayKey(String name, Object[] defaultValue, boolean singleton, int min, int max)
	{
		super(name, defaultValue, singleton, false);
		
		this.min = min;
		this.max = max;
	}

	@Override
	public Predicate<Object[]> getValider()
	{
		return (Object[] array) -> min <= array.length && array.length <= max && getSubValider().test(array);
	}

	public abstract Predicate<Object[]> getSubValider();
}
