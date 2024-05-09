package net.pyel.models;

public class CustomEdge {
	private CustomNode source;
	private CustomNode destination;
	private int distance;

	public CustomEdge(CustomNode source, CustomNode destination) {
		this.source = source;
		this.destination = destination;
	}

	public CustomEdge(CustomNode source, CustomNode destination, int distance) {
		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}

	public CustomNode getSource() {
		return source;
	}

	public CustomNode getDestination() {
		return destination;
	}

	public int getDistance() {
		return distance;
	}
}
