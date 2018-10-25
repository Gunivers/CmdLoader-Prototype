package net.gunivers.cmdloader.keys.structure.interfaces;

import com.mojang.brigadier.CommandDispatcher;

import net.gunivers.cmdloader.keys.structure.exceptions.KeyNotFoundException;

/**
 * 
 * @author A~Z
 *
 */
@FunctionalInterface
public interface Root
{
	public <S> void rootAction(CommandDispatcher<S> dispatcher, String raw) throws KeyNotFoundException;
}