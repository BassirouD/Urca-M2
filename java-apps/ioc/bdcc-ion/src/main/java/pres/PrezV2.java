package pres;

import dao.IDao;
import ext.IDaoImplV2;
import metier.IMetier;
import metier.IMetierImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Injection des deps par instanciation dynamique
 */
public class PrezV2 {
    public static void main(String[] args) {
        try {
            /**
             * Instanciation dynamique
             */
            Scanner scanner = new Scanner(new File("config.txt"));
            /**
             * Lire la primière ligne du fichier config.txt
             */

            String daoClassName = scanner.nextLine();
            String metierClassName = scanner.nextLine();

            System.out.println(daoClassName);
            /**
             * AU moment de l'exec, il va charger la classe en mémoire(docClassName)
             */

            Class cDao = Class.forName(daoClassName);
            Class cMetier = Class.forName(metierClassName);

            IDao dao = (IDao) cDao.getConstructor().newInstance();
            //Instanciation via constructeur
            IMetier metier = (IMetier) cMetier.getConstructor(IDao.class).newInstance(dao);

            /**
             * Instanciation via setter
             * En précisant le nom "setDao" et le paramètre vu que plusieurs methodes peuvent
             * avoir le mm nom
             */
            Method setDao = cMetier.getDeclaredMethod("setDao", IDao.class);
            /**
             * Exec method sur l'objet 'metier' avec le param 'dao'
             */
            setDao.invoke(metier, dao);

            System.out.println(dao.getData());
            System.out.println(metier.calcul());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
