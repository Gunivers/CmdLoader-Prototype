package net.gunivers.cmdloader.minecraft_test;

import java.util.ArrayList;
import java.util.HashMap;

public class Entity {
	
	private HashMap<String, Integer> scores = new HashMap<>();
	private ArrayList<String> tags = new ArrayList<>();
	private EntityList entityType;
	private String name;
	
	public Entity(EntityList entityType) {
		this.entityType = entityType;
	}
	
	public void addTag(String tag) {
		if(!tags.contains(tag))
			tags.add(tag);
	}
	
	public void removeTag(String tag) {
		tags.remove(tag);
	}
	
	public ArrayList<String> getTag() {
		return tags;
	}
	
	public EntityList getEntityType() {
		return entityType;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addAtScore(String scoreboard, int score) {
		scores.put(scoreboard, scores.getOrDefault(scoreboard, 0) + score);
	}
	
	public void removeAtScore(String scoreboard, int score) {
		addAtScore(scoreboard, -score);
	}
	
	public void setScore(String scoreboard, int score) {
		scores.put(scoreboard, score);
	}
	
	public int getScore(String scoreboard) {
		return(scores.getOrDefault(scoreboard, null));
	}
	
	public HashMap<String, Integer> getScores() {
		return scores;
	}
}
