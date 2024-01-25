package main;
import java . rmi .*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface MonitorInterface extends Remote{
    boolean register(String nick, TransmitInterface n) throws RemoteException;
    boolean unregister(String nick) throws RemoteException;
    void sendMessage(String user, String msg, String comand) throws RemoteException;
    boolean propagate(String sender,String nick, String message) throws RemoteException;
    
    Vector<String> getClientList() throws RemoteException;
    Vector<clientCluster> getMessageList() throws RemoteException;
    Vector<clientCluster> getComandList() throws RemoteException;
    Vector<clientCluster> getMessageSubset(String user) throws RemoteException;
    Vector<clientCluster> getComandSubset(String date) throws RemoteException;
    Vector<clientCluster> getMessagesSubsetDate(String date) throws RemoteException;
}

