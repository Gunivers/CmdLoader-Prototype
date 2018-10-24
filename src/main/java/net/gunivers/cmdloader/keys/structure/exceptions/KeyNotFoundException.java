package net.gunivers.cmdloader.keys.structure.exceptions;

public class KeyNotFoundException extends KeyException
{
	private static final long serialVersionUID = 1097265131115930587L;

	public KeyNotFoundException(String key)
	{
		super(key, "does not exist");
	}
}
