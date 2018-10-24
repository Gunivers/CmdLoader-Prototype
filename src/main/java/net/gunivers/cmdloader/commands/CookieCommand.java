package net.gunivers.cmdloader.commands;

import java.util.HashMap;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.gunivers.cmdloader.Main;

/**
 * 
 * @author Oromis
 * @author A~Z
 *
 */
public class CookieCommand {
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CookieCommand() {
		Main.dispatcher.register((LiteralArgumentBuilder) LiteralArgumentBuilder.literal("cookie")
				.then(RequiredArgumentBuilder.argument("nb", IntegerArgumentType.integer(0, 128))
				.executes((CommandContext<Object> c) -> {
					((Sender) c.getSource()).give(Item.COOKIE, IntegerArgumentType.getInteger(c, "nb"));
					return 1;
				})));
	}
}

enum Item {
	COOKIE,
}