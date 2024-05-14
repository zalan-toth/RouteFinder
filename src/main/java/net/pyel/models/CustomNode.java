package net.pyel.models;

public class CustomNode {

	private int x;
	private int y;
	private String name;
	private boolean cultural = false;
	private CustomNode parent;

	@Override
	public String toString() {
		return "CustomNode{" +
				"x=" + x +
				", y=" + y +
				", name='" + name + '\'' +
				", cultural=" + cultural +
				'}';
	}

	public CustomNode(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public CustomNode(int x, int y, String name) {
		this.x = x;
		this.y = y;
		cultural = true;
		this.name = name;
	}
	public CustomNode getParent() {
		return parent;
	}

	public void setParent(CustomNode parent) {
		this.parent = parent;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCultural() {
		return cultural;
	}

	public void setCultural(boolean cultural) {
		this.cultural = cultural;
	}
}
