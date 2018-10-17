package net.gunivers.cmdloader.minecraft_test;

public class Player extends Entity {

	private boolean isOp;
	
	public Player(String name, boolean isOp) {
		super(EntityList.player);
		setName(name);
	}
	
	public boolean isOp() {
		return isOp;
	}
	
	public void setOp(boolean isOp) {
		this.isOp = isOp;
	}
	
}
