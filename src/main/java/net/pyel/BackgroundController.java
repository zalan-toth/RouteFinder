package net.pyel;

/**
 * Manages saves and loads in the background mainly
 *
 * @author Zalán Tóth
 */
public class BackgroundController {
	private static LandmarksAPI landmarksAPI = new LandmarksAPI();


	public static LandmarksAPI getLandmarksAPI() {
		return landmarksAPI;
	}

	public static void setLandmarksAPI(LandmarksAPI landmarksAPI) {
		BackgroundController.landmarksAPI = landmarksAPI;
	}

	public static void loadData() {
		load();
	}

	public static void saveData() {
		save();
	}

	private static void save() {

		try {
			System.out.println("Data save attempted.");
			landmarksAPI.save();
		} catch (Exception e) {
			System.err.println("Error writing to file: " + e);
		}
	}

	private static void load() {
		try {
			System.out.println("Data load attempted.");
			landmarksAPI.load();
		} catch (Exception e) {
			System.err.println("Error reading from file: " + e);
		}

	}
}