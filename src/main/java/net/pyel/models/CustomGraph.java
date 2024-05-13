package net.pyel.models;

import java.util.*;

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
		edges.add(edge); //THE CONTAINSVALUE SEARCH BELOW SLOWS EVERYTHING DOWN, but it works btw if you need check
		/*if (nodes.containsValue(edge.getSource()) && nodes.containsValue(edge.getDestination())) {
			edges.add(edge);
		} else {
			throw new IllegalArgumentException("Both nodes of the edge must be added to the graph before adding the edge.");
		}*/
	}

	// Adds an edge to the graph
	public void addEdgeWithCheck(CustomEdge edge) { //This method does have a check, that slows everything down, but it's safer to use this.
		if (nodes.containsValue(edge.getSource()) && nodes.containsValue(edge.getDestination())) {
			edges.add(edge);
		} else {
			throw new IllegalArgumentException("Both nodes of the edge must be added to the graph before adding the edge.");
		}
	}

	// Gets a node by its identifier
	public CustomNode getNode(Coordinate coordinate) {
		return nodes.get(coordinate);
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


	public List<CustomNode> findShortestPathBFS(CustomNode startNode, CustomNode targetNode) {
		Map<CustomNode, CustomNode> parentMap = new HashMap<>();
		Set<CustomNode> visited = new HashSet<>();
		Queue<CustomNode> queue = new LinkedList<>();

		queue.add(startNode);
		visited.add(startNode);
		parentMap.put(startNode, null);

		while (!queue.isEmpty()) {
			CustomNode currentNode = queue.poll();

			if (currentNode.equals(targetNode)) {
				return constructPath(parentMap, targetNode);
			}

			for (CustomEdge edge : edges) {
				CustomNode adjacentNode = null;
				if (edge.getSource().equals(currentNode)) {
					adjacentNode = edge.getDestination();
				} else if (edge.getDestination().equals(currentNode)) {
					adjacentNode = edge.getSource();
				}

				if (adjacentNode != null && !visited.contains(adjacentNode)) {
					queue.add(adjacentNode);
					visited.add(adjacentNode);
					parentMap.put(adjacentNode, currentNode);
				}
			}
		}
		return Collections.emptyList(); // Return empty list when no path found
	}

	// Construct the path
	private List<CustomNode> constructPath(Map<CustomNode, CustomNode> parentMap, CustomNode targetNode) {
		List<CustomNode> path = new LinkedList<>();
		for (CustomNode at = targetNode; at != null; at = parentMap.get(at)) {
			path.add(at);
		}
		Collections.reverse(path); //reverse it to correct the order!
		return path;
	}

}
