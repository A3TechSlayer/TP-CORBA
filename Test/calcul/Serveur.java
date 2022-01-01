package calcul;
// le serveur va utiliser le service de nommage
import org.omg.CosNaming.*;
//inclure le package des exceptions pouvant etre generees
// par le service de nommage
import org.omg.CosNaming.NamingContextPackage.*;
// sert a manipuler les objets CORBA
import org.omg.CORBA.*;
// Classes necessaires pour referencer le POA
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
// Proprietes pour initialiser l'ORB
import java.util.Properties;
public class Serveur {
public Serveur() {
}
public static void main(String args[]) {
try {
ORB orb=ORB.init(args, null);
POA rootpoa =
POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
rootpoa.the_POAManager().activate();
Calcul_Servant calc= new Calcul_Servant();
org.omg.CORBA.Object ref = rootpoa.servant_to_reference(calc);
calcul_montants href = calcul_montantsHelper.narrow(ref);
org.omg.CORBA.Object objRef =
orb.resolve_initial_references("NameService");
NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
String name = "calcul_ttc";
NameComponent path[] = ncRef.to_name( name );
ncRef.rebind(path, href);
System.out.println("Server is waiting");
orb.run();
} catch(Exception e) {
System.err.println("Erreur : "+e);
e.printStackTrace(System.out);
}
}
}