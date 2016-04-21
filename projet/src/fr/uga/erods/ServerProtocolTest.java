package fr.uga.erods;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;

import junit.framework.TestCase;

public class ServerProtocolTest extends TestCase {

	public void testProcessInput() {
		fail("Not yet implemented");
	}

	public void testGetTable() {
		fail("Not yet implemented");
	}

	public void testSetTable() {
		fail("Not yet implemented");
	}




	public void testLPUSHCreation() {		
		//Insertion d'un élément dans une nouvelle clé
		ServerProtocol sp = new ServerProtocol();
		String theInput = "LPUSH friends Thomas";
		String m = sp.processInput(theInput,2); //2 pour accéder directement à l'état MANIPULATION
		assertEquals("élément présent dans la table de donnée : ","Thomas",sp.table.get("friends").get(0) );
	}

	public void testLPUSHElement() {		
		//Insertion d'un élément à gauche dans une clé existante
		ServerProtocol sp = new ServerProtocol();
		String theInput = "LPUSH friends Thomas";
		sp.processInput(theInput,2); 
		theInput = "LPUSH friends Julie";
		sp.processInput(theInput,2);

		String resultatObtenu = sp.table.get("friends").get(0) + " " + sp.table.get("friends").get(1);
		assertEquals("insertion élément par LPUSH :", "Julie Thomas",resultatObtenu);
	}

	public void testLPUSHElements() {		
		//Insertion d'une liste d'éléments à gauche
		ServerProtocol sp = new ServerProtocol();
		String theInput = "LPUSH friends Thomas Julie";
		String m = sp.processInput(theInput,2); //2 pour accéder directement à l'état MANIPULATION
		assertEquals("insertion élément par LPUSH :", "(integer) 2",m);
	}


	public void testRPUSH() {
		//Insertion d'un élément à droite dans une clé existante
		ServerProtocol sp = new ServerProtocol();
		String theInput = "RPUSH friends Thomas";
		sp.processInput(theInput,2); 
		theInput = "RPUSH friends Julie";
		sp.processInput(theInput,2);

		String resultatObtenu = sp.table.get("friends").get(0) + " " + sp.table.get("friends").get(1);
		assertEquals("insertion élément par LPUSH :", "Thomas Julie",resultatObtenu);
	}

	public void testLPUSHX() {
		fail("Not yet implemented");
	}

	public void testRPUSHX() {
		fail("Not yet implemented");
	}

	public void testLINSERT() {
		fail("Not yet implemented");
	}

	public void testLPOP() {
		fail("Not yet implemented");
	}

	public void testRPOP() {
		fail("Not yet implemented");
	}

	public void testLLEN() {
		fail("Not yet implemented");
	}

	public void testLSET() {
		fail("Not yet implemented");
	}

	public void testSREM() {
		fail("Not yet implemented");
	}

	public void testDEL() {
		fail("Not yet implemented");
	}

	public void testFLUSHALL() {
		fail("Not yet implemented");
	}

	public void testEXISTS() {
		fail("Not yet implemented");
	}

	public void testGET() {
		fail("Not yet implemented");
	}

	public void testGETRANGE() {
		fail("Not yet implemented");
	}

	public void testGETSET() {
		fail("Not yet implemented");
	}

	public void testSET() {
		fail("Not yet implemented");
	}

	public void testAPPEND() {
		fail("Not yet implemented");
	}

	public void testDECR() {
		fail("Not yet implemented");
	}

	public void testINCR() {
		fail("Not yet implemented");
	}

	public void testECHO() {
		fail("Not yet implemented");
	}

	public void testCOMMAND() {
		fail("Not yet implemented");
	}

	public void testHSET() {
		fail("Not yet implemented");
	}

	public void testHGET() {
		fail("Not yet implemented");
	}

	public void testHDEL() {
		fail("Not yet implemented");
	}

	public void testHEXISTS() {
		fail("Not yet implemented");
	}

	public void testHKEYS() {
		fail("Not yet implemented");
	}

	public void testHLEN() {
		fail("Not yet implemented");
	}

	public void testHSTRLEN() {
		fail("Not yet implemented");
	}

	public void testHVALS() {
		fail("Not yet implemented");
	}

	public void testHINCRBY() {
		fail("Not yet implemented");
	}

}
