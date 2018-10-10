package net.gunivers.cmdloader.keys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public abstract class Key<T>
{
	private final static HashMap<String, Key<?>> keys = new HashMap<>();
	
	protected final String name;
	protected final boolean singleton;
	protected T value;
	
	private Predicate<T> valider;
	
	
	public Key(String name, T defaultValue, boolean singleton)
	{
		if (keys.containsKey(name))
			throw new InstantiationError("A Key with the name '" + name + "' already exist");
		keys.put(name, this);
		
		this.name = name;
		this.singleton = singleton;
		
		this.valider = this.getValider();
	}
	
	public abstract Predicate<T> getValider();
	
	public final static Set<String> getKeysID() { return keys.keySet(); }
	public final static HashMap<String, Key<?>> getKeys() { return (HashMap<String, Key<?>>) keys.clone(); }
	public final static <T> Key<T> getKey(String name) { return (Key<T>) keys.get(name); }
	
	public String getName() { return this.name; }
	
	public KeyInstance<T> newInstance(T value)
	{
		return new KeyInstance<T>(value, this);
	}
	
	/**
	 * 
	 * @author A~Z
	 *
	 * @param <K>
	 *           the type of variable the json file may 
	 */
	public static class KeyInstance<K>
	{
		private static ArrayList<KeyInstance<?>> instances = new ArrayList<>();
		
		private final Key<K> key;
		private K value;
		
		private KeyInstance(K value, Key<K> key)
		{
			if (key.singleton && instances.stream().anyMatch(k -> k.key == key))
				throw new InstantiationError("Tried to instantiate the singleton Key '" + key + "' 2 times");
			
			this.value = value;
			this.key = key;
		}
		
		public void setValue(K value)
		{
			if (!key.valider.test(value))
				throw new AssertionError("Illegal value assertion for " + this.key.name + ": " + value);
			
			this.value = value;
		}
		
		public K getValue() { return value; }
		public Key<K> getKey() { return key; }
	}
}
