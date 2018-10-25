package net.gunivers.cmdloader.keys.keys;

import java.util.function.Predicate;

import com.mojang.brigadier.CommandDispatcher;

import net.gunivers.cmdloader.keys.structure.abstracts.SimpleValueKey;

public class NameKey extends SimpleValueKey<String>
{
	public NameKey()
	{
		super("name", null, true);
	}

	@Override
	public KeyInstance<String> parse(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate<String> getValider()
	{
		return (String s) -> true;
	}

	@Override
	public <S> boolean trigger(CommandDispatcher<S> dispatcher, KeyInstance<String> instance)
	{
		return false;
	}
}
