package net.gunivers.cmdloader.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

import net.gunivers.cmdloader.Main;
import net.gunivers.cmdloader.nodes.CoordinateArgumentType;

public class TestCommand {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TestCommand() {
		Main.dispatcher.register((LiteralArgumentBuilder)LiteralArgumentBuilder.literal("test").then(
				RequiredArgumentBuilder.argument("x", CoordinateArgumentType.coordinate()).executes( c -> {
					System.out.println("Valide !");
					return 1;
		})));
	}
	
}
