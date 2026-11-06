package model;

public class Parcel {
	private String id;
	private int weight;   // kg

	public Parcel(String i, int w) {
		id = i;
		weight = w;
	}

	public String getId() { return id; }
	public int getWeight() { return weight; }
}
