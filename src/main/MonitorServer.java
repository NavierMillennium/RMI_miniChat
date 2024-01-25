package main;
import java . rmi. RemoteException ;
import java . rmi. registry . LocateRegistry ;
import java . rmi. registry . Registry ;
import java . rmi. server . UnicastRemoteObject ;

public class MonitorServer {
	Registry reg ; /* rejestr nazw obiektow */
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
		 /* Utworzenie rejestru nazw */
		 reg = LocateRegistry . createRegistry (1099);
		 /* utworzenie obiektu dostepnego zdalnie */
		 servant = new MonitorServant ();
		 /* zwiazanie z obiektem nazwy */
		 reg. rebind (" MonitorServer ", servant );
		 System . out . println (" Server READY ");
	 } catch ( RemoteException e) {
		 e. printStackTrace (); throw e;
	 }
 	}
 }





		