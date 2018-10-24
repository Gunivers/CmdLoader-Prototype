package net.gunivers.cmdloader.nodes;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class CoordinateArgumentType implements ArgumentType<String> {
	
	public enum CoordinatePrefix {
		
		RELATIVE("~"),
		LOCAL("\\^"),
		RELATIVE_AND_LOCAL("\\^|~"),
		EMPTY("");
		
		private final String prefix;
		
		private CoordinatePrefix(String s) {
			prefix = s;
		}
		
		public String toString() {
			return prefix;
		}
	}
	
	private final double min;
	private final double max;
	private final CoordinatePrefix prefix;
	
	private CoordinateArgumentType(double min, double max, CoordinatePrefix prefix) {
		this.min = min;
		this.max = max;
		this.prefix = prefix;
	}
	
	public static CoordinateArgumentType coordinate() {
		return coordinate(Double.MIN_VALUE);
	}
	
	public static CoordinateArgumentType coordinate(Double min) {
		return coordinate(min, Double.MAX_VALUE);
	}
	
	public static CoordinateArgumentType coordinate(Double min, Double max) {
		return coordinate(min, max, CoordinatePrefix.RELATIVE_AND_LOCAL);
	}
	
	public static CoordinateArgumentType coordinate(Double min, Double max, CoordinatePrefix prefix) {
		return new CoordinateArgumentType(min, max, prefix);
	}

	@Override
	public <S> String parse(StringReader reader) throws CommandSyntaxException {
		String prefixe = String.valueOf(reader.read());
		String arg = "";
		if(Character.isDigit(prefixe.charAt(0)) || prefixe.matches("|\\.|-"))
			reader.setCursor(reader.getCursor() - 1);
		else if(!prefixe.toString().matches(prefix.toString()))
			throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.doubleTooLow().createWithContext(reader, prefixe, arg);
		return arg + DoubleArgumentType.doubleArg().parse(reader);
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	public CoordinatePrefix getPrefix() {
		return prefix;
	}
	
	
	

}
