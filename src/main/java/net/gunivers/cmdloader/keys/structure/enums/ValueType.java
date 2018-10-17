package net.gunivers.cmdloader.keys.structure.enums;

import net.gunivers.cmdloader.keys.structure.classes.Array;
import net.gunivers.cmdloader.keys.structure.classes.Compound;

/**
 * 
 * @author A~Z
 *
 */
public enum ValueType
{
	Byte(Byte.class),
	Integer(Integer.class),
	Long(Long.class),
	
	Float(Float.class),
	Double(Double.class),
	
	Char(Character.class),
	String(String.class),
	
	Boolean(Boolean.class),
	
	Array(Array.class),
	Compound(Compound.class),
	
	Unknown(null)
	;
	
	private final Class<?> clazz;
	
	private ValueType(Class<?> clazz)
	{
		this.clazz = clazz;
	}
	
	public boolean isSimpleValue() { return !this.isComplexValue(); }
	
	public boolean isComplexValue()
	{
		switch (this)
		{
			case Array:
			case Compound:
				return true;
				
			default: return false;
		}
	}
	
	public boolean isIntegerValue()
	{
		switch (this)
		{
			case Byte:
			case Integer:
			case Long:
				return true;
				
			default: return false;
		}
	}
	
	public boolean isFloatingValue()
	{
		switch (this)
		{
			case Float:
			case Long:
				return true;
			
			default: return false;
		}
	}
	
	public boolean isTextValue()
	{
		switch (this)
		{
			case Char:
			case String:
				return true;
				
			default: return false;
		}
	}
	
	public Class<?> getClazz() { return clazz; }
}
