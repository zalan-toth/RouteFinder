import net.pyel.models.Coordinate;
import net.pyel.models.CustomEdge;
import net.pyel.models.CustomGraph;
import net.pyel.models.CustomNode;
import org.junit.platform.commons.annotation.Testable;

import java.util.List;


@Testable
class MainTests {
	public static void main(String[] args) {
		CustomGraph graph = new CustomGraph();

		CustomNode nodeA = new CustomNode(0, 0, "A");
		CustomNode nodeB = new CustomNode(1, 0, "B");
		CustomNode nodeC = new CustomNode(2, 0, "C");
		CustomNode nodeD = new CustomNode(3, 0, "D");
		CustomNode nodeE = new CustomNode(4, 0, "E");

		Coordinate coordA = new Coordinate(0, 0);
		Coordinate coordB = new Coordinate(1, 0);
		Coordinate coordC = new Coordinate(2, 0);
		Coordinate coordD = new Coordinate(3, 0);
		Coordinate coordE = new Coordinate(4, 0);

		graph.addNode(coordA, nodeA);
		graph.addNode(coordB, nodeB);
		graph.addNode(coordC, nodeC);
		graph.addNode(coordD, nodeD);
		graph.addNode(coordE, nodeE);

		graph.addEdge(new CustomEdge(nodeA, nodeB));
		graph.addEdge(new CustomEdge(nodeA, nodeC));
		graph.addEdge(new CustomEdge(nodeA, nodeD));
		graph.addEdge(new CustomEdge(nodeC, nodeD));

		// Perform BFS
		List<CustomNode> bfsResult = graph.findShortestPathBFS(nodeD, nodeA);

		// Print the BFS result
		for (CustomNode node : bfsResult) {
			System.out.println(node.getName());
		}
	}

}