package net.gunivers.cmdloader;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

public class CloneAndRotate {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CloneAndRotate() {
		Main.dispatcher.register((LiteralArgumentBuilder)LiteralArgumentBuilder.literal("cloneandrotate").then(
				RequiredArgumentBuilder.argument("x1", IntegerArgumentType.integer()).then(
						RequiredArgumentBuilder.argument("y1", IntegerArgumentType.integer()).then(
								RequiredArgumentBuilder.argument("z1", IntegerArgumentType.integer()).then(
										RequiredArgumentBuilder.argument("x2", IntegerArgumentType.integer()).then(
												RequiredArgumentBuilder.argument("y2", IntegerArgumentType.integer()).then(
														RequiredArgumentBuilder.argument("z2", IntegerArgumentType.integer()).then(
																RequiredArgumentBuilder.argument("x", IntegerArgumentType.integer()).then(
																		RequiredArgumentBuilder.argument("y", IntegerArgumentType.integer()).then(
																				RequiredArgumentBuilder.argument("z", IntegerArgumentType.integer()).then(
																						RequiredArgumentBuilder.argument("angle", IntegerArgumentType.integer(0, 360)).executes( c -> {
																							System.out.println("Zone clonée !");
																							return 1;
		}))))))))))));
	}
	
}
