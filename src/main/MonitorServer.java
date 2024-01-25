package main;
import java . rmi. RemoteException ;
import java . rmi. registry . LocateRegistry ;
import java . rmi. registry . Registry ;
import java . rmi. server . UnicastRemoteObject ;

public class MonitorServer {
	Registry reg ; /* Object names register */
	MonitorServant servant ;
	
	public static void main ( String [] args ) {
		try {
		MonitorServer s = new MonitorServer ();
		} catch ( Exception e) {
		e. printStackTrace (); System . exit (1);
		}
	}


protected MonitorServer () throws RemoteException {
	 try {
		 /*Creating names register*/
		 reg = LocateRegistry . createRegistry (1099);
		 /*Creating objects available remotly*/
		 servant = new MonitorServant ();
		 /*Associating a name with an object*/
		 reg. rebind (" MonitorServer ", servant );
		 System . out . println (" Server READY ");
	 } catch ( RemoteException e) {
		 e. printStackTrace (); throw e;
	 }
 	}
 }





		