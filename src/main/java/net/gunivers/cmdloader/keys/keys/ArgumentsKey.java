package net.gunivers.cmdloader.keys.keys;

import java.util.function.Predicate;

import net.gunivers.cmdloader.keys.Key;
import net.gunivers.cmdloader.keys.structure.abstracts.ArrayKey;
import net.gunivers.cmdloader.keys.structure.classes.Compound;
import net.gunivers.cmdloader.keys.structure.interfaces.KeyContainer;

/**
 * 
 * @author A~Z
 *
 */
@SuppressWarnings("rawtypes")
public class ArgumentsKey extends ArrayKey<Compound<ArgumentsKey>> implements KeyContainer
{
	public ArgumentsKey()
	{
		super("arguments", null, false);
	}

	@Override
	public Predicate<Compound<ArgumentsKey>[]> getSubValider()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyInstance<Compound<ArgumentsKey>[]> parse(String value)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean trigger(Compound<ArgumentsKey>[] value, KeyInstance<Compound<ArgumentsKey>[]> instance)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Key[] getNecessaryKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Key[] getOptionalKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
