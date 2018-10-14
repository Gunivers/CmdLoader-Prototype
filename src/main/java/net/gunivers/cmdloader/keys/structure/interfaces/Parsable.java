package net.gunivers.cmdloader.keys.structure.interfaces;

@FunctionalInterface
public interface Parsable<T>
{
	public T parse(String string);
}
