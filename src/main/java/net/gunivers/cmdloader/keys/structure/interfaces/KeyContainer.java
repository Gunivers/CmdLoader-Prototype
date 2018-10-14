package net.gunivers.cmdloader.keys.structure.interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import net.gunivers.cmdloader.keys.Key;
import net.gunivers.cmdloader.keys.Key.KeyInstance;

/**
 * 
 * @author A~Z
 *
 */
@SuppressWarnings("rawtypes")
public interface KeyContainer
{
	public Key[] getNecessaryKeys();
	public Key[] getOptionalKeys();
	
	public default boolean validateKeys(Key[] keys)
	{
		HashMap<Key, Boolean> validator = new HashMap<>();
		
		for (Key k : this.getNecessaryKeys())
			validator.put(k, false);
		
		ArrayList<Key> optional = (ArrayList<Key>) Arrays.asList(getOptionalKeys());
		
		for (Key key : keys)
		{
			if (validator.containsKey(key))
			{
				validator.replace(key, true);
				continue;
			}
			
			if (!optional.contains(key))
				return false;
		}
		
		return true;
	}
	
	public default boolean validateKeys(KeyInstance[] keys)
	{
		Key[] array = new Key[keys.length];
		
		for (int i = 0; i < keys.length; i++)
		{
			array[i] = keys[i].getKey();
		}
		
		return this.validateKeys(array);
	}
}
