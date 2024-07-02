package pres;

import dao.IDaoImpl;
import ext.IDaoImplV2;
import metier.IMetierImpl;

/**
 * Injection des deps par instanciation statique
 */
public class PrezV1 {
    public static void main(String[] args) {

        IDaoImplV2 dao = new IDaoImplV2();
        /**
         * injection des deps via constructeur
         * (On évite un appel à une methode en moins) donc plus adapté
         */
        IMetierImpl metier = new IMetierImpl(dao);
        /**
         * injection des deps via setter
         */
        //metier.setDao(dao);
        System.out.println(metier.calcul());
    }
}
