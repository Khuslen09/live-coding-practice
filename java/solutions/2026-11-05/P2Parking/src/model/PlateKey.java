package model;

public class PlateKey implements Key {
	private String plate;

	public PlateKey(String p) {
		plate = p;
	}

	public String getPlate() {
		return plate;
	}

	public boolean equals(Key k) {
		if (k instanceof PlateKey)
			return plate.equals(((PlateKey) k).getPlate());
		return false;
	}
}
