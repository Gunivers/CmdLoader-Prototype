package net.gunivers.cmdloader.keys.structure;

import java.util.function.Predicate;

public abstract class SimpleKeyCompound extends Key<Key<Object>[]>
{
	private int min = 0;
	private int max = Integer.MAX_VALUE;
	
	public SimpleKeyCompound(String name, boolean singleton)
	{
		super(name, null, singleton, true);
	}
	
	public SimpleKeyCompound(String name, boolean singleton, int min, int max)
	{
		super(name, null, singleton, true);
		
		this.min = min;
		this.max = max;
	}

	@Override
	public final Predicate<Key<Object>[]> getValider()
	{
		return (Key<Object>[] k) -> min <= k.length && k.length <= max && getSubValider().test(k);
	}
	
	public abstract Predicate<Key<?>[]> getSubValider();
}
