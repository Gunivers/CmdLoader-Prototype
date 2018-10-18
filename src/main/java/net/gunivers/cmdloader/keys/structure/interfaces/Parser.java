package net.gunivers.cmdloader.keys.structure.interfaces;

/**
 * 
 * @author A~Z
 *
 * @param <T>
 */
@FunctionalInterface
public interface Parser<T>
{
	public T parse(String string);
}
