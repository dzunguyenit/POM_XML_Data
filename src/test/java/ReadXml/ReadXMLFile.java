package ReadXml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadXMLFile {
	public static void main(String argv[]) {

		String userPath = System.getProperty("user.dir");
		String pathFileXml = userPath.concat("/data/BankGuru.xml");
		Element nodeEment = readXMLFile(pathFileXml);

		System.out.println("nummericValueName = " + getValueXml(nodeEment, "nummericValueName"));
		System.out.println("nummericValueCity = " + getValueXml(nodeEment, "nummericValueCity"));
		System.out.println("customerName = " + getValueXml(nodeEment, "customerName"));
		System.out.println("dateOfBirth = " + getValueXml(nodeEment, "dateOfBirth"));

	}

	public static Element readXMLFile(String pathFileXml) {
		Element nodeElement = null;
		try {

			File xmlFile = new File(pathFileXml);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getChildNodes();

			System.out.println("----------------------------");

			for (int i = 0; i < nList.getLength(); i++) {

				Node nNode = nList.item(i);

				System.out.println("\nCurrent Element : " + nNode.getNodeName());
				nodeElement = (Element) nNode;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodeElement;
	}

	public static String getValueXml(Element nodeElement, String value) {
		return nodeElement.getElementsByTagName(value).item(0).getTextContent();

	}
}
