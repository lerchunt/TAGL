package main.java.fr.uga.erods;

public class Menu {
	private static final int MENU = 0;
	private static final int REPONSE = 4;
	private static final int ATTENTE = 3;
	private static final int MANIPULATION = 2;
	private static final int COMMANDES = 1;

	private int state=MENU;
	
	public String processInput(String theInput, Transaction t) {
		String theOutput = " ";


		if (state == MENU) {
			theOutput = "Que voulez-vous faire ?  "
					+ "1 - Afficher les commandes disponibles  "
					+ "2 - Manipuler les données";
			setState(REPONSE);			
		} else if (this.state == REPONSE) {
			if (theInput.equals("2")) {
				theOutput = ":/";
				setState(ATTENTE);            	
			} else if (theInput.equalsIgnoreCase("1")) {
				setState(COMMANDES);
			} else {
				theOutput = "Vous n'avez pas entrée une valeur correcte. "
						+ "Entrez une valeur entre 1 et 2 ";
				setState(MENU);
			}
		} else if (state == MANIPULATION) {
			theOutput = t.transact(theInput);			
			setState(ATTENTE);
		} else if (state == COMMANDES) {
			theOutput = t.COMMAND();
			setState(MENU);
		} else if(state == ATTENTE){
			theOutput = ":/";
			setState(MANIPULATION);
		}
		
		return theOutput;		
	}
	
	
	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}
}
