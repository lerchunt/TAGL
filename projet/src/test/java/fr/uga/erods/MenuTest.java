package test.java.fr.uga.erods;


import junit.framework.TestCase;
import main.java.fr.uga.erods.Menu;
import main.java.fr.uga.erods.Transaction;

public class MenuTest extends TestCase {
	
	public void testMenuChangeState() {		
		Menu m = new Menu();
		Transaction t = new Transaction();
		String input = "";
		m.processInput(input, t);
		
		int resAtt = 4; //etat = REPONSE
		int resObt = m.getState();
		
		assertEquals("MENU : changement d'état ",resAtt,resObt);
	}
	
	public void testReponseChangeState1() {		
		Menu m = new Menu();
		Transaction t = new Transaction();
		String input = "";
		m.processInput(input, t);
		input = "1";
		m.processInput(input, t);
		
		int resAtt = 1; //etat = COMMANDES
		int resObt = m.getState();
		
		assertEquals("MENU : changement d'état ",resAtt,resObt);
	}
	
	public void testReponseChangeState2() {		
		Menu m = new Menu();
		Transaction t = new Transaction();
		String input = "";
		m.processInput(input, t);
		input = "2";
		m.processInput(input, t);
		
		int resAtt = 3; //etat = COMMANDES
		int resObt = m.getState();
		
		assertEquals("MENU : changement d'état ",resAtt,resObt);
	}
	
	public void testReponseChangeStateErr() {		
		Menu m = new Menu();
		Transaction t = new Transaction();
		String input = "";
		m.processInput(input, t);
		input = "toto";
		m.processInput(input, t);
		
		int resAtt = 0; //etat = COMMANDES
		int resObt = m.getState();
		
		assertEquals("MENU : changement d'état ",resAtt,resObt);
	}

}
