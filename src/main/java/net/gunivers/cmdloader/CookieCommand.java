package net.gunivers.cmdloader;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.ArgumentCommandNode;

public class CookieCommand
{
	CommandDispatcher<Sender> dispatcher = new CommandDispatcher<>();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CookieCommand()
	{	
		dispatcher.register(LiteralArgumentBuilder.literal("cookie")
			.then
			(RequiredArgumentBuilder.argument("nb", IntegerArgumentType.integer(0, 128)))
				.executes((CommandContext<Object> c) ->
				{
					((Sender) c.getSource()).give(Item.COOKIE, IntegerArgumentType.getInteger(c, "nb"));
					return 0;
				})
		);
	}
}

class Sender
{
	public void give(Item item, int nb) {}
}

enum Item
{
	COOKIE,
}