package net.gunivers.cmdloader.keys.keys;

import java.util.function.Predicate;

import net.gunivers.cmdloader.keys.structure.abstracts.SimpleValueKey;

public class NameKey extends SimpleValueKey<String> {

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
	public Predicate<String> getValider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean trigger(String value, KeyInstance<String> instance) {
		// TODO Auto-generated method stub
		return false;
	}

}
