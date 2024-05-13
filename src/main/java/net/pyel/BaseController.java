package net.pyel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.pyel.models.Coordinate;
import net.pyel.models.CustomEdge;
import net.pyel.models.CustomGraph;
import net.pyel.models.CustomNode;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Base Controller - Manages all windows with fxml
 *
 * @author Zalán Tóth
 */
public class BaseController implements Initializable {


	//██████╗░███████╗░█████╗░██╗░░░░░░█████╗░██████╗░███████╗
	//██╔══██╗██╔════╝██╔══██╗██║░░░░░██╔══██╗██╔══██╗██╔════╝
	//██║░░██║█████╗░░██║░░╚═╝██║░░░░░███████║██████╔╝█████╗░░
	//██║░░██║██╔══╝░░██║░░██╗██║░░░░░██╔══██║██╔══██╗██╔══╝░░
	//██████╔╝███████╗╚█████╔╝███████╗██║░░██║██║░░██║███████╗
	//╚═════╝░╚══════╝░╚════╝░╚══════╝╚═╝░░╚═╝╚═╝░░╚═╝╚══════╝
	//private static PanelAPI panelAPI = new PanelAPI(null);
	//private CustomList<Machine> machines;
	//private CustomList<Game> games = new CustomList<>();
	Coordinate[] coordinates;
	boolean[] valid;
	Coordinate point1 = new Coordinate(-1);
	Coordinate point2 = new Coordinate(-1);
	Coordinate point3 = new Coordinate(-1);
	Stage popupstage = new Stage();
	Parent popuproot;
	Scene popupScene;
	Stage terminalStage = new Stage();
	Parent terminalRoot;
	Scene terminalScene;
	boolean setRun = true;
	LandmarksAPI landmarksAPI;
	CustomGraph customGraph = new CustomGraph();
	Image bwMapImage;
	Image mapImage;

	WritableImage wi;
	PixelWriter pw;

	//███████╗██╗░░██╗███╗░░░███╗██╗░░░░░░░░░░░░░██████╗░███████╗░█████╗░██╗░░░░░░█████╗░██████╗░███████╗
	//██╔════╝╚██╗██╔╝████╗░████║██║░░░░░░░░░░░░░██╔══██╗██╔════╝██╔══██╗██║░░░░░██╔══██╗██╔══██╗██╔════╝
	//█████╗░░░╚███╔╝░██╔████╔██║██║░░░░░░░░░░░░░██║░░██║█████╗░░██║░░╚═╝██║░░░░░███████║██████╔╝█████╗░░
	//██╔══╝░░░██╔██╗░██║╚██╔╝██║██║░░░░░░░░░░░░░██║░░██║██╔══╝░░██║░░██╗██║░░░░░██╔══██║██╔══██╗██╔══╝░░
	//██║░░░░░██╔╝╚██╗██║░╚═╝░██║███████╗░░░░░░░░██████╔╝███████╗╚█████╔╝███████╗██║░░██║██║░░██║███████╗
	//╚═╝░░░░░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚══════╝░░░░░░░░╚═════╝░╚══════╝░╚════╝░╚══════╝╚═╝░░╚═╝╚═╝░░╚═╝╚══════╝
	@FXML
	private ImageView overlayImageView = new ImageView();
	@FXML
	private TreeView<Coordinate> treeView = new TreeView<>();
	@FXML
	private ImageView mapImageView = new ImageView();
	@FXML
	private Text processingText = new Text();
	@FXML
	private Text pixelUnitsText = new Text();
	@FXML
	private Text point1Text = new Text();
	@FXML
	private Text point2Text = new Text();
	@FXML
	private Text point3Text = new Text();
	@FXML
	private CheckBox point1CheckBox = new CheckBox();
	@FXML
	private CheckBox point2CheckBox = new CheckBox();
	@FXML
	private CheckBox point3CheckBox = new CheckBox();
	@FXML
	private ImageView originalMapImageView = new ImageView();
	@FXML
	private ImageView bwMapImageView = new ImageView();
	private static final String MAP_IMAGE_PATH = "/map2.jpg"; //path to file!
	private static final String BWMAP_IMAGE_PATH = "/bwmap2.png"; //path to black&white file!

	public BaseController() {
		landmarksAPI = BackgroundController.getLandmarksAPI();
		//panelAPI = BackgroundController.getPanelAPI();
		//machines = panelAPI.panel.getMachines();
		//games = panelAPI.panel.getGames();
	}


	/**
	 * Call this method if you wanna close the application
	 */
	@FXML
	private void quit() {
		javafx.application.Platform.exit();
	}

	@FXML
	private void openImage() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG, PNG & GIF Images", "jpg", "gif", "png");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			/*selectedFile = chooser.getSelectedFile();
			try {
				image = convertToFxImage(ImageIO.read(selectedFile));
			} catch (IOException e) {
			}
			image = new Image(selectedFile.toURI().toString(), 512, 512, false, false); //downscale
			id = new ImageData(image);
			ip = new ImageProcess(image);
			ip.processMe();
			ib.setImage(ip.getImage());
			ib.setUp();


			labeler = new Labeler(ip.getSizeOfSet());

			originalImageView.setImage(id.getOriginalImage());
			imageView.setImage(id.getImageToEdit());
			grayImage = id.getGrayScaleImage();
			grayImageView.setImage(grayImage);
			redImageView.setImage(id.getOnlyRedChannelImage());
			greenImageView.setImage(id.getOnlyGreenChannelImage());
			blueImageView.setImage(id.getOnlyBlueChannelImage());
			//redChart = createBarChart(id.getRedFrequency(), "Red");*/

			//selectedPillTypes = new ArrayList<>();
		}
	}


	private static Image convertToFxImage(BufferedImage image) {
		WritableImage wr = null;
		if (image != null) {
			wr = new WritableImage(image.getWidth(), image.getHeight());
			PixelWriter pw = wr.getPixelWriter();
			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					pw.setArgb(x, y, image.getRGB(x, y));
				}
			}
		}

		return new ImageView(wr).getImage();
	}


	//░██████╗██╗░░░██╗███╗░░██╗░█████╗░
	//██╔════╝╚██╗░██╔╝████╗░██║██╔══██╗
	//╚█████╗░░╚████╔╝░██╔██╗██║██║░░╚═╝
	//░╚═══██╗░░╚██╔╝░░██║╚████║██║░░██╗
	//██████╔╝░░░██║░░░██║░╚███║╚█████╔╝
	//╚═════╝░░░░╚═╝░░░╚═╝░░╚══╝░╚════╝░
	@FXML
	private void refresh() {
		initialize(null, null);
	}

	private void createGraph() {
		mapImage = new Image(getClass().getResourceAsStream(MAP_IMAGE_PATH), 512, 512, false, false);
		bwMapImage = new Image(getClass().getResourceAsStream(BWMAP_IMAGE_PATH), 512, 512, false, false);
		int coordSize = (int) (mapImage.getWidth() * mapImage.getHeight());
		coordinates = new Coordinate[coordSize];
		valid = new boolean[coordSize];

		wi = new WritableImage((int) mapImage.getWidth(), (int) mapImage.getHeight());
		pw = wi.getPixelWriter();
		System.out.println("MAP SIZE = " + coordinates.length);
		mapImageView.setImage(mapImage);
		originalMapImageView.setImage(mapImage);
		bwMapImageView.setImage(bwMapImage);

		PixelReader pr = bwMapImage.getPixelReader();
		for (int x = 0; x < bwMapImage.getWidth(); x++) {
			for (int y = 0; y < bwMapImage.getHeight(); y++) {
				int v = pr.getArgb(x, y);
				Coordinate coord = new Coordinate(x, y);
				if (v == 0xFFFFFFFF) {
					valid[coord.getValue()] = true;
				} else {
					valid[coord.getValue()] = false;
				}
				coordinates[coord.getValue()] = coord;
			}
		}
		for (Coordinate coordinate : coordinates) {
			if (valid[coordinate.getValue()]) {
				customGraph.addNode(coordinate, new CustomNode(coordinate.getX(), coordinate.getY()));
			}
		}

		for (Coordinate coordinate : coordinates) {
			if (valid[coordinate.getValue()]) {
				//CONNECTING NODE TO THE ABOVE NODE USING AN EDGE! //TODO ABOVE
				if ((coordinate.getY() != 0)) {
					Coordinate coordinateAbove = new Coordinate(coordinate.getX(), coordinate.getY() - 1);
					if (valid[coordinateAbove.getValue()]) {
						CustomNode nodeAbove = customGraph.getNode(coordinateAbove);
						customGraph.addEdge(new CustomEdge(customGraph.getNode(coordinate), nodeAbove));
					}
				}
				//CONNECTING NODE TO THE BELOW NODE USING AN EDGE! //TODO BELOW
				if ((coordinate.getY() != (mapImage.getHeight() - 1))) {
					Coordinate coordinateBelow = new Coordinate(coordinate.getX(), coordinate.getY() + 1);
					if (valid[coordinateBelow.getValue()]) {
						CustomNode nodeBelow = customGraph.getNode(coordinateBelow);
						customGraph.addEdge(new CustomEdge(customGraph.getNode(coordinate), nodeBelow));
					}
				}
				//CONNECTING NODE TO THE RIGHT NODE USING AN EDGE! //TODO RIGHT
				if ((coordinate.getX() != (mapImage.getWidth() - 1))) {
					Coordinate coordinateToTheRight = new Coordinate(coordinate.getX() + 1, coordinate.getY());
					if (valid[coordinateToTheRight.getValue()]) {
						CustomNode nodeToTheRight = customGraph.getNode(coordinateToTheRight);
						customGraph.addEdge(new CustomEdge(customGraph.getNode(coordinate), nodeToTheRight));
					}
				}
				//CONNECTING NODE TO THE LEFT NODE USING AN EDGE! //TODO LEFT
				if ((coordinate.getX() != 0)) {
					Coordinate coordinateToTheLeft = new Coordinate(coordinate.getX() - 1, coordinate.getY());
					if (valid[coordinateToTheLeft.getValue()]) {
						CustomNode nodeToTheLeft = customGraph.getNode(coordinateToTheLeft);
						customGraph.addEdge(new CustomEdge(customGraph.getNode(coordinate), nodeToTheLeft));
					}
				}
			}
		}
	}

	public void doBFS() {
		//System.out.println(getImageCoordinates(point1.getX(), point1.getY()));
		CustomNode cn1 = customGraph.getNode(new Coordinate(point1.getX(), point1.getY()));
		CustomNode cn2 = customGraph.getNode(new Coordinate(point2.getX(), point2.getY()));
		System.out.println("Selected Node 1: " + cn1);
		System.out.println("Selected Node 2: " + cn2);

		List<CustomNode> bfsResult = customGraph.findShortestPathBFS(cn1, cn2);
		List<Coordinate> bfs = new ArrayList<>();
		for (CustomNode node : bfsResult) {
			System.out.print("[" + node.getX() + " " + node.getY() + "]");
			bfs.add(new Coordinate(node.getX(), node.getY()));
		}
		TreeItem<Coordinate> rootItem = new TreeItem<>(bfs.get(0));
		rootItem.setExpanded(true);
		treeView.setRoot(rootItem);

		for (Coordinate cd : bfs) {
			TreeItem<Coordinate> treeItem = new TreeItem<>(cd);
			rootItem.getChildren().add(treeItem);
		}

		for (Coordinate coordinate : coordinates) {
			if (bfs.contains(coordinate)) {
				pw.setArgb(coordinate.getX(), coordinate.getY(), 0xFF0000FF);
			}
		}
		overlayImageView.setImage(wi);

		pixelUnitsText.setText("BFS result: " + bfs.size() + " units");
	}

	public void doDFS() {
		
	}

	public void doDjiktra() {

	}

	public void resetPointSelection() {
		point1 = new Coordinate(-1);
		point1CheckBox.setSelected(false);
		point1Text.setText("-");
		point2 = new Coordinate(-1);
		point2CheckBox.setSelected(false);
		point2Text.setText("-");
		point3 = new Coordinate(-1);
		point3CheckBox.setSelected(false);
		point3Text.setText("-");
		for (Coordinate coordinate : coordinates) {
			pw.setArgb(coordinate.getX(), coordinate.getY(), 0x00000000);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (setRun) {

			createGraph();


			setupMainListener();
			setRun = false;
		}
	}

	private void setupMainListener() {

		overlayImageView.setOnMouseClicked(event -> {

			processingText.setText(" ");
			pixelUnitsText.setText("-");
			int x = (int) event.getX();
			int y = (int) event.getY();

			if (point1CheckBox.isSelected()) {
				if (valid[new Coordinate(x, y).getValue()]) {
					point1.setXY(x, y);
					point1Text.setText(point1.toString());
					point1CheckBox.setSelected(false);
				} else {
					point1Text.setText("Last point was invalid (not road)");
				}
			}

			if (point2CheckBox.isSelected()) {
				if (valid[new Coordinate(x, y).getValue()]) {
					point2.setXY(x, y);
					point2Text.setText(point2.toString());
					point2CheckBox.setSelected(false);
				} else {
					point2Text.setText("Last point was invalid (not road)");
				}
			}

			if (point3CheckBox.isSelected()) {
				if (valid[new Coordinate(x, y).getValue()]) {
					point3.setXY(x, y);
					point3Text.setText(point3.toString());
					point3CheckBox.setSelected(false);
				} else {
					point3Text.setText("Last point was invalid (not road)");
				}
			}
			for (Coordinate coordinate : coordinates) {
				pw.setArgb(coordinate.getX(), coordinate.getY(), 0x00000000);
			}
			for (Coordinate coordinate : coordinates) {
				if (coordinate.getValue() == point1.getValue() || coordinate.getValue() == point2.getValue() || coordinate.getValue() == point3.getValue()) {
					pw.setArgb(coordinate.getX(), coordinate.getY(), 0xFFFF0000);
					pw.setArgb(coordinate.getX() + 1, coordinate.getY(), 0xFFFF0000);
					pw.setArgb(coordinate.getX() - 1, coordinate.getY(), 0xFFFF0000);
					pw.setArgb(coordinate.getX(), coordinate.getY() + 1, 0xFFFF0000);
					pw.setArgb(coordinate.getX(), coordinate.getY() - 1, 0xFFFF0000);
				}
			}
			overlayImageView.setImage(wi);


			/*if (cn != null) {
				List<CustomNode> bfsResult = customGraph.bfs(cn);

				for (CustomNode node : bfsResult) {
					System.out.print("[" + node.getX() + " " + node.getY() + "]");
				}
				System.out.println("Selected Node: " + cn);
			}*/


			/*ArrayList<Coordinate> nates = new ArrayList<>();
			nates.add(new Coordinate(84, 227));
			nates.add(new Coordinate(82, 227));
			nates.add(new Coordinate(83, 227));
			landmarksAPI.landmarks.getLandmarks().put("Eiffel Tower", nates);
			try {
				landmarksAPI.save();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}*/

		});
	}

	public Coordinate getImageCoordinates(double x, double y) {


		double viewWidth = mapImageView.getFitWidth();
		double viewHeight = mapImageView.getFitHeight();

		// Actual dimensions of the image
		double actualWidth = mapImageView.getImage().getWidth();
		double actualHeight = mapImageView.getImage().getHeight();

		// Compute the scale
		double scaleX = actualWidth / viewWidth;
		double scaleY = actualHeight / viewHeight;

		// Adjusting for the actual display size within the ImageView
		double displayWidth = mapImageView.getBoundsInLocal().getWidth();
		double displayHeight = mapImageView.getBoundsInLocal().getHeight();

		// Calculate the ratio of the original image to the displayed image
		double ratioX = actualWidth / displayWidth;
		double ratioY = actualHeight / displayHeight;

		// Calculate the original coordinates of the click
		double originalX = x * ratioX;
		double originalY = y * ratioY;

		return new Coordinate((int) originalX, (int) originalY);
	}
}