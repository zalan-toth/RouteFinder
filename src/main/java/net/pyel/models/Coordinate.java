package net.pyel.models;

public class Coordinate {
	private int width = 4; //CHANGE THIS TO 4 WHEN DOING JUnit Testing!! 512 is the default
	private int value;
	private int x;
	private int y;

	@Override
	public String toString() {
		return "Coordinate{" +
				"x=" + x +
				", y=" + y +
				'}';
	}

	public Coordinate(int value) {
		this.value = value;
		x = value % width;
		y = value / width;
	}


	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		x = value % width;
		y = value / width;
	}

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
		value = y * width + x;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		x = value % width;
		y = value / width;
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
		value = y * width + x;
	}

	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Coordinate that = (Coordinate) o;

		if (x != that.x) return false;
		return y == that.y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}

}
