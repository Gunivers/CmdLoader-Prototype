package net.gunivers.cmdloader.keys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Predicate;

import net.gunivers.cmdloader.keys.structure.classes.Context;
import net.gunivers.cmdloader.keys.structure.interfaces.Parser;

/**
 * 
 * @author A~Z
 *
 * @param <T>
 *           The type of held value
 */
@SuppressWarnings("unchecked")
public abstract class Key<T> implements Parser<Key.KeyInstance<T>>
{
	private final static HashMap<String, Key<?>> keys = new HashMap<>();

	protected final String name;

	protected T value;
	protected final boolean isSingleton;
	
	private Predicate<T> valider;
	
	protected Key(String name, T defaultValue, boolean singleton)
	{
		if (keys.containsKey(name))
			throw new InstantiationError("A Key with the name '" + name + "' already exist");
		
		keys.put(name, this);
		
		this.name = name;
		
		this.value = defaultValue;
		this.isSingleton = singleton;
		
		this.valider = this.getValider();
	}

	public KeyInstance<T> newInstance(Context context)
	{
		return this.newInstance(value, context);
	}
	
	public KeyInstance<T> newInstance(T value, Context context)
	{
		return new KeyInstance<T>(value, this, context);
	}
	
	// Static Getters
	public final static Set<String> getKeysID() { return keys.keySet(); }
	public final static HashMap<String, Key<?>> getKeys() { return (HashMap<String, Key<?>>) keys.clone(); }
	public final static Key<?> getKey(String name) { return keys.get(name); }
	
	// Getter
	public String getName() { return this.name; }

	// Abstract Methods
	public abstract Predicate<T> getValider();
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
		
		public final Context context;
		
		private KeyInstance(K value, Key<K> key, Context context)
		{
			if (key.isSingleton && instances.stream().anyMatch(k -> k.key == key))
				throw new InstantiationError("Tried to instantiate the singleton Key '" + key + "' 2 times");
			
			this.setValue(value);
			this.key = key;
			
			this.context = context;
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
