package main;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TransmitInterface extends Remote {
		public void inform(String sender, String nick, String text) throws RemoteException;
}



