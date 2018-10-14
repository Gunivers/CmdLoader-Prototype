package net.gunivers.cmdloader.keys.structure.abstracts;

import net.gunivers.cmdloader.keys.Key;

/**
 * 
 * @author A~Z
 *
 * @param <T>
 */
public abstract class SimpleValueKey<T> extends Key<T>
{
	public SimpleValueKey(String name, T defaultValue, boolean singleton)
	{
		super(name, defaultValue, singleton);
	}
}
