import net.pyel.BaseController;
import net.pyel.models.Coordinate;
import net.pyel.models.CustomEdge;
import net.pyel.models.CustomGraph;
import net.pyel.models.CustomNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


@Testable
class MainTests {
	Coordinate[] coordinates;
	boolean[] valid;
	boolean[] valid2;
	Random rnd = new Random();
	int width = 4;
	CustomGraph customGraph = new CustomGraph();
	CustomGraph customGraph2 = new CustomGraph();


	//TODO IMPORTANT: Change default value of the width in the Coordinate class from 512 to 4 when doing unit testing
	@BeforeEach
	void setUp() {
		int size = width * width;
		coordinates = new Coordinate[size];
		valid = new boolean[]{false, false, false, true,  //y = 0
				true, true, true, true,   //y = 1
				false, false, false, true,//y = 2
				true, true, true, true};  //y = 3
		valid2 = new boolean[]{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};

		for (int v = 0; v < coordinates.length; v++) {
			coordinates[v] = new Coordinate(v);
			coordinates[v].setWidth(width); //FORCE WIDTH
		}
		customGraph = BaseController.processImageToGraph(coordinates, valid, width);
		customGraph2 = BaseController.processImageToGraph(coordinates, valid2, width);

	}

	@Test
	void checkImageProcess() {
		assertNull(customGraph.getNode(new Coordinate(1, 2)));
		boolean result = false;
		for (CustomEdge edge : customGraph.getEdges()) {
			if (Objects.equals(edge.getSource().getX(), 3) && Objects.equals(edge.getSource().getY(), 0)) {

				if (Objects.equals(edge.getDestination().getX(), 3) && Objects.equals(edge.getDestination().getY(), 1)) {
					result = true;
				}
			}
		}
		assertTrue(result); //Should be true as they're neighboring correct pixels!

		boolean result2 = false;
		for (CustomEdge edge : customGraph.getEdges()) {
			if (Objects.equals(edge.getSource().getX(), 3) && Objects.equals(edge.getSource().getY(), 0)) {

				if (Objects.equals(edge.getDestination().getX(), 3) && Objects.equals(edge.getDestination().getY(), 2)) {
					result2 = true;
				}
			}
		}
		assertFalse(result2); //Correct pixels but they're not neighbords, shouldn't have a connection between them
	}

	@Test
	void checkBFS() {
		List<CustomNode> routeTaken = customGraph.findShortestPathBFS(customGraph.getNode(new Coordinate(3, 0)), customGraph.getNode(new Coordinate(0, 3)));
		assertEquals(7, routeTaken.size()); //Route length should be 7 including start and endpoints
		assertEquals(routeTaken.get(0), customGraph.getNode(new Coordinate(3, 0))); //STARTING POINT
		assertEquals(routeTaken.get(1), customGraph.getNode(new Coordinate(3, 1)));
		assertEquals(routeTaken.get(2), customGraph.getNode(new Coordinate(3, 2)));
		assertEquals(routeTaken.get(3), customGraph.getNode(new Coordinate(3, 3)));
		assertEquals(routeTaken.get(4), customGraph.getNode(new Coordinate(2, 3)));
		assertEquals(routeTaken.get(5), customGraph.getNode(new Coordinate(1, 3)));
		assertEquals(routeTaken.get(6), customGraph.getNode(new Coordinate(0, 3))); //ENDPOINT
	}

	@Test
	void addEdge() {
		customGraph.addEdgeWithCheck(new CustomEdge(customGraph.getNode(new Coordinate(0, 0)), customGraph.getNode(new Coordinate(1, 0)))); //Nodes don't exist, so it shouldn't add it

		boolean result = false;
		for (CustomEdge edge : customGraph.getEdges()) {
			if (Objects.equals(edge.getSource().getX(), 0) && Objects.equals(edge.getSource().getY(), 0)) {
				result = true;
			}
		}
		assertFalse(result);


		customGraph.getNode(new Coordinate(3, 0)).setName("Start");
		customGraph.getNode(new Coordinate(0, 3)).setName("End");
		customGraph.addEdgeWithCheck(new CustomEdge(customGraph.getNode(new Coordinate(3, 0)), customGraph.getNode(new Coordinate(0, 3)))); //It should add the edge

		boolean result2 = false;
		for (CustomEdge edge : customGraph.getEdges()) {
			if (Objects.equals(edge.getSource().getName(), "Start") && Objects.equals(edge.getDestination().getName(), "End")) {
				result2 = true;
			}
		}
		assertTrue(result2); //It should find the edge containing the 2 points and result2 should be true.
	}

	@Test
	void checkCoordinateManipulationAndCreation() {
		System.out.println("Checking Coordinate calculation");
		Coordinate cordToCheck = new Coordinate(0);
		cordToCheck.setWidth(4);
		assertEquals(0, cordToCheck.getX());
		assertEquals(0, cordToCheck.getY());
		assertEquals(0, cordToCheck.getValue());
		assertEquals(4, cordToCheck.getWidth());
		cordToCheck.setValue(2);
		assertEquals(2, cordToCheck.getX());
		assertEquals(0, cordToCheck.getY());
		assertEquals(2, cordToCheck.getValue());
		assertEquals(4, cordToCheck.getWidth());
		cordToCheck.setValue(6);
		assertEquals(2, cordToCheck.getX());
		assertEquals(1, cordToCheck.getY());
		assertEquals(6, cordToCheck.getValue());
		assertEquals(4, cordToCheck.getWidth());
		cordToCheck.setValue(15);
		assertEquals(3, cordToCheck.getX());
		assertEquals(3, cordToCheck.getY());
		assertEquals(15, cordToCheck.getValue());
		assertEquals(4, cordToCheck.getWidth());
		cordToCheck.setValue(16);
		assertEquals(0, cordToCheck.getX());
		assertEquals(4, cordToCheck.getY());
		assertEquals(16, cordToCheck.getValue());
		assertEquals(4, cordToCheck.getWidth());
	}

	@Test
	void checkGetAndAddNode() {
		assertNull(customGraph.getNode(new Coordinate(0, 0))); //This node shouldn't exist
		assertEquals(3, customGraph.getNode(new Coordinate(3, 0)).getX()); //This one should!
		assertEquals(0, customGraph.getNode(new Coordinate(3, 0)).getY());
		customGraph.addNode(new Coordinate(0, 0), new CustomNode(0, 0));
		assertEquals(0, customGraph.getNode(new Coordinate(0, 0)).getX()); //One it was added!
		assertEquals(0, customGraph.getNode(new Coordinate(0, 0)).getY());
	}


}