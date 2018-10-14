package net.gunivers.cmdloader.keys.keys;

import java.util.function.Predicate;

import com.mojang.brigadier.CommandDispatcher;

import net.gunivers.cmdloader.keys.Key;
import net.gunivers.cmdloader.keys.structure.abstracts.CompoundKey;
import net.gunivers.cmdloader.keys.structure.interfaces.Root;
import net.gunivers.cmdloader.keys.structure.types.Compound;

/**
 * 
 * @author A~Z
 *
 */
public class CommandKey extends CompoundKey implements Root
{
	public CommandKey()
	{
		super("command", true);
	}

	@Override
	public Predicate<Compound> getSubValider() { return (Compound keys) -> this.validateKeys(keys.getCompound()); }

	@Override
	public KeyInstance<Compound> parse(String value)
	{
		Compound c = new Compound();
		c.parse(value);
		
		return this.newInstance(c);
	}

	@Override
	public boolean trigger(Compound value, KeyInstance<Compound> instance)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S> void rootAction(CommandDispatcher<S> dispatcher)
	{
		//TODO : rootAction
		return;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Key[] getNecessaryKeys()
	{
		return new Key[] {KeyRegister.arguments, KeyRegister.only_op};
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Key[] getOptionalKeys()
	{
		return new Key[] {KeyRegister.description};
	}
}
