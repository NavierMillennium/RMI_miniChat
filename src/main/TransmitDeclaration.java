package main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TransmitDeclaration extends UnicastRemoteObject implements TransmitInterface {
	public TransmitDeclaration() throws RemoteException {
		super();
		
	}
	//Method which implements ICallback interface
	public void inform(String sender, String nick, String text) throws RemoteException {
		System.out.println(sender + " wrote: " + text);   
	}
}
