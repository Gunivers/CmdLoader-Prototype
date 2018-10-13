package net.gunivers.cmdloader.keys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Predicate;

/**
 * 
 * @author A~Z
 *
 * @param <T>
 *           The type of held value
 */
@SuppressWarnings("unchecked")
public abstract class Key<T>
{
	private final static HashMap<String, Key<?>> keys = new HashMap<>();

	protected final boolean isSingleton;
	protected final boolean isKeyContainer;
	
	protected final String name;
	protected T value;
	
	private Predicate<T> valider;
	
	
	protected Key(String name, T defaultValue, boolean singleton, boolean keyContainer)
	{
		if (keys.containsKey(name))
			throw new InstantiationError("A Key with the name '" + name + "' already exist");
		
		keys.put(name, this);
		
		this.name = name;
		this.value = defaultValue;
		
		this.isSingleton = singleton;
		this.isKeyContainer = keyContainer;
		
		this.valider = this.getValider();
	}

	public KeyInstance<T> newInstance()
	{
		return this.newInstance(value);
	}
	
	public KeyInstance<T> newInstance(T value)
	{
		return new KeyInstance<T>(value, this);
	}
	
	// Static Getters
	public final static Set<String> getKeysID() { return keys.keySet(); }
	public final static HashMap<String, Key<?>> getKeys() { return (HashMap<String, Key<?>>) keys.clone(); }
	public final static Key<?> getKey(String name) { return keys.get(name); }
	
	// Getter
	public String getName() { return this.name; }

	// Abstract Methods
	public abstract Predicate<T> getValider();
	public abstract KeyInstance<T> parse(String value);
	public abstract boolean trigger(T value, KeyInstance<T> instance);
	
	/**
	 * 
	 * @author A~Z
	 *
	 * @param <K>
	 *           the type of held value
	 */
	public static class KeyInstance<K>
	{
		private static ArrayList<KeyInstance<?>> instances = new ArrayList<>();
		
		private final Key<K> key;
		private K value;
		
		private KeyInstance(Key<K> key)
		{
			this(key.value, key);
		}
		
		private KeyInstance(K value, Key<K> key)
		{
			if (key.isSingleton && instances.stream().anyMatch(k -> k.key == key))
				throw new InstantiationError("Tried to instantiate the isSingleton Key '" + key + "' 2 times");
			
			this.value = value;
			this.key = key;
		}
		
		public void setValue(K value)
		{
			if (!key.valider.test(value))
				throw new AssertionError("Illegal value assertion for " + this.key.name + ": " + value);
			
			this.value = value;
		}
		
		public boolean trigger(K value)
		{
			return key.trigger(value, this);
		}
		
		public K getValue() { return value; }
		public Key<K> getKey() { return key; }
	}
}
