package net.pyel.models;

public class CustomEdge {
	private CustomNode source;
	private CustomNode destination;

	public CustomEdge(CustomNode source, CustomNode destination) {
		this.source = source;
		this.destination = destination;
	}

	public CustomNode getSource() {
		return source;
	}

	public CustomNode getDestination() {
		return destination;
	}
}
