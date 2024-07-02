package hosso.diallo.xml_config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "beans")
public class BeanConfig {
    private List<Bean> beans;

    @XmlElement(name = "bean")
    public List<Bean> getBeans() {
        return beans;
    }

    public void setBeans(List<Bean> beans) {
        this.beans = beans;
    }
}
