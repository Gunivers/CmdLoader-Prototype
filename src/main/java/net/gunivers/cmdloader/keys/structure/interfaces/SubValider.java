package net.gunivers.cmdloader.keys.structure.interfaces;

import java.util.function.Predicate;

/**
 * 
 * @author A~Z
 *
 * @param <T>
 */
@FunctionalInterface
public interface SubValider<T>
{
	public Predicate<T> getSubValider();
}
