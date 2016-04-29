package main.java.fr.uga.erods;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

public class Transaction implements ClientItf<String>,Serializable {
	public Hashtable<String, LinkedList<String>> table;
	public Hashtable<String, LinkedList<Hashtable<String, String>>> tableHash;

	public Transaction(){
		table = new Hashtable<String, LinkedList<String>>();
		tableHash = new Hashtable<String, LinkedList<Hashtable<String, String>>>();
	}
	public String transact(String s){
		String theOutput = "";

		if(s.toUpperCase().startsWith("LPUSH ")){
			String[] input = s.split(" ");
			String clé = input[1];
			LinkedList<String> valeur = new LinkedList<String>();
			for (int i=2;i<input.length;i++){
				valeur.add(input[i]);
			}
			theOutput = LPUSH(clé,valeur);
		}else if(s.toUpperCase().startsWith("RPUSH ")){
			String[] input = s.split(" ");
			String clé = input[1];
			LinkedList<String> valeur = new LinkedList<String>();
			for (int i=2;i<input.length;i++){
				valeur.add(input[i]);
			}
			theOutput = RPUSH(clé,valeur);
		} else if(s.toUpperCase().startsWith("LPUSHX ")){
			String[] input = s.split(" ");
			String clé = input[1];
			LinkedList<String> valeur = new LinkedList<String>();
			for (int i=2;i<input.length;i++){
				valeur.add(input[i]);
			}
			theOutput = LPUSHX(clé,valeur);
		}else if(s.toUpperCase().startsWith("RPUSHX ")){
			String[] input = s.split(" ");
			String clé = input[1];
			LinkedList<String> valeur = new LinkedList<String>();
			for (int i=2;i<input.length;i++){
				valeur.add(input[i]);
			}
			theOutput = RPUSHX(clé,valeur);
		}else if(s.toUpperCase().startsWith("LINSERT ")){
			String[] input = s.split(" ");
			if(input.length != 5){
				theOutput = "ERREUR LINSERT : nombre d'arguments inccorect";
			} else{
			String clé = input[1];
			String mode = input[2];
			String valeur = input[3];
			String insert = input[4];

			theOutput = LINSERT(clé,mode,valeur,insert);
			}
		}else if(s.toUpperCase().startsWith("LPOP ")){
			String[] input = s.split(" ");
			String clé = input[1];
			theOutput = LPOP(clé);
		}else if(s.toUpperCase().startsWith("RPOP ")){
			String[] input = s.split(" ");
			String clé = input[1];
			theOutput = RPOP(clé);
		}else if(s.toUpperCase().startsWith("LLEN ")){
			String[] input = s.split(" ");
			String clé = input[1];
			theOutput = LLEN(clé);
		}else if(s.toUpperCase().startsWith("LSET ")){
			String[] input = s.split(" ");
			if(input.length != 4){
				theOutput = ("ERREUR LSET : nombre d'arguments incorrect");
				
			} else{
			String clé = input[1];
			int  index = Integer.parseInt(input[2]);
			String valeur = input[3];

			theOutput = LSET(clé,index,valeur);
			}

		}else if(s.toUpperCase().startsWith("SREM ")){
			String[] input = s.split(" ");
			String clé = input[1];
			LinkedList<String> valeur = new LinkedList<String>();
			for (int i=2;i<input.length;i++){
				valeur.add(input[i]);
			}
			theOutput = SREM(clé,valeur);
		}else if(s.toUpperCase().startsWith("DEL ")){
			String[] input = s.split(" ");
			LinkedList<String> clés = new LinkedList<String>();
			for (int i=1;i<input.length;i++){
				clés.add(input[i]);
			}
			theOutput = DEL(clés);

		}else if(s.toUpperCase().startsWith("FLUSHALL")){
			theOutput = FLUSHALL();

		}else if(s.toUpperCase().startsWith("EXISTS ")){
			String[] input = s.split(" ");
			if(input.length == 2){
				theOutput = EXISTS(input[1]);
			} else{
				theOutput = ("ERREUR EXISTS : nombre d'arguments incorrect");
				
			}				
		}else if(s.toUpperCase().startsWith("GET ")){
			String[] input = s.split(" ");
			if(input.length == 2){
				theOutput = GET(input[1]);
			}else{
				theOutput = ("ERREUR GET : nombre d'arguments incorrect");				
			}
		}else if(s.toUpperCase().startsWith("LRANGE ")){
			String[] input = s.split(" ");
			if(input.length == 4){
				String key = input[1];
				String start = input[2];
				String end = input[3];					
				theOutput = LRANGE(key,start,end);
			}else{
				theOutput = ("ERREUR LRANGE : nombre d'arguments incorrect");				
			}
		}else if(s.toUpperCase().startsWith("GETSET ")){
			String[] input = s.split(" ");
			if(input.length >= 3){
				String key = input[1];
				LinkedList<String> valeur = new LinkedList<String>();
				for (int i=2;i<input.length;i++){
					valeur.add(input[i]);
				}					
				theOutput = GETSET(key,valeur);
			}else{
				theOutput = ("ERREUR GETSET : nombre d'arguments incorrect");				
			}
		}else if(s.toUpperCase().startsWith("SET ")){
			String[] input = s.split(" ");
			if(input.length >= 3){
				String key = input[1];
				String valeur="";

				for (int i=2;i<input.length;i++){
					valeur=valeur.concat(input[i]);
				}					
				theOutput = SET(key,valeur);
			}else{
				theOutput = ("ERREUR SET : nombre d'arguments incorrect");				
			}
		}else if(s.toUpperCase().startsWith("APPEND ")){
			String[] input = s.split(" ");
			if(input.length == 3){
				theOutput = APPEND(input[1],input[2]);
			} else{
				theOutput = ("ERREUR APPEND : nombre d'arguments incorrect");				
			}				
		}
		else if(s.toUpperCase().startsWith("DECR ")){
			String[] input = s.split(" ");
			if(input.length == 2){
				theOutput = DECR(input[1]);
			} else{
				theOutput = ("ERREUR DECR : nombre d'arguments incorrect");
				
			}				
		}
		else if(s.toUpperCase().startsWith("INCR ")){
			String[] input = s.split(" ");
			if(input.length == 2){
				theOutput = INCR(input[1]);
			} else{
				theOutput = ("ERREUR INCR : nombre d'arguments incorrect");				
			}				
		}
		else if(s.toUpperCase().startsWith("ECHO ")){
			theOutput = ECHO(s);
		}else if(s.toUpperCase().startsWith("COMMAND ")){
			theOutput = COMMAND();
		}else if(s.toUpperCase().startsWith("HSET ")){
			String[] input = s.split(" ");
			if(input.length == 4){
				String key = input[1];
				String field = input[2];
				String value = input[3];							
				theOutput = HSET(key,field,value);

			} else{
				theOutput = ("ERREUR HSET : nombre d'arguments incorrect");				
			}	
		}else if(s.toUpperCase().startsWith("HGET ")){
			String[] input = s.split(" ");
			if(input.length == 3){
				String key = input[1];
				String field = input[2];						
				theOutput = HGET(key,field);

			} else{
				theOutput = ("ERREUR HGET : nombre d'arguments incorrect");				
			}	
		}else if(s.toUpperCase().startsWith("HDEL ")){
			String[] input = s.split(" ");
			if(input.length == 3){
				String key = input[1];
				String field = input[2];						
				theOutput = HDEL(key,field);

			} else{
				theOutput = ("ERREUR HDEL : nombre d'arguments incorrect");				
			}	
		}else if(s.toUpperCase().startsWith("HEXISTS ")){
			String[] input = s.split(" ");
			if(input.length == 3){
				String key = input[1];
				String field = input[2];						
				theOutput = HEXISTS(key,field);

			} else{
				theOutput = ("ERREUR HEXISTS : nombre d'arguments incorrect");				
			}	
		}else if(s.toUpperCase().startsWith("HKEYS ")){
			String[] input = s.split(" ");
			if(input.length == 2){
				String key = input[1];						
				theOutput = HKEYS(key);

			} else{
				theOutput = ("ERREUR HKEYS : nombre d'arguments incorrect");				
			}	
		} else if(s.toUpperCase().startsWith("HLEN ")){
			String[] input = s.split(" ");
			if(input.length == 2){
				String key = input[1];						
				theOutput = HLEN(key);

			} else{
				theOutput = ("ERREUR HLEN : nombre d'arguments incorrect");				
			}	
		} else if(s.toUpperCase().startsWith("HSTRLEN ")){
			String[] input = s.split(" ");
			if(input.length == 3){
				String key = input[1];	
				String field = input[2];
				theOutput = HSTRLEN(key,field);

			} else{
				theOutput = ("ERREUR HLEN : nombre d'arguments incorrect");
				
			}	
		} else if(s.toUpperCase().startsWith("HVALS ")){
			String[] input = s.split(" ");
			if(input.length == 2){
				String key = input[1];	
				theOutput = HVALS(key);

			} else{
				theOutput = ("ERREUR HLEN : nombre d'arguments incorrect");				
			}	
		}else if(s.toUpperCase().startsWith("HINCRBY ")){
			String[] input = s.split(" ");
			if(input.length == 4){
				String key = input[1];	
				String field = input[2];
				String incr = input[3];
				theOutput = HINCRBY(key,field,incr);

			} else{
				theOutput = ("ERREUR HLEN : nombre d'arguments incorrect");				
			}	
		}

		return theOutput;
	}


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
					return ("(erreur) LINSERT : mode incorrect.");
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
			return ("ERREUR : pas de clé à ce nom");
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
			return ("ERREUR : pas de clé à ce nom");
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
		tableHash.clear();
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
	public String LRANGE(String key, String startS, String endS) {

		if(!estUnEntier(startS) && !estUnEntier(endS)){
			return ("ERREUR : la ou les valeurs start et end ne sont pas des entiers");
			
		} 
		int start = Integer.parseInt(startS);
		int end = Integer.parseInt(endS);			


		if(table.containsKey(key)){
			LinkedList<String> tmp = table.get(key);
			String res = "";
			int cmp=0;
			if(end == -1){
				end = tmp.size()-1;
			}
			if(start >tmp.size()-1){
				return "(liste vide ou inexistante)";
			}
			if(end >tmp.size()-1){
				end = tmp.size()-1;
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
				return ("ERREUR : value n'est pas un entier");
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
				return ("ERREUR : value n'est pas un entier");
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
		String Commandes;
		Commandes = "LPUSH "
				+ "LPUSHX "
				+ "RPUSH "
				+ "RPUSHX "
				+ "LINSERT "
				+ "LPOP "
				+ "RPOP "
				+ "LLEN "
				+ "LSET "
				+ "DEL "
				+ "FLUSHALL "
				+ "EXISTS "
				+ "GET "
				+ "LRANGE "
				+ "GETSET "
				+ "SET "
				+ "APPEND "
				+ "DECR "
				+ "INCR "
				+ "ECHO "
				+ "COMMAND "
				+ "HSET "
				+ "HGET "
				+ "HDEL "
				+ "HEXISTS "
				+ "HKEYS "
				+ "HLEN "
				+ "HSTRLEN "
				+ "HVALS "
				+ "HINCRBY ";
		return Commandes;
	}

	@Override
	public String HSET(String key, String field, String value) {
		String res="";

		if(tableHash.containsKey(key)){
			LinkedList<Hashtable<String,String>> listeFields = tableHash.get(key);
			if(estDansHash(listeFields, field)){
				setValueH(listeFields, field, value);
			} else {
				Hashtable<String,String> tmp = new Hashtable<String,String>();
				tmp.put(field, value);
				listeFields.add(tmp);
			}
			res = "(integer) 1";

		}else{
			Hashtable<String,String> tmp = new Hashtable<String,String>();
			tmp.put(field, value);
			LinkedList<Hashtable<String,String>> ltmp = new LinkedList<Hashtable<String,String>>();
			ltmp.add(tmp);
			tableHash.put(key, ltmp);
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
			if(estDansHash(tableHash.get(key),field)){
				Hashtable<String,String> tmp = new Hashtable<String,String>();
				tmp.put(field, valueH(tableHash.get(key), field));
				tableHash.get(key).remove(tmp);
				res = "(integer) 1";
			}
		}
		else{
			res = "(integer) 0";
		}
		return res;
	}

	@Override
	public String HEXISTS(String key, String field) {
		if(tableHash.containsKey(key)){
			if(estDansHash(tableHash.get(key),field)){
				return "(integer) 1";
			}
		}
		return "(integer) 0";
	}

	@Override
	public String HKEYS(String key) {
		String res="";
		if(tableHash.containsKey(key)){
			int cmp = 1;
			for(Hashtable<String,String> h: tableHash.get(key)){
				Enumeration<String> e = h.keys();				
				while (e.hasMoreElements()){
					res+=cmp+") "+e.nextElement()+"\n";
					cmp ++;
				}
			}
			return res;
		} else {
			return "Clé inexistante";
		}
	}

	@Override
	public String HLEN(String key) {
		if(tableHash.containsKey(key)){
			return "(integer) "+tableHash.get(key).size();
		} else {
			return "Clé inexistante";
		}
	}

	@Override
	public String HSTRLEN(String key, String field) {
		if(tableHash.containsKey(key)){
			if(estDansHash(tableHash.get(key), field)){
				int l = valueH(tableHash.get(key), field).length();
				return "(integer) "+l;
			}	
		}
		return "Clé inexistante";
	}

	@Override
	public String HVALS(String key) {
		String res = "";
		if(tableHash.containsKey(key)){
			int cmp = 1;
			for(Hashtable<String,String> h: tableHash.get(key)){
				Enumeration<String> e = h.keys();				
				while (e.hasMoreElements()){
					res+=cmp+") "+valueH(tableHash.get(key),e.nextElement())+"\n";
					cmp ++;
				}
			}
			return res;
		}
		return "Clé inexistante";
	}

	@Override
	public String HINCRBY(String key, String field, String valIncr) {
		if(tableHash.containsKey(key)){

			if(estDansHash(tableHash.get(key), field)){
				String res = valueH(tableHash.get(key),field);
				if(estUnEntier(res)){
					int i=Integer.parseInt(res)+Integer.parseInt(valIncr);

					HSET(key,field,String.valueOf(i));
					return "(integer) "+i;
				}else{
					return("ERREUR : La valeur n'est pas un entier");
					
				}				
			} else{		
				if(estUnEntier(valIncr)){
					HSET(key,field, valIncr);
					return ("(integer) 1");
				}else{
					return("ERREUR : La valeur n'est pas un entier");
					
				}
			}
		}else{		
			if(estUnEntier(valIncr)){
				HSET(key,field, valIncr);
				return ("(integer) 1");
			}else{
				return("ERREUR : La valeur n'est pas un entier");
				
			}
		}
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
				return h.get(key);
			}
		}
		return res;
	}

	public void setValueH(LinkedList<Hashtable<String,String>> lhash, String key, String value ){		
		for(Hashtable<String,String> h: lhash){
			if(h.containsKey(key)){
				h.put(key, value);
			}
		}
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
