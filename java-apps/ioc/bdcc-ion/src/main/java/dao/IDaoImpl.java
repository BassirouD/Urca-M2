package dao;

import org.springframework.stereotype.Component;

/**
 * On peut utiliser aussi @Repository pour bien spécifier la couche DAO
 */
@Component("dao")
public class IDaoImpl implements IDao {
    @Override
    public double getData() {
        System.out.println("Version BD");
        return 23;
    }
}
