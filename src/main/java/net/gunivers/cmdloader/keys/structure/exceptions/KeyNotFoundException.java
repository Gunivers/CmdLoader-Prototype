package net.gunivers.cmdloader.keys.structure.exceptions;

public class KeyNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1097265131115930587L;

	public KeyNotFoundException(String error)
	{
		super(error);
	}
}
