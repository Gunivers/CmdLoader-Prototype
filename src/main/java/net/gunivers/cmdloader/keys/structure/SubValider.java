package net.gunivers.cmdloader.keys.structure;

import java.util.function.Predicate;

public interface SubValider<T>
{
	public Predicate<T> getSubValider();
}
