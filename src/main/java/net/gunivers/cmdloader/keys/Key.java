package net.gunivers.cmdloader.keys;

import java.util.HashMap;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public abstract class Key<T>
{
	private final static HashMap<Class<? extends Key<?>>, Key<?>> keys = new HashMap<>();
	
	protected final String name;
	protected final boolean singleton;
	protected T value;
	
	private Predicate<T> valider;
	
	
	public Key(String name, T defaultValue, boolean singleton)
	{
		keys.putIfAbsent((Class<? extends Key<T>>) this.getClass(), this);
		
		this.name = name;
		this.singleton = singleton;
		this.value = defaultValue;
		
		this.valider = this.getValider();
	}

	public void setValue(T value)
	{
		if (!valider.test(value))
			throw new AssertionError("Illegal value for " + this.getClass().getName() + ": " + value);
		
		this.value = value;
	}
	
	public abstract Predicate<T> getValider();
	
	
	public final static HashMap<String, Key<?>> getKeys() { return (HashMap<String, Key<?>>) keys.clone(); }
	public final static <T> Key<T> getKey(Class<? extends Key<T>> clazz) { return (Key<T>) keys.get(clazz); }
	
	public String getName() { return this.name; }
}
