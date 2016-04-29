package test.java.fr.uga.erods;


import junit.framework.TestCase;
import main.java.fr.uga.erods.Transaction;
import main.java.fr.uga.erods.Transaction;

public class TransactionTest extends TestCase {

	public void testLPUSHCreation() {		
		//Insertion d'un élément dans une nouvelle clé
		Transaction sp = new Transaction();
		String theInput = "LPUSH friends Thomas";
		sp.transact(theInput); //2 pour accéder directement à l'état MANIPULATION
		assertEquals("élément présent dans la clé : ","Thomas",sp.table.get("friends").get(0) );
	}
		
	public void testLPUSHElement() {		
		//Insertion d'un élément à gauche dans une clé existante
		Transaction sp = new Transaction();
		String theInput = "LPUSH friends Thomas";
		sp.transact(theInput); 
		theInput = "LPUSH friends Julie";
		sp.transact(theInput);

		String resultatObtenu = sp.table.get("friends").get(0) + " " + sp.table.get("friends").get(1);
		assertEquals("insertion élément par LPUSH, clé existe :", "Julie Thomas",resultatObtenu);
	}

	public void testLPUSHElements() {		
		//Insertion d'une liste d'éléments à gauche
		Transaction sp = new Transaction();
		String theInput = "LPUSH friends Thomas Julie";
		String m = sp.transact(theInput); //2 pour accéder directement à l'état MANIPULATION
		assertEquals("insertion plusieurs éléments par LPUSH :", "(integer) 2",m);
	}


	public void testRPUSH() {
		//Insertion d'un élément à droite dans une clé existante
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput); 
		theInput = "RPUSH friends Julie";
		sp.transact(theInput);

		String resultatObtenu = sp.table.get("friends").get(0) + " " + sp.table.get("friends").get(1);
		assertEquals("insertion élément par RPUSH, clé existe :", "Thomas Julie",resultatObtenu);
	}

	public void testLPUSHX() {
		//Insertion d'un élément à gauche dans une clé existante
		Transaction sp = new Transaction();
		String theInput = "LPUSH friends Thomas";
		sp.transact(theInput); 
		theInput = "LPUSHX friends Julie";
		sp.transact(theInput);

		String resultatObtenu = sp.table.get("friends").get(0) + " " + sp.table.get("friends").get(1);
		assertEquals("insertion élément par LPUSHX, clé existe :", "Julie Thomas",resultatObtenu);

	}

	public void testLPUSHXError() {
		//Insertion d'un élément à gauche dans une clé inexistante avec LPUSHX
		Transaction sp = new Transaction();
		String theInput = "LPUSHX friends Thomas";
		sp.transact(theInput); 		
		assertEquals("insertion élément par LPUSHX, clé existe pas :", 0,sp.table.size());
	}



	public void testRPUSHX() {
		//Insertion d'un élément à droite dans une clé existante
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput); 
		theInput = "RPUSHX friends Julie";
		sp.transact(theInput);

		String resultatObtenu = sp.table.get("friends").get(0) + " " + sp.table.get("friends").get(1);
		assertEquals("insertion élément par RPUSHX, clé existe :", "Thomas Julie",resultatObtenu);
	}

	public void testLINSERTAFTER() {
		//Insertion d'un élément après une valeur existante dans une clé existante
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput); 
		theInput = "LINSERT friends AFTER Thomas Julie";
		String m = sp.transact(theInput);
		assertEquals("insertion avec LINSERT AFTER : ","2",m);
	}

	public void testLINSERTBEFORE() {
		//Insertion d'un élément avant une valeur existante dans une clé existante
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput); 
		theInput = "LINSERT friends BEFORE Thomas Julie";
		sp.transact(theInput);

		String resultatObtenu = sp.table.get("friends").get(0) + " " + sp.table.get("friends").get(1);
		assertEquals("insertion avec LINSERT BEFORE :", "Julie Thomas",resultatObtenu);
	}

	public void testLINSERTValInex() {
		//Insertion d'un élément avant une valeur inexistante dans une clé existante
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput); 
		theInput = "LINSERT friends BEFORE Lorrie Julie";
		String m = sp.transact(theInput);

		assertEquals("insertion avec LINSERT valeur inexistante :", "-1",m);
	}
	
	public void testLINSERTErrArg() {
		//Insertion d'un élément avant une valeur inexistante dans une clé existante
		Transaction t = new Transaction();
		String theInput = "RPUSH friends Thomas";
		t.transact(theInput); 
		theInput = "LINSERT friends BEFORE Julie";
		String m = t.transact(theInput);

		assertTrue("insertion avec LINSERT erreur nombre d'argument", m.contains("nombre d'arguments inccorect"));
	}

	public void testLINSERTCléInex() {
		//Insertion d'uString theInput = "RPUSH friends Thomas";
		Transaction sp = new Transaction();
		String theInput = "LINSERT friends BEFORE Lorrie Julie";
		String m = sp.transact(theInput);

		assertEquals("insertion avec LINSERT clé inexistante :", "0",m);
	}


	public void testLPOP() {
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput);
		theInput = "RPUSH friends Julie";
		sp.transact(theInput); 
		theInput = "LPOP friends";
		String m = sp.transact(theInput);	
		assertEquals("LPOP :", "Thomas",m);
	}

	public void testRPOP() {
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput);
		theInput = "RPUSH friends Julie";
		sp.transact(theInput); 
		theInput = "RPOP friends";
		String m = sp.transact(theInput);

		assertEquals("LPOP :", "Julie",m);

	}


	public void testLLEN() {
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput);
		theInput = "RPUSH friends Julie";
		sp.transact(theInput);
		theInput = "LLEN friends";
		String m=sp.transact(theInput);

		assertEquals("LLEN :", "(integer) 2",m);
	}

	public void testLSET() {
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput);
		theInput = "RPUSH friends Julie";
		sp.transact(theInput);
		theInput = "LSET friends 1 Lorrie";
		sp.transact(theInput);

		assertEquals("LSET :", "Lorrie",sp.table.get("friends").get(1));
	}
	
	public void testLSETErrArg() {
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput);
		theInput = "RPUSH friends Julie";
		sp.transact(theInput);
		theInput = "LSET friends";
		String m = sp.transact(theInput);

		assertTrue("insertion avec LSET erreur nombre d'argument", m.contains("arguments incorrect"));
	}

	public void testSREM() {
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput);
		theInput = "RPUSH friends Julie";
		sp.transact(theInput);
		theInput = "RPUSH friends Lorrie";
		sp.transact(theInput);
		theInput = "RPUSH friends Margaux";
		sp.transact(theInput);
		theInput = "SREM friends Lorrie Julie";
		sp.transact(theInput);

		String resultatObtenu = sp.table.get("friends").get(0)+ " " + sp.table.get("friends").get(1); 
		assertEquals("SREM :", "Thomas Margaux",resultatObtenu);

	}

	public void testDEL1elem() {
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput);
		theInput = "RPUSH friends Julie";
		sp.transact(theInput);
		theInput = "DEL friends";
		sp.transact(theInput);

		assertFalse("DEL 1 element : ", sp.table.containsKey("friends"));
	}

	public void testDEL2elem() {
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput);
		theInput = "RPUSH salade batavia";
		sp.transact(theInput);
		theInput = "DEL friends salade";
		String m = sp.transact(theInput);

		assertEquals("DEL :", "(integer) 2",m);

	}

	public void testFLUSHALL() {
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput);
		theInput = "RPUSH salade batavia";
		sp.transact(theInput);
		theInput = "RPUSH banane plantin";
		sp.transact(theInput);
		theInput = "FLUSHALL";
		sp.transact(theInput);

		assertEquals("FLUSHALL : ", 0, sp.table.size());
	}

	public void testEXISTSTrue() {
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput);
		theInput = "RPUSH salade batavia";
		sp.transact(theInput);
		theInput = "EXISTS salade";
		sp.transact(theInput);

		assertTrue("EXISTS True : ", sp.table.containsKey("salade"));
	}

	public void testEXISTSFalse() {
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput);
		theInput = "RPUSH salade batavia";
		sp.transact(theInput);
		theInput = "EXISTS banane";
		sp.transact(theInput);

		assertFalse("EXISTS False : ", sp.table.containsKey("banane"));
	}

	public void testGET() {
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput);
		theInput = "RPUSH friends Julie";
		sp.transact(theInput);
		theInput = "GET friends";
		String m = sp.transact(theInput);

		String resAttendu = "1) Thomas\n2) Julie\n";
		assertEquals("GET :", resAttendu, m);		
	}

	public void testLRANGETous() {
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput);
		theInput = "RPUSH friends Julie";
		sp.transact(theInput);
		theInput = "RPUSH friends Lorrie";
		sp.transact(theInput);
		theInput = "RPUSH friends Margaux";
		sp.transact(theInput);
		theInput = "LRANGE friends 0 -1";
		String m = sp.transact(theInput);
		
		String resAttendu = "1) Thomas\n2) Julie\n3) Lorrie\n4) Margaux\n";
		assertEquals("GET tous les éléments :", resAttendu, m);	
	}
	
	public void testLRANGE() {
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput);
		theInput = "RPUSH friends Julie";
		sp.transact(theInput);
		theInput = "RPUSH friends Lorrie";
		sp.transact(theInput);
		theInput = "RPUSH friends Margaux";
		sp.transact(theInput);
		theInput = "RPUSH friends Apo";
		sp.transact(theInput);
		
		theInput = "LRANGE friends 1 3";
		String m = sp.transact(theInput);
		
		String resAttendu = "1) Julie\n2) Lorrie\n3) Margaux\n";
		assertEquals("GET :", resAttendu, m);	
		
	}
	

	public void testGETSETIncorrect() {
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput);
		theInput = "RPUSH friends Julie";
		sp.transact(theInput);
		theInput = "GETSET salade batavia";
		sp.transact(theInput);
		
		assertFalse("GETSET clé inexistante : ",sp.table.containsKey("salade"));
	}
	
	public void testGETSET() {
		Transaction sp = new Transaction();
		String theInput = "RPUSH friends Thomas";
		sp.transact(theInput);
		theInput = "RPUSH friends Julie";
		sp.transact(theInput);
		theInput = "GETSET friends Lorrie Margaux";
		sp.transact(theInput);
		
		String resObtenu = sp.table.get("friends").get(0)+" "+sp.table.get("friends").get(1);
		String resAttendu = "Lorrie Margaux";
		
		assertEquals("GETSET clé existante : ",resAttendu,resObtenu);
	}

	public void testSETNewKey() {
		Transaction sp = new Transaction();
		String theInput = "SET friends Lorrie";
		sp.transact(theInput);
		assertTrue("SET clé inexistante ",sp.table.get("friends").get(0).equals("Lorrie"));
	}
	
	public void testSET() {
		Transaction sp = new Transaction();
		String theInput = "SET friends Thomas";
		theInput = "SET friends Lorrie";
		sp.transact(theInput);
		
		assertTrue("SET clé existante ",sp.table.get("friends").get(0).equals("Lorrie") && sp.table.get("friends").size()==1);
	}

	public void testAPPEND() {
		Transaction sp = new Transaction();
		String theInput = "SET friend Lo";
		sp.transact(theInput);
		theInput = "APPEND friend to";
		sp.transact(theInput);
		
		assertEquals("SET clé existante ","Loto",sp.table.get("friend").get(0));
	}

	public void testDECR() {
		Transaction sp = new Transaction();
		String theInput = "SET number 24";
		sp.transact(theInput);
		theInput = "DECR number";
		sp.transact(theInput);
		
		assertTrue("DECR ",sp.table.get("number").get(0).equals("23"));
	}

	public void testINCR() {
		Transaction sp = new Transaction();
		String theInput = "SET number 24";
		sp.transact(theInput);
		theInput = "INCR number";
		sp.transact(theInput);
		
		assertTrue("INCR ",sp.table.get("number").get(0).equals("25"));
	}
	public void testECHO() {
		Transaction sp = new Transaction();
		String theInput = "ECHO message enregistré !";
		String m = sp.transact(theInput);
		
		assertEquals("ECHO : ","message enregistré !",m);
	}
/*
	public void testCOMMAND() {
		fail("Not yet implemented");
	}
*/
	
	public void testHSETNewKey() {
		Transaction sp = new Transaction();
		String theInput = "HSET Pomme Grany verte";
		sp.transact(theInput);
		
		assertTrue("HSET clés inexistantes",sp.tableHash.get("Pomme").get(0).contains("verte") && sp.tableHash.get("Pomme").size()==1);
	}
	
	public void testHSET() {
		Transaction sp = new Transaction();
		String theInput = "HSET Pomme Grany verte";
		sp.transact(theInput);
		theInput = "HSET Pomme Grany mure";
		sp.transact(theInput);
		
		assertTrue("HSET clés existantes",sp.tableHash.get("Pomme").get(0).get("Grany").equals("mure") && sp.tableHash.get("Pomme").size()==1);
	}
	
	public void testHSETMultiple() {
		Transaction sp = new Transaction();
		String theInput = "HSET Pomme Grany verte";
		sp.transact(theInput);
		theInput = "HSET Pomme Reinette rouge";
		sp.transact(theInput);
		
		assertTrue("HSET clés existantes",sp.estDansHash(sp.tableHash.get("Pomme"),("Grany")) && sp.estDansHash(sp.tableHash.get("Pomme"),("Reinette")));
	}
	
	

	public void testHGET() {
		Transaction sp = new Transaction();
		String theInput = "HSET Pomme Grany verte";
		sp.transact(theInput);
		theInput = "HGET Pomme Grany";
		String m = sp.transact(theInput);
		
		assertEquals("HGET clés existantes","verte",m);
	}
	

	public void testHDEL() {
		Transaction sp = new Transaction();
		String theInput = "HSET Pomme Grany verte";
		sp.transact(theInput);
		theInput = "HDEL Pomme Grany";
		sp.transact(theInput);
		
		assertFalse("HDEL clés existantes",sp.estDansHash(sp.tableHash.get("Pomme"),("Grany")));
	}
	
	public void testHDELvoid() {
		Transaction sp = new Transaction();
		String theInput = "HSET Pomme Grany verte";
		sp.transact(theInput);
		theInput = "HDEL Pomme Pote";
		sp.transact(theInput);
		
		assertFalse("HDEL clés existantes",!sp.estDansHash(sp.tableHash.get("Pomme"),("Grany")) && sp.estDansHash(sp.tableHash.get("Pomme"),"Pote"));
	}

	public void testHEXISTSTrue() {
		Transaction sp = new Transaction();
		String theInput = "HSET Pomme Grany verte";
		sp.transact(theInput);
		theInput = "HEXISTS Pomme Grany";
		String m = sp.transact(theInput);
		
		assertEquals("HEXISTS clés existantes","(integer) 1",m);
	}
	
	public void testHEXISTSFalse() {
		Transaction sp = new Transaction();
		String theInput = "HSET Pomme Grany verte";
		sp.transact(theInput);
		theInput = "HEXISTS Pomme Pote";
		String m = sp.transact(theInput);
		
		assertEquals("HEXISTS clés existantes","(integer) 0",m);
	}
	
	
	

	public void testHKEYS() {
		Transaction sp = new Transaction();
		String theInput = "HSET Pomme Grany verte";
		sp.transact(theInput);
		theInput = "HSET Pomme Golden jaune";
		sp.transact(theInput);
		theInput = "HSET Pomme Reinette rouge";
		sp.transact(theInput);
		theInput = "HKEYS Pomme";
		String m = sp.transact(theInput);
		
		
		String resAttendu= "1) Grany\n2) Golden\n3) Reinette\n";
		assertEquals("HKEYS clés existantes",resAttendu,m);
	}

	
	public void testHLEN() {
		Transaction sp = new Transaction();
		String theInput = "HSET Pomme Grany verte";
		sp.transact(theInput);
		theInput = "HSET Pomme Golden jaune";
		sp.transact(theInput);
		theInput = "HSET Pomme Reinette rouge";
		sp.transact(theInput);
		theInput = "HLEN Pomme";
		sp.transact(theInput);
		
		assertEquals("HKEYS clés existantes",3,sp.tableHash.get("Pomme").size());
	}
	

	public void testHSTRLEN() {
		Transaction sp = new Transaction();
		String theInput = "HSET Pomme Grany verte";
		sp.transact(theInput);
		theInput = "HSTRLEN Pomme Grany";
		sp.transact(theInput);
		
		assertEquals("HSTRLEN clés existantes",5,sp.valueH(sp.tableHash.get("Pomme"),"Grany").length());	
	}

	public void testHVALS() {
		Transaction sp = new Transaction();
		String theInput = "HSET Pomme Grany verte";
		sp.transact(theInput);
		theInput = "HSET Pomme Smith jaune";
		sp.transact(theInput);
		theInput = "HSET Pomme Pote rouge";
		sp.transact(theInput);
		theInput = "HVALS Pomme";
		String m = sp.transact(theInput);
		
		String resAttendu = "1) verte\n2) jaune\n3) rouge\n";
		
		assertEquals("HVALS clés existantes",resAttendu,m);	
	
	}

	public void testHINCRBY() {
		Transaction sp = new Transaction();
		String theInput = "HSET Numero Acces 10";
		sp.transact(theInput);
		theInput = "HINCRBY Numero Acces 15";
		sp.transact(theInput);
		
		assertEquals("HINCRBY clés existantes","25",sp.valueH(sp.tableHash.get("Numero"), "Acces"));	
	}

}
