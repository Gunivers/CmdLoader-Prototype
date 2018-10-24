package net.gunivers.cmdloader.keys.structure.exceptions;

public class KeyNotRootException extends KeyException
{
	private static final long serialVersionUID = 2359614743006296046L;

	public KeyNotRootException(String key)
	{
		super(key, "must be root");
	}
}
