package net.gunivers.cmdloader;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class SelectorArgumentType<S> implements ArgumentType<S>
{
	private String selector;
	
	private SelectorArgumentType(String selector)
	{
		this.selector = selector;
	}
	
	public static <S> SelectorArgumentType<S> selector(String selector, Class<S> sourceClass)
	{
		
		
		return null;
	}
	
	@Override
	public S parse(StringReader reader) throws CommandSyntaxException
	{
		return null;
	}
}
