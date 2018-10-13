package net.gunivers.cmdloader.keys.structure;

import net.gunivers.cmdloader.keys.Key;

public abstract class SimpleValueKey<T> extends Key<T>
{
	public SimpleValueKey(String name, T defaultValue, boolean singleton)
	{
		super(name, defaultValue, singleton, false);
	}
}
