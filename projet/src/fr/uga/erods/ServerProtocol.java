package fr.uga.erods;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

public class ServerProtocol implements ClientItf<String> {
	private static final int MENU = 0;
	private static final int REPONSE = 4;
	private static final int MANIPULATION = 2;
	private static final int COMMANDES = 1;

	private int state = MENU;

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

			if(theInput.toUpperCase().contains("LPUSH ")){
				String[] input = theInput.split(" ");
				String clé = input[1];
				LinkedList<String> valeur = new LinkedList<String>();
				for (int i=2;i<input.length;i++){
					valeur.add(input[i]);
				}
				theOutput = LPUSH(clé,valeur);
			}else if(theInput.toUpperCase().contains("RPUSH ")){
				String[] input = theInput.split(" ");
				String clé = input[1];
				LinkedList<String> valeur = new LinkedList<String>();
				for (int i=2;i<input.length;i++){
					valeur.add(input[i]);
				}
				theOutput = RPUSH(clé,valeur);
			} else if(theInput.toUpperCase().contains("LPUSHX ")){
				String[] input = theInput.split(" ");
				String clé = input[1];
				LinkedList<String> valeur = new LinkedList<String>();
				for (int i=2;i<input.length;i++){
					valeur.add(input[i]);
				}
				theOutput = LPUSHX(clé,valeur);
			}else if(theInput.toUpperCase().contains("RPUSHX ")){
				String[] input = theInput.split(" ");
				String clé = input[1];
				LinkedList<String> valeur = new LinkedList<String>();
				for (int i=2;i<input.length;i++){
					valeur.add(input[i]);
				}
				theOutput = RPUSHX(clé,valeur);
			}else if(theInput.toUpperCase().contains("LINSERT ")){
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
			}else if(theInput.toUpperCase().contains("LPOP ")){
				String[] input = theInput.split(" ");
				String clé = input[1];
				theOutput = LPOP(clé);
			}else if(theInput.toUpperCase().contains("RPOP ")){
				String[] input = theInput.split(" ");
				String clé = input[1];
				theOutput = RPOP(clé);
			}else if(theInput.toUpperCase().contains("LLEN ")){
				String[] input = theInput.split(" ");
				String clé = input[1];
				theOutput = LLEN(clé);
			}else if(theInput.toUpperCase().contains("LSET ")){
				String[] input = theInput.split(" ");
				if(input.length != 4){
					System.err.println("ERREUR LSET : nombre d'arguments incorrect");
					return null;
				}
				String clé = input[1];
				int  index = Integer.parseInt(input[2]);
				String valeur = input[3];

				theOutput = LSET(clé,index,valeur);

			}else if(theInput.toUpperCase().contains("SREM ")){
				String[] input = theInput.split(" ");
				String clé = input[1];
				LinkedList<String> valeur = new LinkedList<String>();
				for (int i=2;i<input.length;i++){
					valeur.add(input[i]);
				}
				theOutput = SREM(clé,valeur);
			}else if(theInput.toUpperCase().contains("DEL ")){
				String[] input = theInput.split(" ");
				LinkedList<String> clés = new LinkedList<String>();
				for (int i=1;i<input.length;i++){
					clés.add(input[i]);
				}
				theOutput = DEL(clés);

			}else if(theInput.toUpperCase().contains("FLUSHALL")){
				theOutput = FLUSHALL();

			}else if(theInput.toUpperCase().contains("EXISTS ")){
				String[] input = theInput.split(" ");
				if(input.length == 2){
					theOutput = EXISTS(input[1]);
				} else{
					System.err.println("ERREUR EXISTS : nombre d'arguments incorrect");
					return null;
				}				
			}else if(theInput.toUpperCase().contains("GET ")){
				String[] input = theInput.split(" ");
				if(input.length == 2){
					theOutput = GET(input[1]);
				}else{
					System.err.println("ERREUR GET : nombre d'arguments incorrect");
					return null;
				}
			}else if(theInput.toUpperCase().contains("LRANGE ")){
				String[] input = theInput.split(" ");
				if(input.length == 4){
					String key = input[1];
					int start = Integer.parseInt(input[2]);
					int end = Integer.parseInt(input[3]);					
					theOutput = LRANGE(key,start,end);
				}else{
					System.err.println("ERREUR LRANGE : nombre d'arguments incorrect");
					return null;
				}
			}else if(theInput.toUpperCase().contains("GETSET ")){
				String[] input = theInput.split(" ");
				if(input.length >= 3){
					String key = input[1];
					LinkedList<String> valeur = new LinkedList<String>();
					for (int i=2;i<input.length;i++){
						valeur.add(input[i]);
					}					
					theOutput = GETSET(key,valeur);
				}else{
					System.err.println("ERREUR GETSET : nombre d'arguments incorrect");
					return null;
				}
			}else if(theInput.toUpperCase().contains("SET ")){
				String[] input = theInput.split(" ");
				if(input.length >= 3){
					String key = input[1];
					LinkedList<String> valeur = new LinkedList<String>();
					for (int i=2;i<input.length;i++){
						valeur.add(input[i]);
					}					
					theOutput = SET(key,valeur);
				}else{
					System.err.println("ERREUR SET : nombre d'arguments incorrect");
					return null;
				}
			}else if(theInput.toUpperCase().contains("ECHO ")){
					theOutput = ECHO(theInput);
			}else if(theInput.toUpperCase().contains("COMMAND ")){
				theOutput = COMMAND();
		}






		} else if (state == COMMANDES) {
			theOutput = COMMAND();
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
			if(table.containsKey(k)){
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
	public String EXISTS(String key) {
		if (table.containsKey(key)){
			return "(integer) 1";
		}
		return "(integer) 0";
	}

	@Override
	public String GET(String key) {
		if(table.containsKey(key)){
			LinkedList<String> tmp = table.get(key);
			String res = "";
			int cmp =0;

			for (String l : tmp){
				cmp ++;
				res+= cmp+") "+l+"\n";
			}
			return res;
		}
		else 
			return "(nil)";
	}

	@Override
	public String LRANGE(String key, int start, int end) {
		if(table.containsKey(key)){
			LinkedList<String> tmp = table.get(key);
			String res = "";
			int cmp=0;
			if(end == -1){
				end = tmp.size()-1;
			}
			if(start >tmp.size()){
				return "(liste vide ou inexistante)";
			}
			if(end >tmp.size()){
				end = tmp.size();
			}
			for (int i=start;i<= end;i++){
				cmp++;
				res+= cmp+") "+ tmp.get(i)+"\n";				
			}
			return res;
		}
		else {
			return "(liste vide ou inexistante)";
		}
	}

	@Override
	public String GETSET(String key, LinkedList<String> value) {
		if(table.containsKey(key)){
			String res = GET(key);
			table.put(key, value);
			return res;
		}
		else {
			return "(clé inexistante)";
		}
	}

	@Override
	public String SET(String key, LinkedList<String> value) {
		if(table.containsKey(key)){
			String res = GET(key);
			table.put(key, value);
			return res;
		}
		else {
			table.put(key, value);
			return "Ok";
		}
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
		return message.substring(5);
	}

	@Override
	public String COMMAND() {
		String Commandes ="Liste des commandes : \n";
		Commandes += "LPUSH Key values : insertion des values par la droite dans Key\n";
		Commandes += "LPUSHX Key values : insertion des values par la droite si Key existe\n";
		Commandes += "RPUSH Key values : insertion des values par la gauche dans Key\n";
		Commandes += "RPUSHX Key values : insertion des values par la gauche dans Key\n";
		Commandes += "LINSERT Key mode value1 value2 : insertion de value2 en fonction du mode\n"
				+ " dans Key\nmode=BEFORE ou AFTER\n";
		Commandes += "LPOP Key : supprime et retourne le 1er élément de la liste dans Key\n";
		Commandes += "RPOP Key : supprime et retourne le dernier élément de la liste dans Key\n";
		Commandes += "LLEN Key : renvoie le nombre d'éléments de la liste dans Key\n";
		Commandes += "LSET Key index value : insertion de value à l'index de la liste dans Key\n";
		Commandes += "DEL Key : supprime Key et renvoie le nombre d'éléments supprimés\n";
		Commandes += "FLUSHALL : supprime toutes les clés\n";
		Commandes += "EXISTS Key : renvoie 1 si Key existe, 0 sinon\n";
		Commandes += "GET Key : renvoie la ou les valeurs de Key\n";
		Commandes += "LRANGE Key start end : renvoie les valeurs de Key compris entre start et end\n";
		Commandes += "GETSET Key values : remplace les valeurs de Key par values et retourne les anciennes\n";
		Commandes += "SET Key values : remplace les valeurs de Key par values et créer la clé si elle est inexistante\n";
		
		
		Commandes += "ECHO String : affiche la String\n";
		Commandes += "COMMAND : affiche les commandes\n";
		
		
		
		
		
		return Commandes;
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
