package net.pyel;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import net.pyel.models.Landmarks;

import javax.sound.sampled.Port;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * CargoAPI, API for Cargo, save and load happens here, utilized by backgroundcontroller
 *
 * @author Zalán Tóth
 */
public class LandmarksAPI {

	Landmarks landmarks = new Landmarks(new HashMap<>()); //center of data


	public LandmarksAPI() {
	}


	//██████╗░███████╗██████╗░░██████╗██╗░██████╗████████╗███████╗███╗░░██╗░█████╗░███████╗
	//██╔══██╗██╔════╝██╔══██╗██╔════╝██║██╔════╝╚══██╔══╝██╔════╝████╗░██║██╔══██╗██╔════╝
	//██████╔╝█████╗░░██████╔╝╚█████╗░██║╚█████╗░░░░██║░░░█████╗░░██╔██╗██║██║░░╚═╝█████╗░░
	//██╔═══╝░██╔══╝░░██╔══██╗░╚═══██╗██║░╚═══██╗░░░██║░░░██╔══╝░░██║╚████║██║░░██╗██╔══╝░░
	//██║░░░░░███████╗██║░░██║██████╔╝██║██████╔╝░░░██║░░░███████╗██║░╚███║╚█████╔╝███████╗
	//╚═╝░░░░░╚══════╝╚═╝░░╚═╝╚═════╝░╚═╝╚═════╝░░░░╚═╝░░░╚══════╝╚═╝░░╚══╝░╚════╝░╚══════╝


	/**
	 * The save method uses the XStream component to write all the objects in the ArrayList
	 * to the xml file stored on the hard disk.
	 *
	 * @throws Exception An exception is thrown if an error occurred during the save e.g. drive is full.
	 */
	public void save() throws Exception {
		XStream xstream = new XStream(new DomDriver());
		ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("landmarks.xml"));
		out.writeObject(landmarks);
		out.close();
	}

	/**
	 * The load method uses the XStream component to read all the objects from the xml
	 * file stored on the hard disk.
	 *
	 * @throws Exception An exception is thrown if an error occurred during the load e.g. a missing file.
	 */
	public void load() throws Exception {
		//list of classes that you wish to include in the serialisation, separated by a comma
		Class<?>[] classes = new Class[]{Landmarks.class, Port.class};

		//setting up the xstream object with default security and the above classes
		XStream xstream = new XStream(new DomDriver());
		//XStream.setupDefaultSecurity(xstream);
		xstream.allowTypes(classes);

		//doing the actual serialisation to an XML file
		ObjectInputStream in = xstream.createObjectInputStream(new FileReader("cargo.xml"));
		landmarks = (Landmarks) in.readObject();
		in.close();
	}


}