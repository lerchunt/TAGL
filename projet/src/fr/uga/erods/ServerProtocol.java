package fr.uga.erods;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

public class ServerProtocol implements ClientItf<String> {
	private static final int MENU = 0;
	private static final int REPONSE = 4;
    private static final int MANIPULATION = 2;
    private static final int COMMANDES = 1;
 
    //private int state = MENU;
    
    public Hashtable<String, LinkedList<String>> table = new Hashtable<String, LinkedList<String>>();

    public String processInput(String theInput, int state) {
        String theOutput = null;

        if (state == MENU) {
            theOutput = "Que voulez-vous faire ? \n"
            		+ "1 - Afficher les commandes disponibles \n"
            		+ "2 - Manipuler les données \n";
            
            
            state = REPONSE;
        } else if (state == REPONSE) {
            if (theInput.equalsIgnoreCase("2")) {
            	state = MANIPULATION;            	
            } else if (theInput.equalsIgnoreCase("1")) {
                state = COMMANDES;
            } else {
            	theOutput = "Vous n'avez pas entrée une valeur correcte. "
            			+ "Entrez une valeur entre 1 et 2 : ";
            	state=REPONSE;
            }
        } else if (state == MANIPULATION) {
        	
        	if(theInput.contains("LPUSH ")){
        		String[] input = theInput.split(" ");
        		String clé = input[1];
        		LinkedList<String> valeur = new LinkedList<String>();
        		for (int i=2;i<input.length;i++){
        			valeur.add(input[i]);
        		}
        		theOutput = LPUSH(clé,valeur);
        	}else if(theInput.contains("RPUSH ")){
        		String[] input = theInput.split(" ");
        		String clé = input[1];
        		LinkedList<String> valeur = new LinkedList<String>();
        		for (int i=2;i<input.length;i++){
        			valeur.add(input[i]);
        		}
        		theOutput = RPUSH(clé,valeur);
        	} else if(theInput.contains("LPUSHX ")){
        		String[] input = theInput.split(" ");
        		String clé = input[1];
        		LinkedList<String> valeur = new LinkedList<String>();
        		for (int i=2;i<input.length;i++){
        			valeur.add(input[i]);
        		}
        		theOutput = LPUSHX(clé,valeur);
        	}else if(theInput.contains("RPUSHX ")){
        		String[] input = theInput.split(" ");
        		String clé = input[1];
        		LinkedList<String> valeur = new LinkedList<String>();
        		for (int i=2;i<input.length;i++){
        			valeur.add(input[i]);
        		}
        		theOutput = RPUSHX(clé,valeur);
        	}else if(theInput.contains("LINSERT ")){
        		String[] input = theInput.split(" ");
        		if(input.length != 5){
        			System.err.println("ERREUR LINSERT : nombre d'arguments inccorect");
        			return null;
        		}
        		String clé = input[1];
        		String mode = input[2];
        		String valeur = input[3];
        		String insert = input[4];
        		
        		theOutput = LINSERT(clé,mode,valeur,insert);
        	}else if(theInput.contains("LPOP ")){
        		String[] input = theInput.split(" ");
        		String clé = input[1];
        		theOutput = LPOP(clé);
        	}else if(theInput.contains("RPOP ")){
        		String[] input = theInput.split(" ");
        		String clé = input[1];
        		theOutput = RPOP(clé);
        	}else if(theInput.contains("LLEN ")){
        		String[] input = theInput.split(" ");
        		String clé = input[1];
        		theOutput = LLEN(clé);
        	}else if(theInput.contains("LSET ")){
        		String[] input = theInput.split(" ");
        		if(input.length != 4){
        			System.err.println("ERREUR LSET : nombre d'arguments inccorect");
        			return null;
        		}
        		String clé = input[1];
        		int  index = Integer.parseInt(input[2]);
        		String valeur = input[3];
        		
        		theOutput = LSET(clé,index,valeur);
        	}else if(theInput.contains("SREM ")){
        		String[] input = theInput.split(" ");
        		String clé = input[1];
        		LinkedList<String> valeur = new LinkedList<String>();
        		for (int i=2;i<input.length;i++){
        			valeur.add(input[i]);
        		}
        		theOutput = SREM(clé,valeur);
        	}else if(theInput.contains("DEL ")){
        		String[] input = theInput.split(" ");
        		LinkedList<String> clés = new LinkedList<String>();
        		for (int i=1;i<input.length;i++){
        			clés.add(input[i]);
        		}
        		theOutput = DEL(clés);
        	}else if(theInput.contains("FLUSHALL")){
        		theOutput = FLUSHALL();
        	}
        	
        	
        	
        	
        	
        	
        } else if (state == COMMANDES) {
            // Liste commande
        	state = MENU;
        }
        return theOutput;
    }

    /*
	@Override
	public void connect(Serveur s) {		
	}

	@Override
	public void disconnect() {
	}

    
	public Hashtable<String, LinkedList<String>> getTable() {
		return table;
	}

	public void setTable(Hashtable<String, LinkedList<String>> table) {
		this.table = table;
	}
*/
	
	@Override
	public String LPUSH(String key, LinkedList<String> value) {		
		if(table.containsKey(key)){
			LinkedList<String> tmp = table.get(key);
			value.addAll(tmp);			
			table.put(key,value);		
		} else {
			table.put(key, value);
		}	
		return "(integer) "+value.size();
	}

	@Override
	public String RPUSH(String key, LinkedList<String> value) {
		if(table.containsKey(key)){
			LinkedList<String> tmp = table.get(key);
			tmp.addAll(value);
			table.put(key,tmp);
			return "(integer) "+tmp.size();
		} else {
			table.put(key, value);
			return "(integer) "+ value.size();
		}	
	}

	@Override
	public String LPUSHX(String key, LinkedList<String> value) {
		if(table.containsKey(key)){
			LinkedList<String> tmp = table.get(key);
			value.addAll(tmp);
			table.put(key,value);
			return "(integer) "+value.size();
		} else {
			return "(integer) 0";
		}	
	}

	@Override
	public String RPUSHX(String key, LinkedList<String> value) {
		if(table.containsKey(key)){
			LinkedList<String> tmp = table.get(key);
			tmp.addAll(value);
			table.put(key,tmp);
			return "(integer) "+tmp.size();
		} else {
			return "(integer) 0";
		}	
	}

	@Override
	public String LINSERT(String key, String mode, String value, String insert) {
		if(table.containsKey(key)){
			if(table.get(key).contains(value)){
				LinkedList<String> tmp = table.get(key);
				int index = tmp.indexOf(value);
				if(mode.toUpperCase().equals("BEFORE")){
					tmp.add(index, insert);
					table.put(key,tmp);
				}else if (mode.toUpperCase().equals("AFTER")){
					table.put(key,tmp);
					tmp.add(index+1,insert);
				} else {
					System.err.println("(erreur) LINSERT : mode incorrect.");
					return null;
				}		
				return String.format("%d",tmp.size());		
			} else{
				return "-1";
			}			
		}else{
			return "0";
		}
	}

	@Override
	public String LPOP(String key) {
		if(table.containsKey(key)){
			LinkedList<String> tmp = table.get(key);
			String rm = tmp.removeFirst();
			table.put(key, tmp);
			return rm;
		}else{
			return "nil";
		}
	}
	
	@Override
	public String RPOP(String key) {
		if(table.containsKey(key)){
			LinkedList<String> tmp = table.get(key);
			String rm = tmp.removeLast();
			table.put(key, tmp);
			return rm;
		}else{
			return "nil";
		}
	}

	@Override
	public String LLEN(String key) {
		if(table.containsKey(key)){
			LinkedList<String> tmp = table.get(key);
			return "(integer) "+tmp.size();
		}else{
			return "(integer) 0";
		}
	}

	@Override
	public String LSET(String key, int index, String value) {
		if(table.containsKey(key)){
			LinkedList<String> tmp = table.get(key);
			tmp.set(index, value);
			table.put(key, tmp);
			return "Ok";
		}else{
			System.err.println("ERREUR : pas de clé à ce nom");
			return null;
		}
	}

	@Override
	public String SREM(String key, LinkedList<String> value) {
		if(table.containsKey(key)){
			LinkedList<String> tmp = table.get(key);
			int nb=tmp.size();
			tmp.removeAll(value);
			nb = nb-tmp.size();
			table.put(key, tmp);
			return "(integer) "+nb;
		}else{
			System.err.println("ERREUR : pas de clé à ce nom");
			return null;
		}
	}

	@Override
	public String DEL(LinkedList <String> keys) {
		int cpt = 0;
		for(String k : keys){
			if(table.containsKey(k){
				table.remove(k);
				cpt++;
			}
		}
		return "(integer) "+cpt;
	}

	@Override
	public String FLUSHALL() {
		table.clear();
		return "OK";
	}

	@Override
	public String EXISTS(String[] key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String GET(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String GETRANGE(String Key, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String GETSET(String Key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String SET(String Key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String APPEND(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String DECR(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String INCR(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ECHO(String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ArrayList<String>> COMMAND() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String HSET(String key, String field, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String HGET(String key, String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String HDEL(String key, String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String HEXISTS(String key, String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> HKEYS(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String HLEN(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String HSTRLEN(String key, String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> HVALS(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String HINCRBY(String key, String field, int valIncr) {
		// TODO Auto-generated method stub
		return null;
	}


}
