package hosso.diallo.xml_config;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlConfigReader {
    public static BeanConfig readConfig(String filepath) throws JAXBException {
        File file = new File(filepath);
        JAXBContext jaxbContext = JAXBContext.newInstance(BeanConfig.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (BeanConfig) jaxbUnmarshaller.unmarshal(file);
    }
}
