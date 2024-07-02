package metier;

import dao.IDao;
import dao.IDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Instanciation via les annotations en utilisant @Compenent
 *
 * On peut utiliser aussi @Service pour bien spécifier la couche metier
 */

@Component("metier")
public class IMetierImpl implements IMetier {

    @Autowired
    private IDao dao;

    /**
     * Le @Qualifier c'est pour préciser le nom de l'objet
     * Par exemple si on a des objets de plusieurs classes qui implémenter la mm interface(IDaoImpl et IDaoImplV2)
     * @param dao
     */

    public IMetierImpl(@Qualifier("daoV2") IDao dao) {
        this.dao = dao;
    }


    @Override
    public double calcul() {
        double t = dao.getData();
        double res = t * 23;
        return res;
    }

    /**
     * C'est pour injecter dans la valeur dao
     *
     * @param dao
     */
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
