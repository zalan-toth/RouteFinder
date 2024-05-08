package net.pyel.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomGraph {
	private Map<Coordinate, CustomNode> nodes; // Storing nodes by an identifier
	private List<CustomEdge> edges; // Storing list of edges

	public CustomGraph() {
		nodes = new HashMap<>();
		edges = new ArrayList<>();
	}

	// Adds a node to the graph using a unique identifier
	public void addNode(Coordinate cd, CustomNode node) {
		if (!nodes.containsKey(cd)) {
			nodes.put(cd, node);
		}
	}

	// Adds an edge to the graph
	public void addEdge(CustomEdge edge) {
		if (nodes.containsValue(edge.getSource()) && nodes.containsValue(edge.getDestination())) {
			edges.add(edge);
		} else {
			throw new IllegalArgumentException("Both nodes of the edge must be added to the graph before adding the edge.");
		}
	}

	// Gets a node by its identifier
	public CustomNode getNode(int id) {
		return nodes.get(id);
	}

	// Gets all nodes
	public List<CustomNode> getNodes() {
		return new ArrayList<>(nodes.values());
	}

	// Gets all edges
	public List<CustomEdge> getEdges() {
		return edges;
	}

	// Removes a node by its identifier
	public void removeNode(Coordinate cd) {
		if (nodes.containsKey(cd)) {
			CustomNode toRemove = nodes.remove(cd);
			edges.removeIf(edge -> edge.getSource().equals(toRemove) || edge.getDestination().equals(toRemove));
		}
	}

	// Removes an edge from the graph
	public void removeEdge(CustomEdge edge) {
		edges.remove(edge);
	}
}
