import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Employee> parseXML(String fileNameXML, String tagName) throws ParserConfigurationException, IOException, SAXException {
        List<Employee> staff = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(fileNameXML));
        NodeList nodeList = doc.getElementsByTagName(tagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                Element element = (Element) node;
                NodeList nodeElementList =  element.getChildNodes();
                ArrayList valueList = new ArrayList();
                for (int j = 1; j < nodeElementList.getLength(); j+=2) {
                    valueList.add(nodeElementList.item(j).getTextContent());
                }
                if(valueList.size() >= 5) {
                    staff.add(new Employee(Long.parseLong((String)valueList.get(0)),
                            (String) valueList.get(1),
                            (String) valueList.get(2),
                            (String) valueList.get(3),
                            Integer.parseInt((String)valueList.get(4))
                    ));
                }
            }
        }

        return staff;
    }

    public static void main(String[] args) {
        String fileNameXML = "data.xml";
        String fileNameJSON = "data2.json";
        String tagName = "employee";
        // интерфейс MyJson и класс Employee описаны в модуле CSV_JSON данного проекта

        try {
            MyJson.writeString(MyJson.listToJson(parseXML(fileNameXML, tagName)), fileNameJSON);
        } catch (IOException | ParserConfigurationException | SAXException e){
            e.getStackTrace();
        }
    }
}
