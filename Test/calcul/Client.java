package calcul;
import org.omg.CosNaming.*; // inclure le service de nommage
import org.omg.CORBA.*; // manipuler des objets CORBA
import org.omg.CosNaming.NamingContextPackage.*;

public class Client {
public Client() {
}
public static void main (String args[]) {
try {
double mt_ht;
double taux;
double mt_ttc;
mt_ht = Double.valueOf(args[0]);
taux = Double.valueOf(args[1]);

ORB orb = ORB.init(args, null);
org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
String nom = "calcul_ttc";
calcul_montants calcul_ttc = calcul_montantsHelper.narrow
(ncRef.resolve_str(nom));
mt_ttc = calcul_ttc.calcul_ttc(mt_ht,taux);
System.out.println("le montant ttc "+ mt_ttc);
}
catch(Exception e) {
System.out.println("Erreur : "+e);
e.printStackTrace(System.out);
}
}
}