package net.gunivers.cmdloader.keys;

import java.util.ArrayList;

public class KeyInstance<T>
{
	private static final ArrayList<KeyInstance<?>> instances = new ArrayList<>();
	private final Key<T> key;
	
	private T value = null;
	
	{
		instances.add(this);
	}

	private KeyInstance(Class<? extends Key<T>> clazz)
	{
		this(clazz, null);
	}
	
	private KeyInstance(Class<? extends Key<T>> clazz, T value)
	{
		this.key = Key.getKey(clazz);
		this.value = value;
	}

	public void setValue(T value)
	{
		if (!this.key.getValider().test(value))
			throw new AssertionError("Illegal value assertion for " + this.key.);
	}
	
	public static <T> KeyInstance<T> newInstance(Class<? extends Key<T>> clazz) { return new KeyInstance<T>(clazz); }
	public static <T> KeyInstance<T> newInstance(Class<? extends Key<T>> clazz, T value) { return new KeyInstance<T>(clazz, value); }
	
	public T getValue() { return this.value; }
	public Key<T> getKey() { return this.key; }
}
