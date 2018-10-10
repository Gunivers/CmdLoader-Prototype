package net.gunivers.cmdloader.keys;

import java.util.function.Predicate;

public class CompoundKey extends Key<Key<?>[]>
{

	public CompoundKey(String name, Key<?>[] defaultValue, boolean singleton) {
		super(name, defaultValue, singleton);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Predicate<Key<?>[]> getValider() {
		// TODO Auto-generated method stub
		return null;
	}

}
