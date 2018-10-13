package net.gunivers.cmdloader.keys.structure;

import java.util.function.Predicate;

import net.gunivers.cmdloader.keys.Key;

public abstract class CompoundKey<T> extends Key<Key<T>[]> implements SubValider<Key<T>[]>
{
	private int min = 0;
	private int max = Integer.MAX_VALUE;
	
	public CompoundKey(String name, boolean singleton)
	{
		super(name, null, singleton, true);
	}
	
	public CompoundKey(String name, boolean singleton, int min, int max)
	{
		super(name, null, singleton, true);
		
		this.min = min;
		this.max = max;
	}

	@Override
	public final Predicate<Key<T>[]> getValider()
	{
		return (Key<T>[] k) -> min <= k.length && k.length <= max && getSubValider().test(k);
	}
}
