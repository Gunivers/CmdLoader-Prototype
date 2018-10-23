package net.gunivers.cmdloader.keys.keys;

import java.util.function.Predicate;

import com.mojang.brigadier.CommandDispatcher;

import net.gunivers.cmdloader.keys.Key;
import net.gunivers.cmdloader.keys.structure.abstracts.CompoundKey;
import net.gunivers.cmdloader.keys.structure.classes.Compound;
import net.gunivers.cmdloader.keys.structure.classes.Context;
import net.gunivers.cmdloader.keys.structure.enums.ValueType;
import net.gunivers.cmdloader.keys.structure.interfaces.Root;

/**
 * 
 * @author A~Z
 *
 */
public class CommandKey extends CompoundKey implements Root
{
	protected CommandKey()
	{
		super("command", true);
	}

	@Override
	public Predicate<Compound<CompoundKey>> getSubValider() {return (Compound<CompoundKey> keys) -> this.validateKeys(keys.getCompound());}

	@Override
	public KeyInstance<Compound<CompoundKey>> parse(String value)
	{
		Compound<CompoundKey> c = new Compound<CompoundKey>(this);
		c.parse(value);
		
		return this.newInstance(c, new Context(this, ValueType.Compound, 0, value));
	}

	@Override
	public boolean trigger(Compound<CompoundKey> value, KeyInstance<Compound<CompoundKey>> instance)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S> void rootAction(CommandDispatcher<S> dispatcher, String raw)
	{
	
		
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
