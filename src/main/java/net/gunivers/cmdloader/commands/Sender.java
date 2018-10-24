package net.gunivers.cmdloader.commands;

import java.util.HashMap;

public class Sender {
	private HashMap<Item, Integer> inventory = new HashMap<>();

	public void give(Item item, int nb) {
		if (inventory.containsKey(item))
			inventory.replace(item, inventory.get(item) + nb);
		else
			inventory.put(item, nb);
	}

}