package net.gunivers.cmdloader;

import com.mojang.brigadier.*;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class Main
{
	public static void main(String[] args) throws CommandSyntaxException
	{
		System.out.println("a");
		System.out.println(Main.class.getResource("/test.json"));
		
		ParseResults<Sender> parse = CookieCommand.getDispatcher().parse("cookie 12", new Sender());
		CookieCommand.getDispatcher().execute(parse);
	}
	
	public static void yo (int i) {}
}
