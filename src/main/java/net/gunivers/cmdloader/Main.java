package net.gunivers.cmdloader;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class Main
{
	
	public static CommandDispatcher<Sender> dispatcher = new CommandDispatcher<>();
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws CommandSyntaxException
	{
		/*System.out.println("");
		System.out.println(Main.class.getResource("/test.json"));*/
		CloneAndRotate cc = new CloneAndRotate();
		ParseResults<Sender> parse = dispatcher.parse("cloneandrotate 1 2 3 4 5 6 7 8 9 360", new Sender());
		dispatcher.execute(parse);
	}
	
	public static void yo (int i) {}
}
