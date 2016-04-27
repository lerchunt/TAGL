package test.java.fr.uga.erods;


import junit.framework.TestCase;
import main.java.fr.uga.erods.ServerProtocol;

public class ServerProtocolTest extends TestCase {

	public void testLPUSHCreation() {		
		//Insertion d'un élément dans une nouvelle clé
		ServerProtocol sp = new ServerProtocol();
		String theInput = "LPUSH friends Thomas";
		sp.processInput(theInput,2); //2 pour accéder directement à l'état MANIPULATION
		assertEquals("élément présent dans la clé : ","Thomas",sp.table.get("friends").get(0) );
	}

	
	
	public void testLPUSHElement() {		
		//Insertion d'un élément à gauche dans une clé existante
		ServerProtocol sp = new ServerProtocol();
		String theInput = "LPUSH friends Thomas";
		sp.processInput(theInput,2); 
		theInput = "LPUSH friends Julie";
		sp.processInput(theInput,2);

		String resultatObtenu = sp.table.get("friends").get(0) + " " + sp.table.get("friends").get(1);
		assertEquals("insertion élément par LPUSH, clé existe :", "Julie Thomas",resultatObtenu);
	}

	public void testLPUSHElements() {		
		//Insertion d'une liste d'éléments à gauche
		ServerProtocol sp = new ServerProtocol();
		String theInput = "LPUSH friends Thomas Julie";
		String m = sp.processInput(theInput,2); //2 pour accéder directement à l'état MANIPULATION
		assertEquals("insertion plusieurs éléments par LPUSH :", "(integer) 2",m);
	}


	public void testRPUSH() {
		//Insertion d'un élément à droite dans une clé existante
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2); 
		theInput = "RPUSH friends Julie";
		sp.processInput(theInput,2);

		String resultatObtenu = sp.table.get("friends").get(0) + " " + sp.table.get("friends").get(1);
		assertEquals("insertion élément par RPUSH, clé existe :", "Thomas Julie",resultatObtenu);
	}

	public void testLPUSHX() {
		//Insertion d'un élément à gauche dans une clé existante
		ServerProtocol sp = new ServerProtocol();
		String theInput = "LPUSH friends Thomas";
		sp.processInput(theInput,2); 
		theInput = "LPUSHX friends Julie";
		sp.processInput(theInput,2);

		String resultatObtenu = sp.table.get("friends").get(0) + " " + sp.table.get("friends").get(1);
		assertEquals("insertion élément par LPUSHX, clé existe :", "Julie Thomas",resultatObtenu);

	}

	public void testLPUSHXError() {
		//Insertion d'un élément à gauche dans une clé inexistante avec LPUSHX
		ServerProtocol sp = new ServerProtocol();
		String theInput = "LPUSHX friends Thomas";
		sp.processInput(theInput,2); 		
		assertEquals("insertion élément par LPUSHX, clé existe pas :", 0,sp.table.size());
	}



	public void testRPUSHX() {
		//Insertion d'un élément à droite dans une clé existante
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2); 
		theInput = "RPUSHX friends Julie";
		sp.processInput(theInput,2);

		String resultatObtenu = sp.table.get("friends").get(0) + " " + sp.table.get("friends").get(1);
		assertEquals("insertion élément par RPUSHX, clé existe :", "Thomas Julie",resultatObtenu);
	}

	public void testLINSERTAFTER() {
		//Insertion d'un élément après une valeur existante dans une clé existante
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2); 
		theInput = "LINSERT friends AFTER Thomas Julie";
		String m = sp.processInput(theInput,2);
		assertEquals("insertion avec LINSERT AFTER : ","2",m);
	}

	public void testLINSERTBEFORE() {
		//Insertion d'un élément avant une valeur existante dans une clé existante
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2); 
		theInput = "LINSERT friends BEFORE Thomas Julie";
		sp.processInput(theInput,2);

		String resultatObtenu = sp.table.get("friends").get(0) + " " + sp.table.get("friends").get(1);
		assertEquals("insertion avec LINSERT BEFORE :", "Julie Thomas",resultatObtenu);
	}

	public void testLINSERTValInex() {
		//Insertion d'un élément avant une valeur inexistante dans une clé existante
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2); 
		theInput = "LINSERT friends BEFORE Lorrie Julie";
		String m = sp.processInput(theInput,2);

		assertEquals("insertion avec LINSERT valeur inexistante :", "-1",m);
	}

	public void testLINSERTCléInex() {
		//Insertion d'uString theInput = "RPUSH friends Thomas";
		ServerProtocol sp = new ServerProtocol();
		String theInput = "LINSERT friends BEFORE Lorrie Julie";
		String m = sp.processInput(theInput,2);

		assertEquals("insertion avec LINSERT clé inexistante :", "0",m);
	}


	public void testLPOP() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Julie";
		sp.processInput(theInput,2); 
		theInput = "LPOP friends";
		String m = sp.processInput(theInput,2);	
		assertEquals("LPOP :", "Thomas",m);
	}

	public void testRPOP() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Julie";
		sp.processInput(theInput,2); 
		theInput = "RPOP friends";
		String m = sp.processInput(theInput,2);

		assertEquals("LPOP :", "Julie",m);

	}


	public void testLLEN() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Julie";
		sp.processInput(theInput,2);
		theInput = "LLEN friends";
		String m=sp.processInput(theInput,2);

		assertEquals("LLEN :", "(integer) 2",m);
	}

	public void testLSET() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Julie";
		sp.processInput(theInput,2);
		theInput = "LSET friends 1 Lorrie";
		sp.processInput(theInput,2);

		assertEquals("LSET :", "Lorrie",sp.table.get("friends").get(1));
	}

	public void testSREM() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Julie";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Lorrie";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Margaux";
		sp.processInput(theInput,2);
		theInput = "SREM friends Lorrie Julie";
		sp.processInput(theInput,2);

		String resultatObtenu = sp.table.get("friends").get(0)+ " " + sp.table.get("friends").get(1); 
		assertEquals("SREM :", "Thomas Margaux",resultatObtenu);

	}

	public void testDEL1elem() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Julie";
		sp.processInput(theInput,2);
		theInput = "DEL friends";
		sp.processInput(theInput,2);

		assertFalse("DEL 1 element : ", sp.table.containsKey("friends"));
	}

	public void testDEL2elem() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2);
		theInput = "RPUSH salade batavia";
		sp.processInput(theInput,2);
		theInput = "DEL friends salade";
		String m = sp.processInput(theInput,2);

		assertEquals("DEL :", "(integer) 2",m);

	}

	public void testFLUSHALL() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2);
		theInput = "RPUSH salade batavia";
		sp.processInput(theInput,2);
		theInput = "RPUSH banane plantin";
		sp.processInput(theInput,2);
		theInput = "FLUSHALL";
		sp.processInput(theInput,2);

		assertEquals("FLUSHALL : ", 0, sp.table.size());
	}

	public void testEXISTSTrue() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2);
		theInput = "RPUSH salade batavia";
		sp.processInput(theInput,2);
		theInput = "EXISTS salade";
		sp.processInput(theInput,2);

		assertTrue("EXISTS True : ", sp.table.containsKey("salade"));
	}

	public void testEXISTSFalse() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2);
		theInput = "RPUSH salade batavia";
		sp.processInput(theInput,2);
		theInput = "EXISTS banane";
		sp.processInput(theInput,2);

		assertFalse("EXISTS False : ", sp.table.containsKey("banane"));
	}

	public void testGET() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Julie";
		sp.processInput(theInput,2);
		theInput = "GET friends";
		String m = sp.processInput(theInput,2);

		String resAttendu = "1) Thomas\n2) Julie\n";
		assertEquals("GET :", resAttendu, m);		
	}

	public void testLRANGETous() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Julie";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Lorrie";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Margaux";
		sp.processInput(theInput,2);
		theInput = "LRANGE friends 0 -1";
		String m = sp.processInput(theInput,2);
		
		String resAttendu = "1) Thomas\n2) Julie\n3) Lorrie\n4) Margaux\n";
		assertEquals("GET tous les éléments :", resAttendu, m);	
	}
	
	public void testLRANGE() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Julie";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Lorrie";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Margaux";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Apo";
		sp.processInput(theInput,2);
		
		theInput = "LRANGE friends 1 3";
		String m = sp.processInput(theInput,2);
		
		String resAttendu = "1) Julie\n2) Lorrie\n3) Margaux\n";
		assertEquals("GET :", resAttendu, m);	
		
	}
	

	public void testGETSETIncorrect() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Julie";
		sp.processInput(theInput,2);
		theInput = "GETSET salade batavia";
		sp.processInput(theInput,2);
		
		assertFalse("GETSET clé inexistante : ",sp.table.containsKey("salade"));
	}
	
	public void testGETSET() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2);
		theInput = "RPUSH friends Julie";
		sp.processInput(theInput,2);
		theInput = "GETSET friends Lorrie Margaux";
		sp.processInput(theInput,2);
		
		String resObtenu = sp.table.get("friends").get(0)+" "+sp.table.get("friends").get(1);
		String resAttendu = "Lorrie Margaux";
		
		assertEquals("GETSET clé existante : ",resAttendu,resObtenu);
	}

	public void testSETNewKey() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "SET friends Lorrie";
		sp.processInput(theInput,2);
		assertTrue("SET clé inexistante ",sp.table.get("friends").get(0).equals("Lorrie"));
	}
	
	public void testSET() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "SET friends Thomas";
		theInput = "SET friends Lorrie";
		sp.processInput(theInput,2);
		
		assertTrue("SET clé existante ",sp.table.get("friends").get(0).equals("Lorrie") && sp.table.get("friends").size()==1);
	}

	public void testAPPEND() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "SET friend Lo";
		sp.processInput(theInput,2);
		theInput = "APPEND friend to";
		sp.processInput(theInput,2);
		
		assertEquals("SET clé existante ","Loto",sp.table.get("friend").get(0));
	}

	public void testDECR() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "SET number 24";
		sp.processInput(theInput,2);
		theInput = "DECR number";
		sp.processInput(theInput,2);
		
		assertTrue("DECR ",sp.table.get("number").get(0).equals("23"));
	}

	public void testINCR() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "SET number 24";
		sp.processInput(theInput,2);
		theInput = "INCR number";
		sp.processInput(theInput,2);
		
		assertTrue("INCR ",sp.table.get("number").get(0).equals("25"));
	}
	public void testECHO() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "ECHO message enregistré !";
		String m = sp.processInput(theInput,2);
		
		assertEquals("ECHO : ","message enregistré !",m);
	}
/*
	public void testCOMMAND() {
		fail("Not yet implemented");
	}
*/
	
	public void testHSETNewKey() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "HSET Pomme Grany verte";
		sp.processInput(theInput,2);
		
		assertTrue("HSET clés inexistantes",sp.tableHash.get("Pomme").get(0).contains("verte") && sp.tableHash.get("Pomme").size()==1);
	}
	
	public void testHSET() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "HSET Pomme Grany verte";
		sp.processInput(theInput,2);
		theInput = "HSET Pomme Grany mure";
		sp.processInput(theInput,2);
		
		assertTrue("HSET clés existantes",sp.tableHash.get("Pomme").get(0).get("Grany").equals("mure") && sp.tableHash.get("Pomme").size()==1);
	}
	
	public void testHSETMultiple() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "HSET Pomme Grany verte";
		sp.processInput(theInput,2);
		theInput = "HSET Pomme Reinette rouge";
		sp.processInput(theInput,2);
		
		assertTrue("HSET clés existantes",sp.estDansHash(sp.tableHash.get("Pomme"),("Grany")) && sp.estDansHash(sp.tableHash.get("Pomme"),("Reinette")));
	}
	
	

	public void testHGET() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "HSET Pomme Grany verte";
		sp.processInput(theInput,2);
		theInput = "HGET Pomme Grany";
		String m = sp.processInput(theInput,2);
		
		assertEquals("HGET clés existantes","verte",m);
	}
	

	public void testHDEL() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "HSET Pomme Grany verte";
		sp.processInput(theInput,2);
		theInput = "HDEL Pomme Grany";
		sp.processInput(theInput,2);
		
		assertFalse("HDEL clés existantes",sp.estDansHash(sp.tableHash.get("Pomme"),("Grany")));
	}
	
	public void testHDELvoid() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "HSET Pomme Grany verte";
		sp.processInput(theInput,2);
		theInput = "HDEL Pomme Pote";
		sp.processInput(theInput,2);
		
		assertFalse("HDEL clés existantes",!sp.estDansHash(sp.tableHash.get("Pomme"),("Grany")) && sp.estDansHash(sp.tableHash.get("Pomme"),"Pote"));
	}

	public void testHEXISTSTrue() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "HSET Pomme Grany verte";
		sp.processInput(theInput,2);
		theInput = "HEXISTS Pomme Grany";
		String m = sp.processInput(theInput,2);
		
		assertEquals("HEXISTS clés existantes","(integer) 1",m);
	}
	
	public void testHEXISTSFalse() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "HSET Pomme Grany verte";
		sp.processInput(theInput,2);
		theInput = "HEXISTS Pomme Pote";
		String m = sp.processInput(theInput,2);
		
		assertEquals("HEXISTS clés existantes","(integer) 0",m);
	}
	
	
	

	public void testHKEYS() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "HSET Pomme Grany verte";
		sp.processInput(theInput,2);
		theInput = "HSET Pomme Golden jaune";
		sp.processInput(theInput,2);
		theInput = "HSET Pomme Reinette rouge";
		sp.processInput(theInput,2);
		theInput = "HKEYS Pomme";
		String m = sp.processInput(theInput,2);
		
		
		String resAttendu= "1) Grany\n2) Golden\n3) Reinette\n";
		assertEquals("HKEYS clés existantes",resAttendu,m);
	}

	
	public void testHLEN() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "HSET Pomme Grany verte";
		sp.processInput(theInput,2);
		theInput = "HSET Pomme Golden jaune";
		sp.processInput(theInput,2);
		theInput = "HSET Pomme Reinette rouge";
		sp.processInput(theInput,2);
		theInput = "HLEN Pomme";
		sp.processInput(theInput,2);
		
		assertEquals("HKEYS clés existantes",3,sp.tableHash.get("Pomme").size());
	}
	

	public void testHSTRLEN() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "HSET Pomme Grany verte";
		sp.processInput(theInput,2);
		theInput = "HSTRLEN Pomme Grany";
		sp.processInput(theInput,2);
		
		assertEquals("HSTRLEN clés existantes",5,sp.valueH(sp.tableHash.get("Pomme"),"Grany").length());	
	}

	public void testHVALS() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "HSET Pomme Grany verte";
		sp.processInput(theInput,2);
		theInput = "HSET Pomme Smith jaune";
		sp.processInput(theInput,2);
		theInput = "HSET Pomme Pote rouge";
		sp.processInput(theInput,2);
		theInput = "HVALS Pomme";
		String m = sp.processInput(theInput,2);
		
		String resAttendu = "1) verte\n2) jaune\n3) rouge\n";
		
		assertEquals("HVALS clés existantes",resAttendu,m);	
	
	}

	public void testHINCRBY() {
		ServerProtocol sp = new ServerProtocol();
		String theInput = "HSET Numero Acces 10";
		sp.processInput(theInput,2);
		theInput = "HINCRBY Numero Acces 15";
		sp.processInput(theInput,2);
		
		assertEquals("HINCRBY clés existantes","25",sp.valueH(sp.tableHash.get("Numero"), "Acces"));	
	}

}
