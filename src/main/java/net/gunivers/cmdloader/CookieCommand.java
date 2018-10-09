package net.gunivers.cmdloader;

import java.util.HashMap;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

public class CookieCommand
{
	private static CommandDispatcher<Sender> dispatcher = new CommandDispatcher<>();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CookieCommand()
	{	
		dispatcher.register((LiteralArgumentBuilder) LiteralArgumentBuilder.literal("cookie")
			.then
			(RequiredArgumentBuilder.argument("nb", IntegerArgumentType.integer(0, 128)))
				.executes((CommandContext<Object> c) ->
				{
					((Sender) c.getSource()).give(Item.COOKIE, IntegerArgumentType.getInteger(c, "nb"));
					return 0;
				})
		);
	}
	
	public static CommandDispatcher<Sender> getDispatcher()
	{
		return dispatcher;
	}
}

class Sender
{
	private HashMap<Item, Integer> inventory = new HashMap<>();
	
	public void give(Item item, int nb)
	{
		if (inventory.containsKey(item))
			inventory.replace(item, inventory.get(item) + nb);
		else
			inventory.put(item, nb);
	}
	
}

enum Item
{
	COOKIE,
}