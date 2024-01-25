package main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MonitorServant extends UnicastRemoteObject implements MonitorInterface {
    private Vector<String> users=new Vector<String>();
    private Vector<clientCluster> cluster= new Vector<clientCluster>();
    private Map<String, TransmitInterface> people = new HashMap<>();
    
  
	// constructor
	public MonitorServant() throws RemoteException {
	}
	

	// Implements register() of MonitorInterface interface
	public boolean register(String nick, TransmitInterface n) throws RemoteException {
		if (!users.contains(nick)) {
			users.add(nick);
			people.put(nick, n);
			System.out.println("Server.register(): " + nick);
			return true;
		}
		return false;
	}

	// Implements unregister() of MonitorInterface interface
	public boolean unregister(String nick) throws RemoteException {
		if (!users.contains(nick)) {
			users.remove(nick); 
			System.out.println("Server.unregister(): " + nick);
			return true;
		}
		return false;
	}
	
	// Implements propagate() of TransmitInterface interface
	public boolean propagate(String username, String nick, String text) throws RemoteException {
		System.out.println("Stream message: Sender:"+username+"\nreciver:" + nick + "\nMessage:" + text + "\n");
		TransmitInterface callback = people.get(nick);
		if (callback != null) {
			callback.inform(username,nick, text);
			return true;
		}
		return false;
	}

	// Implements sendMessage() of MonitorInterface interface
	public void sendMessage(String user, String msg,String comand) throws RemoteException {
		    clientCluster newMsg = new clientCluster(user, msg, "");
		    this.cluster.add(newMsg);
		    System.out.println(newMsg.getFormatedMessage());
		}
	// Implements getClientList() of MonitorInterface interface
	  public Vector<String> getClientList() {
	        return users;
	    }
	// Implements getMessageList() of MonitorInterface interface
	  public Vector<clientCluster> getMessageList() {
	        return cluster;
	    }
	// Implements getComandList() of MonitorInterface interface
	  public Vector<clientCluster> getComandList() {
		Vector<clientCluster> onlyComand = new Vector<clientCluster>();
		for (int i = 0; i < cluster.size(); i++) {
		    onlyComand.add(cluster.get(i));  
		 }
		return onlyComand; 
	   }
	// Implements getMessageSubset() of MonitorInterface interface
	  public Vector<clientCluster> getMessageSubset(String user) {
	        Vector<clientCluster> filteredMessages = new Vector<clientCluster>();

	        for (int i = 0; i < cluster.size(); i++) {
	            if (cluster.get(i).getUser().equals(user)) {
	                filteredMessages.add(this.cluster.get(i));
	            }
	        }

	        return filteredMessages;
	    }
	  public Vector<clientCluster> getComandSubset(String user) {
	        Vector<clientCluster> filteredComand = new Vector<clientCluster>();

	        for (int i = 0; i < cluster.size(); i++) {
	            if (cluster.get(i).getUser().equals(user)) {
	                filteredComand.add(this.cluster.get(i));
	            }
	        }

	        return filteredComand;
	    }
	   public Vector<clientCluster> getMessagesSubsetDate(String date) {
	        Vector<clientCluster> filteredMessages = new Vector<clientCluster>();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        LocalDateTime convertedDate = LocalDateTime.parse(date, formatter);

	        for (int i = 0; i < cluster.size(); i++) {
	            if (cluster.get(i).getDate().isAfter(convertedDate)) {
	                filteredMessages.add(cluster.get(i));
	            }
	        }

	        return filteredMessages;
	    }

}