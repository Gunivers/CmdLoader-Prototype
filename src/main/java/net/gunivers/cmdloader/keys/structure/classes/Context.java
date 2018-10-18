package net.gunivers.cmdloader.keys.structure.classes;

import net.gunivers.cmdloader.keys.Key;
import net.gunivers.cmdloader.keys.structure.enums.ValueType;
import net.gunivers.cmdloader.keys.structure.interfaces.KeyContainer;

public class Context
{
	public final Key<?> parent;
	public final ValueType in;
	
	public final int layer;
	public final String raw;
	
	/**
	 * <h3>Constructor</h3>
	 * <p>
	 * Construct a Context with the layer as 0
	 * 
	 * @param parent
	 *              the keyContainer
	 * @param in
	 */
	public Context(Key<?> parent, ValueType in, String raw)
	{
		this(parent, in, -1, raw);
	}
	
	public Context(Key<?> parent, ValueType in, int layer, String raw)
	{
		if (!(parent instanceof KeyContainer))
			throw new InstantiationError("A Context cannot be instanciated outside of a KeyContainer");
		
		if (raw == null)
			throw new NullPointerException("The raw json cannot be null");
		
		this.parent = parent;
		this.in = in;
		
		this.layer = layer;
		this.raw = raw;
	}
}