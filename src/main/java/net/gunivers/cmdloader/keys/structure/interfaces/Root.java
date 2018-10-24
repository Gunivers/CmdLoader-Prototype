package net.gunivers.cmdloader.keys.structure.interfaces;

import com.mojang.brigadier.CommandDispatcher;

import net.gunivers.cmdloader.keys.structure.exceptions.KeyNotFoundException;

/**
 * 
 * @author A~Z
 *
 */
public interface Root extends KeyContainer
{
	public default <S> void trigger(CommandDispatcher<S> dispatcher, String raw) throws KeyNotFoundException
	{
		
	}
	
	@Deprecated
	public <S> void rootAction(CommandDispatcher<S> dispatcher, String raw) throws KeyNotFoundException;
}