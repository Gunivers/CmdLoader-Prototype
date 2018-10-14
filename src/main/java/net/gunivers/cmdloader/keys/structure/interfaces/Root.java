package net.gunivers.cmdloader.keys.structure.interfaces;

import com.mojang.brigadier.CommandDispatcher;

public interface Root extends KeyContainer
{
	public <S> void rootAction(CommandDispatcher<S> dispatcher);
}