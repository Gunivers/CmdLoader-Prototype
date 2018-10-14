package net.gunivers.cmdloader.keys.structure.abstracts;

import java.util.function.Predicate;

import net.gunivers.cmdloader.keys.Key;
import net.gunivers.cmdloader.keys.structure.interfaces.SubValider;
import net.gunivers.cmdloader.keys.structure.interfaces.KeyContainer;
import net.gunivers.cmdloader.keys.structure.types.Compound;

public abstract class CompoundKey extends Key<Compound> implements SubValider<Compound>, KeyContainer
{
	private int min = 0;
	private int max = Integer.MAX_VALUE;
	
	public CompoundKey(String name, boolean singleton)
	{
		super(name, null, singleton);
	}
	
	public CompoundKey(String name, boolean singleton, int min, int max)
	{
		super(name, null, singleton);
		
		this.min = min;
		this.max = max;
	}

	@Override
	public final Predicate<Compound> getValider()
	{
		return (Compound k) -> min <= k.size() && k.size() <= max && getSubValider().test(k);
	}
}
