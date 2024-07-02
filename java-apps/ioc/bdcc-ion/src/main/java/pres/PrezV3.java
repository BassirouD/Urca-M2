package pres;

import metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * IOC avec spring via file config xml
 */
public class PrezV3 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        //Via la classe
        IMetier metier = (IMetier) context.getBean("metier");
        //Via l'interface
        IMetier metier2 = context.getBean(IMetier.class);
        System.out.println("Version spring xml:=> " + metier.calcul());
    }
}
