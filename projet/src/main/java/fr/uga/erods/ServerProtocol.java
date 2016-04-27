package fr.uga.erods;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class ServerProtocol implements ClientItf<String> {
	private static final int MENU = 0;
	private static final int REPONSE = 4;
	private static final int MANIPULATION = 2;
	private static final int COMMANDES = 1;

	private int state = MENU;

	public Hashtable<String, LinkedList<String>> table = new Hashtable<String, LinkedList<String>>();
	public Hashtable<String, LinkedList<Hashtable<String, String>>> tableHash = new Hashtable<String, LinkedList<Hashtable<String, String>>>();

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

			if(theInput.toUpperCase().startsWith("LPUSH ")){
				String[] input = theInput.split(" ");
				String clé = input[1];
				LinkedList<String> valeur = new LinkedList<String>();
				for (int i=2;i<input.length;i++){
					valeur.add(input[i]);
				}
				theOutput = LPUSH(clé,valeur);
			}else if(theInput.toUpperCase().startsWith("RPUSH ")){
				String[] input = theInput.split(" ");
				String clé = input[1];
				LinkedList<String> valeur = new LinkedList<String>();
				for (int i=2;i<input.length;i++){
					valeur.add(input[i]);
				}
				theOutput = RPUSH(clé,valeur);
			} else if(theInput.toUpperCase().startsWith("LPUSHX ")){
				String[] input = theInput.split(" ");
				String clé = input[1];
				LinkedList<String> valeur = new LinkedList<String>();
				for (int i=2;i<input.length;i++){
					valeur.add(input[i]);
				}
				theOutput = LPUSHX(clé,valeur);
			}else if(theInput.toUpperCase().startsWith("RPUSHX ")){
				String[] input = theInput.split(" ");
				String clé = input[1];
				LinkedList<String> valeur = new LinkedList<String>();
				for (int i=2;i<input.length;i++){
					valeur.add(input[i]);
				}
				theOutput = RPUSHX(clé,valeur);
			}else if(theInput.toUpperCase().startsWith("LINSERT ")){
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
			}else if(theInput.toUpperCase().startsWith("LPOP ")){
				String[] input = theInput.split(" ");
				String clé = input[1];
				theOutput = LPOP(clé);
			}else if(theInput.toUpperCase().startsWith("RPOP ")){
				String[] input = theInput.split(" ");
				String clé = input[1];
				theOutput = RPOP(clé);
			}else if(theInput.toUpperCase().startsWith("LLEN ")){
				String[] input = theInput.split(" ");
				String clé = input[1];
				theOutput = LLEN(clé);
			}else if(theInput.toUpperCase().startsWith("LSET ")){
				String[] input = theInput.split(" ");
				if(input.length != 4){
					System.err.println("ERREUR LSET : nombre d'arguments incorrect");
					return null;
				}
				String clé = input[1];
				int  index = Integer.parseInt(input[2]);
				String valeur = input[3];

				theOutput = LSET(clé,index,valeur);

			}else if(theInput.toUpperCase().startsWith("SREM ")){
				String[] input = theInput.split(" ");
				String clé = input[1];
				LinkedList<String> valeur = new LinkedList<String>();
				for (int i=2;i<input.length;i++){
					valeur.add(input[i]);
				}
				theOutput = SREM(clé,valeur);
			}else if(theInput.toUpperCase().startsWith("DEL ")){
				String[] input = theInput.split(" ");
				LinkedList<String> clés = new LinkedList<String>();
				for (int i=1;i<input.length;i++){
					clés.add(input[i]);
				}
				theOutput = DEL(clés);

			}else if(theInput.toUpperCase().startsWith("FLUSHALL")){
				theOutput = FLUSHALL();

			}else if(theInput.toUpperCase().startsWith("EXISTS ")){
				String[] input = theInput.split(" ");
				if(input.length == 2){
					theOutput = EXISTS(input[1]);
				} else{
					System.err.println("ERREUR EXISTS : nombre d'arguments incorrect");
					return null;
				}				
			}else if(theInput.toUpperCase().startsWith("GET ")){
				String[] input = theInput.split(" ");
				if(input.length == 2){
					theOutput = GET(input[1]);
				}else{
					System.err.println("ERREUR GET : nombre d'arguments incorrect");
					return null;
				}
			}else if(theInput.toUpperCase().startsWith("LRANGE ")){
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
			}else if(theInput.toUpperCase().startsWith("GETSET ")){
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
			}else if(theInput.toUpperCase().startsWith("SET ")){
				String[] input = theInput.split(" ");
				if(input.length >= 3){
					String key = input[1];
					String valeur="";

					for (int i=2;i<input.length;i++){
						valeur=valeur.concat(input[i]);
					}					
					theOutput = SET(key,valeur);
				}else{
					System.err.println("ERREUR SET : nombre d'arguments incorrect");
					return null;
				}
			}else if(theInput.toUpperCase().startsWith("APPEND ")){
				String[] input = theInput.split(" ");
				if(input.length == 3){
					theOutput = APPEND(input[1],input[2]);
				} else{
					System.err.println("ERREUR APPEND : nombre d'arguments incorrect");
					return null;
				}				
			}
			else if(theInput.toUpperCase().startsWith("DECR ")){
				String[] input = theInput.split(" ");
				if(input.length == 2){
					theOutput = DECR(input[1]);
				} else{
					System.err.println("ERREUR DECR : nombre d'arguments incorrect");
					return null;
				}				
			}
			else if(theInput.toUpperCase().startsWith("INCR ")){
				String[] input = theInput.split(" ");
				if(input.length == 2){
					theOutput = INCR(input[1]);
				} else{
					System.err.println("ERREUR INCR : nombre d'arguments incorrect");
					return null;
				}				
			}
			else if(theInput.toUpperCase().startsWith("ECHO ")){
				theOutput = ECHO(theInput);
			}else if(theInput.toUpperCase().startsWith("COMMAND ")){
				theOutput = COMMAND();
			}else if(theInput.toUpperCase().startsWith("HSET ")){
				String[] input = theInput.split(" ");
				if(input.length == 4){
					String key = input[1];
					String field = input[2];
					String value = input[3];							
					theOutput = HSET(key,field,value);

				} else{
					System.err.println("ERREUR HSET : nombre d'arguments incorrect");
					return null;
				}	
			}else if(theInput.toUpperCase().startsWith("HGET ")){
				String[] input = theInput.split(" ");
				if(input.length == 3){
					String key = input[1];
					String field = input[2];						
					theOutput = HGET(key,field);

				} else{
					System.err.println("ERREUR HGET : nombre d'arguments incorrect");
					return null;
				}	
			}else if(theInput.toUpperCase().startsWith("HDEL ")){
				String[] input = theInput.split(" ");
				if(input.length == 3){
					String key = input[1];
					String field = input[2];						
					theOutput = HDEL(key,field);

				} else{
					System.err.println("ERREUR HDEL : nombre d'arguments incorrect");
					return null;
				}	
			}else if(theInput.toUpperCase().startsWith("HEXISTS ")){
				String[] input = theInput.split(" ");
				if(input.length == 3){
					String key = input[1];
					String field = input[2];						
					theOutput = HEXISTS(key,field);

				} else{
					System.err.println("ERREUR HEXISTS : nombre d'arguments incorrect");
					return null;
				}	
			}else if(theInput.toUpperCase().startsWith("HKEYS ")){
				String[] input = theInput.split(" ");
				if(input.length == 2){
					String key = input[1];						
					theOutput = HKEYS(key);

				} else{
					System.err.println("ERREUR HKEYS : nombre d'arguments incorrect");
					return null;
				}	
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
			String res = GET(key).substring(3,key.length()-1);
			table.put(key, value);
			return res;
		}
		else {
			return "(clé inexistante)";
		}
	}

	@Override
	public String SET(String key, String value) {
		if(table.containsKey(key)){
			String res = GET(key).substring(3,key.length()-1);
			LinkedList<String> tmp = new LinkedList<String>();
			tmp.add(value);
			table.put(key, tmp);
			return res;
		}
		else {
			LinkedList<String> tmp = new LinkedList<String>();
			tmp.add(value);
			table.put(key, tmp);
			return "Ok";
		}
	}

	@Override
	public String APPEND(String key, String value) {
		if(table.containsKey(key)){
			String res=GET(key).substring(3,key.length()-1);
			String v=res.concat(value);	

			SET(key,v);
			return "(integer) "+ v.length();
		}
		else
		{

			SET(key, value);
			return "(integer) "+value.length();
		}
	}

	@Override
	public String DECR(String key) {
		if(table.containsKey(key)){			
			String res=table.get(key).getFirst();
			if(estUnEntier(res)){
				int i=Integer.parseInt(res);
				i--;
				SET(key,String.valueOf(i));
				return "(integer) "+i;
			}
			else
			{
				System.err.println("(erreur) ERR value is not an integer or out of range");
				return null;
			}
		}else{
			SET(key,"-1");
			return ("(integer) -1");
		}
	}

	@Override
	public String INCR(String key) {
		if(table.containsKey(key)){
			String res=table.get(key).getFirst();
			if(estUnEntier(res)){
				int i=Integer.parseInt(res);
				i++;
				SET(key, String.valueOf(i));
				return "(integer) "+i;
			}
			else
			{
				System.err.println("(erreur) ERR value is not an integer or out of range");
				return null;
			}
		}else{
			SET(key,"1");
			return ("(integer) 1");
		}
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
		String res="";
		Hashtable<String,String> tmp = new Hashtable<String,String>();
		tmp.put(field, value);
		LinkedList<Hashtable<String,String>> ltmp = new LinkedList<Hashtable<String,String>>();
		ltmp.add(tmp);
		tableHash.put(key, ltmp);

		if(tableHash.containsKey(key)){	
			res = "(integer) 1";

		}else{
			res = "(integer) 0";
		}
		return res;

	}


	@Override
	public String HGET(String key, String field) {		
		if(tableHash.containsKey(key)){
			if(estDansHash(tableHash.get(key),field)){
				return valueH(tableHash.get(key),field);
			}
		}
		return "(nil)";
	}
	@Override
	public String HDEL(String key, String field) {
		String res="";
		if(tableHash.containsKey(key)){
			//if(tableHash.get(key).containsKey(field)){
				tableHash.get(key).remove(field);

				res = "(integer) 1";
			//}
		}
		else{
			res = "(integer) 0";
		}
		return res;
	}

	@Override
	public String HEXISTS(String key, String field) {
		if(tableHash.containsKey(key)){
			/*if(tableHash.get(key).containsKey(field)){
				return "(integer) 1";
			}*/
		}
		return "(integer) 0";
	}

	@Override
	public String HKEYS(String key) {
		String res="";
		if(tableHash.containsKey(key)){
			int cpt=0;
			/*Hashtable<String, String> ht= tableHash.get(key);

			Set<String> set = ht.keySet();
			String str ;
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				str = it.next();
				cpt++;
				res=res.concat(cpt+") "+str+"\n");
			}

			if(cpt == 0){
				return "Clé vide";
			}
		} else {
			return "Clé inexistante";
		}
*/
		return res;
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
	public LinkedList<String> HVALS(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String HINCRBY(String key, String field, int valIncr) {
		// TODO Auto-generated method stub
		return null;
	}



	public boolean estDansHash(LinkedList<Hashtable<String,String>> lhash, String key ){
		for(Hashtable<String,String> h: lhash){
			if(h.containsKey(key)){
				return true;
			}
		}
		return false;
	}
	
	public String valueH(LinkedList<Hashtable<String,String>> lhash, String key ){
		String res="";
		
		for(Hashtable<String,String> h: lhash){
			if(h.containsKey(key)){
				res=h.get(key);
			}
		}
		return res;
	}

	public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);


		} catch (NumberFormatException e){
			return false;
		}

		return true;
	}



}
