package net.gunivers.cmdloader.keys.structure.abstracts;

import java.util.function.Predicate;

import org.json.JSONObject;

import net.gunivers.cmdloader.keys.Key;
import net.gunivers.cmdloader.keys.structure.interfaces.SubValider;
import net.gunivers.cmdloader.keys.structure.interfaces.KeyContainer;

/**
 * 
 * @author A~Z
 *
 */
public abstract class CompoundKey extends Key<JSONObject> implements SubValider<JSONObject>, KeyContainer
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
	public final Predicate<JSONObject> getValider()
	{
		return (JSONObject j) -> min <= j.length() && j.length() <= max && getSubValider().test(j);
	}
}
