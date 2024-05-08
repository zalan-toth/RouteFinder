package net.pyel.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Landmarks {

	HashMap<String, ArrayList<Coordinate>> landmarks = new HashMap<>();

	public Landmarks(HashMap<String, ArrayList<Coordinate>> landmarks) {
		this.landmarks = landmarks;
	}

	public HashMap<String, ArrayList<Coordinate>> getLandmarks() {
		return landmarks;
	}

	public void setLandmarks(HashMap<String, ArrayList<Coordinate>> landmarks) {
		this.landmarks = landmarks;
	}
}
