package net.gunivers.cmdloader.keys.structure.interfaces;

/**
 * 
 * @author A~Z
 *
 * @param <T>
 */
@FunctionalInterface
public interface Parsable<T>
{
	public T parse(String string);
}
