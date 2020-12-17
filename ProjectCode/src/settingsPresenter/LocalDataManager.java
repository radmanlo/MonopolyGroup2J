package settingsPresenter;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;
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

	public void getDefaultValues(){
		String xmlString = "  <transaction href=\"https://test.com\" type=\"cc\">\n" +
				"    <source>subscription</source>\n" +
				"    <created_at type=\"datetime\">2014-03-06T20:59:03Z</created_at>\n" +
				"    <details>\n" +
				"      <account>\n" +
				"        <account_code>234234234</account_code>\n" +
				"        <first_name>asdadad</first_name>\n" +
				"        <last_name>asdadasd3433</last_name>\n" +
				"        <company nil=\"nil\"></company>\n" +
				"        <email>test@gmail.com</email>\n" +
				"        <billing_info type=\"credit_card\">\n" +
				"          <first_name>asdasdasd</first_name>\n" +
				"          <last_name>asdasdasd23434</last_name>\n" +
				"          <address1 nil=\"nil\"></address1>\n" +
				"          <address2 nil=\"nil\"></address2>\n" +
				"          <city nil=\"nil\"></city>\n" +
				"          <state nil=\"nil\"></state>\n" +
				"          <zip nil=\"nil\"></zip>\n" +
				"          <country nil=\"nil\"></country>\n" +
				"          <phone nil=\"nil\"></phone>\n" +
				"          <vat_number nil=\"nil\"></vat_number>\n" +
				"          <card_type>Visa</card_type>\n" +
				"          <year type=\"integer\">2039</year>\n" +
				"          <month type=\"integer\">6</month>\n" +
				"          <first_six>111111</first_six>\n" +
				"          <last_four>9999</last_four>\n" +
				"        </billing_info>\n" +
				"      </account>\n" +
				"    </details>\n" +
				"    <a name=\"refund\" href=\"https://test.com/refund\" method=\"delete\"/>\n" +
				"  </transaction>";

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(xmlString)));

			//Get all the transaction elements and then loop over them
			NodeList transaction = doc.getElementsByTagName("transaction");
			for(int j = 0; j < transaction.getLength(); j++) {
				//Traverse down the transaction node till we get the billing info
				NodeList details = ((Element)transaction.item(j)).getElementsByTagName("details");
				NodeList account = ((Element)details.item(0)).getElementsByTagName("account");
				NodeList billinginfo = ((Element)account.item(0)).getElementsByTagName("billing_info");

				System.out.println("===Billing Info===");
				System.out.println("Type: "+((Element)billinginfo.item(0)).getAttribute("type"));

				//Get all children nodes from billing info
				NodeList billingChildren = billinginfo.item(0).getChildNodes();

				for(int i = 0; i < billingChildren.getLength(); i++) {
					Node current = billingChildren.item(i);
					//Only want stuff from ELEMENT nodes
					if(current.getNodeType() == Node.ELEMENT_NODE) {
						System.out.println(current.getNodeName()+": "+current.getTextContent());
					}
				}
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
//	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	}
}

