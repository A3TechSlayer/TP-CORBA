package exo1;
import org.omg.CORBA.*;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.io.*;

import java.lang.*;

import java.util.*;


public class Client {
    public static void main(String[] args) throws IOException {
 
        ORB orb = ORB.init(args, null);

        try {
            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");
            NamingContextExt ncRef =
                    NamingContextExtHelper.narrow(objRef);
            String nom = "calculateur";
            calcul calc = calculHelper.narrow
                    (ncRef.resolve_str(nom));
            char car;

            Integer param = new Integer(args[0]);

            IntHolder resultat = new IntHolder(param.intValue());

            try {
                System.out.println(" Que faire (incrementer ou decrementer ; saisir i ou d)?  ");
                car = (char) System.in.read();

                if (car == 'i') {
                    calc.incrementer(resultat);

                    System.out.println(" Valeur incrementee = " + resultat.value);
                } else if (car == 'd') {
                    calc.decrementer(resultat);

                    System.out.println(" Valeur decrementee = " + resultat.value);
                } else {
                    System.out.println("  Saisir 'i' ou 'd'");
                }
            } catch (IOException ex) {
                System.out.println("Erreur lecture commande (char)");
                System.exit(1);
            }
        }
        catch(Exception e) {
            System.out.println("Erreur : "+e);
            e.printStackTrace(System.out);
        }
        System.exit(0);
    }
}