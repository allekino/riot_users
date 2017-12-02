package Utils;

import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class Utils {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Utils.class);

    public ArrayList<String> reader() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("users.xml").getFile());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            NodeList elements = doc.getElementsByTagName("user");
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < elements.getLength(); i++) {
                Node element = elements.item(i);
                String value = element.getFirstChild().getNodeValue();
                list.add(value);
            }
            return list;
        } catch (Exception e) {
            logger.error("Ошибка чтения файла");
        }
        return new ArrayList<>();
    }
}
