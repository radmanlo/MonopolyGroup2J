package settingsPresenter;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.util.ArrayList;

public class LocalDataManager {
	private String localPath;
	private static LocalDataManager localManager = null;

	private LocalDataManager() {}

	public static LocalDataManager getInstance(){
		if (localManager == null)
			localManager = new LocalDataManager();
		return localManager;
	}
	
	public ArrayList<Integer> getStoredGamesIds(){ 
		return null;
	}
	
	public boolean deleteSavedGames(int id) {
		return false;
	}
	
	public String getSavedGameNameById(int id) { 
		return "";
	}
	
	public boolean saveGame() {
		return false;
	}

	private String readFileToString (String path) throws Exception {
		String xmlString = "";
		File file = null;
		int line;

		FileInputStream fileInputStream = null;
		try {
			file = new File(path);
			fileInputStream = new FileInputStream(file);

			while ((line=fileInputStream.read()) != -1) {
				xmlString += (char)line;
			}
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (Exception e2) {
				throw e2;
			}
		}

		return xmlString;
	}

	public Document getDefaultValues(){
		String xmlString = "";
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

		try {
			xmlString = readFileToString("D:/data/file.xml");

			try {
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(new InputSource(new StringReader(xmlString)));
				return doc;
			} catch(Exception ex){
				ex.printStackTrace();
				return null;
			}
		} catch (Exception ex){
			System.out.println("An exception" + ex.toString());
			return null;
		}
	}
}

