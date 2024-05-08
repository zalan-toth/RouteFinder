package net.pyel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import net.pyel.models.Coordinate;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.net.URL;
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
	Stage popupstage = new Stage();
	Parent popuproot;
	Scene popupScene;
	Stage terminalStage = new Stage();
	Parent terminalRoot;
	Scene terminalScene;
	boolean setRun = true;

	//███████╗██╗░░██╗███╗░░░███╗██╗░░░░░░░░░░░░░██████╗░███████╗░█████╗░██╗░░░░░░█████╗░██████╗░███████╗
	//██╔════╝╚██╗██╔╝████╗░████║██║░░░░░░░░░░░░░██╔══██╗██╔════╝██╔══██╗██║░░░░░██╔══██╗██╔══██╗██╔════╝
	//█████╗░░░╚███╔╝░██╔████╔██║██║░░░░░░░░░░░░░██║░░██║█████╗░░██║░░╚═╝██║░░░░░███████║██████╔╝█████╗░░
	//██╔══╝░░░██╔██╗░██║╚██╔╝██║██║░░░░░░░░░░░░░██║░░██║██╔══╝░░██║░░██╗██║░░░░░██╔══██║██╔══██╗██╔══╝░░
	//██║░░░░░██╔╝╚██╗██║░╚═╝░██║███████╗░░░░░░░░██████╔╝███████╗╚█████╔╝███████╗██║░░██║██║░░██║███████╗
	//╚═╝░░░░░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚══════╝░░░░░░░░╚═════╝░╚══════╝░╚════╝░╚══════╝╚═╝░░╚═╝╚═╝░░╚═╝╚══════╝
	@FXML
	private ImageView mapImageView = new ImageView();
	@FXML
	private ImageView bwMapImageView = new ImageView();
	private static final String MAP_IMAGE_PATH = "/map2.jpg"; //path to file!
	private static final String BWMAP_IMAGE_PATH = "/bwmap2.png"; //path to black&white file!

	public BaseController() {
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (setRun) {

			Image mapImage = new Image(getClass().getResourceAsStream(MAP_IMAGE_PATH), 512, 512, false, false);
			Image bwMapImage = new Image(getClass().getResourceAsStream(BWMAP_IMAGE_PATH), 512, 512, false, false);
			mapImageView.setImage(mapImage);
			bwMapImageView.setImage(bwMapImage);


			setupMainListener();
			setRun = false;
		}
	}

	private void setupMainListener() {

		mapImageView.setOnMouseClicked(event -> {
			double x = event.getX();
			double y = event.getY();

			System.out.println(getImageCoordinates(x, y));
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