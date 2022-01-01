package exo1;
import org.omg.CORBA.*;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.*;

import java.io.*;

import java.lang.*;

import java.util.*;


public class Serveur {
    public static void main(String[] args) throws IOException {
        try {

            ORB orb = ORB.init(args, null);
            POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            poa.the_POAManager().activate();
            calculImpl calcImpl = new calculImpl();
            org.omg.CORBA.Object ref = poa.servant_to_reference(calcImpl);
            calcul href = calculHelper.narrow(ref);
            org.omg.CORBA.Object objRef =
            orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String name = "calculateur";
            NameComponent path[] = ncRef.to_name( name );
            ncRef.rebind(path, href);
            System.out.println("Le serveur est pret ");
            orb.run();
            System.exit(0);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
