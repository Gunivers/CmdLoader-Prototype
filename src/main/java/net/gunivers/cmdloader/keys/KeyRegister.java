package net.gunivers.cmdloader.keys;

import java.util.ArrayList;

import net.gunivers.cmdloader.keys.structure.Key;
import net.gunivers.cmdloader.keys.structure.SimpleArrayKey;
import net.gunivers.cmdloader.keys.structure.SimpleKeyCompound;
import net.gunivers.cmdloader.keys.structure.SimpleValueKey;
import net.gunivers.cmdloader.keys.structure.TypedArrayKey;

/**
 * 
 * @author A~Z
 *
 */
public abstract class KeyRegister
{
	/*
	 * Add here any key that you creates, as the following:
	 * <Type> <name> = new <Type>();
	 * 
	 * e.g:
	 * SelectorKey selectorKey = new SelectorKey();
	 * 
	 * 	- In this example, the class SelectorKey extends from SimpleValueKey
	 */
	
	
	
	
	public static ArrayList<SimpleValueKey<?>> getSimpleValueKeys()
	{
		final ArrayList<SimpleValueKey<?>> keys = new ArrayList<>();
		
		Key.getKeys().values().stream().filter(k -> k instanceof SimpleValueKey).forEach(k -> keys.add((SimpleValueKey<?>) k));
		
		return keys;
	}
	
	public static ArrayList<SimpleArrayKey> getSimpleArrayKeys()
	{
		final ArrayList<SimpleArrayKey> keys = new ArrayList<>();
		
		Key.getKeys().values().stream().filter(k -> k instanceof SimpleArrayKey).forEach(k -> keys.add((SimpleArrayKey) k));
		
		return keys;
	}
	
	public static ArrayList<TypedArrayKey<?>> getTypedArrayKeys()
	{
		final ArrayList<TypedArrayKey<?>> keys = new ArrayList<>();
		
		Key.getKeys().values().stream().filter(k -> k instanceof TypedArrayKey).forEach(k -> keys.add((TypedArrayKey<?>) k));
		
		return keys;
	}
	
	public static ArrayList<SimpleKeyCompound> getSimpleCompoundKeys()
	{
		final ArrayList<SimpleKeyCompound> keys = new ArrayList<>();
		
		Key.getKeys().values().stream().filter(k -> k instanceof SimpleKeyCompound).forEach(k -> keys.add((SimpleKeyCompound) k));
		
		return keys;
	}
}
