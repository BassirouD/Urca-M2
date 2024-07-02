package hosso.diallo.xml_config;

import javax.xml.bind.JAXBException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DependencyInjector {
    private Map<String, Object> beanContainer = new HashMap<>();

    public void injectDependencies(String xmlFilePath) {
        try {
            // Lire le fichier de configuration XML
            BeanConfig beanConfig = XmlConfigReader.readConfig(xmlFilePath);
            List<Bean> beans = beanConfig.getBeans();

            // Parcourir les beans et effectuer l'injection de dépendances
            for (Bean bean : beans) {
                // Instancier le bean
                Object instance = Class.forName(bean.getClassName()).newInstance();

                // Parcourir les propriétés et injecter les dépendances
                for (Property property : bean.getProperties()) {
                    // Récupérer la référence de la dépendance
                    Object dependency = getDependency(property.getRef());

                    // Utiliser la réflexion pour injecter la dépendance
                    // par exemple, si la propriété est un champ
                    Field field = instance.getClass().getDeclaredField(property.getName());
                    field.setAccessible(true);
                    field.set(instance, dependency);
                }

                // Faire quelque chose avec l'instance, comme l'enregistrer dans un conteneur
                // Enregistrement dans le conteneur de beans
                beanContainer.put(bean.getId(), instance);

            }
        } catch (JAXBException | ClassNotFoundException | InstantiationException | IllegalAccessException |
                 NoSuchFieldException e) {
            e.printStackTrace();
            // Gérer les erreurs de lecture de configuration ou d'injection de dépendances
        }
    }

    public Object getDependency(String beanId) {
        // Récupérer l'instance de la dépendance à partir du conteneur de beans
        // Ceci est un exemple simplifié, vous devez implémenter votre propre logique de récupération de la dépendance
        // à partir du conteneur de beans ou d'autres sources
        return beanContainer.get(beanId);
    }
}
