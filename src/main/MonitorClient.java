package main;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Vector;
import java.util.Scanner;

public class MonitorClient {
	private Scanner userInput = new Scanner(System.in);
	private Scanner comandInput = new Scanner(System.in);
	String username;
	MonitorInterface remoteObject; // a remote object reference
	TransmitInterface callback;

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage: MonitorClient <server host name>");
			System.exit(-1);
		}
		new MonitorClient(args[0]);
	}

	public MonitorClient(String hostname) {
		System.out.println("Enter client name: ");
		if (userInput.hasNextLine())
			username = userInput.nextLine();
		Registry reg; // the remote objects registry
		try {
			// getting a reference to the object names registry
			reg = LocateRegistry.getRegistry(hostname);
			
			 // odszukanie zdalnego obiektu po jego nazwie
			 remoteObject = ( MonitorInterface ) reg . lookup (" MonitorServer ");
			 callback=new TransmitDeclaration();
			// find a remote object by its name
			// calling methods of a remote object	
			if (!remoteObject.register(username, callback)) {
				System.out.println("User with login:" +"["+ username+"]" + " is already registered");
				System.exit(-1);
			}
			
			loop();
			remoteObject.unregister(username);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	private void sendMessage() {
		String uname;
		System.out.print("Enter the name of addressee: ");
		if (userInput.hasNextLine()) {
			uname = userInput.nextLine();
			String utext;
			System.out.print("Enter the text you want to send him: ");
			if (userInput.hasNextLine()) {
				utext = userInput.nextLine();
				try {
					remoteObject.sendMessage(uname, utext," ");
					remoteObject.propagate(username,uname, utext);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}
    private void getClientList() {
    	try {
	        Vector<String> users = remoteObject.getClientList();
	        System.out.println("Server response:\n There are: " + users.size() + " user(s):\n");
	        for (String s:users) {
	            System.out.print(s+"\n");
	        }       
    	}
    	catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    private void getMessageList() {
       try {
    	   Vector<clientCluster> messages = remoteObject.getMessageList(); 
	       for (int i = 0; i < messages.size(); i++) {
	            System.out.println(messages.get(i).getFormatedMessage());
	        }
        }
        catch (RemoteException e) {
			e.printStackTrace();
		}
        
    }
    private void getComandList() {
    	  System.out.println("Not supported!");
    }
    
    private void getMessageSubset() {
        try {
	        System.out.print("Enter the user name filter regular expression:");
			if (userInput.hasNextLine()) {
				String uname = userInput.nextLine();
				Vector<clientCluster> messages = remoteObject.getMessageSubset(uname);
				System.out.print("["+uname+"]"+"have"+messages.size()+"messages:\n");
				 for (int i = 0; i < messages.size(); i++) {
			            System.out.println(messages.get(i).getFormatedMessage()+"\n");
			     }
			}
	       
        }
        catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    private void getMessageSubsetDate() {
    	try {
    		 System.out.print("Enter date for filtering (yyyy-MM-dd HH:mm:ss) regular expression:");
 			if (userInput.hasNextLine()) {
 				String date = userInput.nextLine();
 				Vector<clientCluster> messages = remoteObject.getMessagesSubsetDate(date);
 				System.out.println("["+date+"]"+"Find:" + messages.size() + "messages:\n ");
		        for (int i = 0; i < messages.size(); i++) {
		            System.out.println("["+date+"]"+messages.get(i).getFormatedMessage()+"\n");
		        }
 			}
    	}
    	catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    private void getComandSubset() {
  	  System.out.println("Not supported!");
  }
	
	private String prompt() {
		return "[" + username + "] > ";
	}

	void loop() {
	   while(true) {
		    String comand;
		    System.out.println("\n-------------------------------------------------------------");
            System.out.println("-----RMI CHAT-----\n");
            System.out.println("Logged as:"+prompt()+"\n");
            System.out.println("Available options:\n");
            System.out.println("1: Send message");
            System.out.println("2: Get users list");
            System.out.println("3: Get message list");
            System.out.println("4: Get comand list");
            System.out.println("5: Get messages filtered by user name");
            System.out.println("6: Get messages filtered by date expresion");
            System.out.println("6: Get comand filtered by user name");
            System.out.println("0: Quit");
            System.out.println("-------------------------------------------------------------");
            System.out.println(prompt());

            if (comandInput.hasNextLine()) {
    			comand= userInput.nextLine();
	            switch (comand) {
	                case "1":
	                    sendMessage();
	                    break;
	                case "2":
	                    getClientList();
	                    break;
	                case "3":
	                	getMessageList();
	                	break;  
	                case "4":
	                    getComandList();
	                    break;
	                case "5":
	                    getMessageSubset();
	                    break;
	                case "6":
	                	getMessageSubsetDate();
	                	break; 
	                case "7":
	                    getComandSubset();
	                    break;
	                case "0":
	                	return;
	                default:
	                    System.out.println("You entered invalid command!");
	                    break;
            	}
            }
	   }
		
	}
}
