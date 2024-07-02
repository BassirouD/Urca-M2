package ext;

import dao.IDao;
import org.springframework.stereotype.Component;

/**
 * On peut utiliser aussi @Repository pour bien spécifier la couche DAO
 */
@Component("daoV2")
public class IDaoImplV2 implements IDao {
    @Override
    public double getData() {
        System.out.println("Version web service");
        return 11;
    }
}
