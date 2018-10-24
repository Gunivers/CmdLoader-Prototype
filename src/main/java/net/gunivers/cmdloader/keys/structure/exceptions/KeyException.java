package net.gunivers.cmdloader.keys.structure.exceptions;

public class KeyException extends Exception
{
	private static final long serialVersionUID = -7227609796285430532L;

	public KeyException() { super(); }

	public KeyException(String key, String message) { super("The key '" +  key + "' " + message); }

	public KeyException(Throwable cause) { super(cause); }

	public KeyException(String message, Throwable cause) { super(message, cause); }

	public KeyException(String message, Throwable cause, boolean arg2, boolean arg3) { super(message, cause, arg2, arg3); }
}
