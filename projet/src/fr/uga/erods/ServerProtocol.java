package fr.uga.erods;

public class ServerProtocol {
	private static final int MENU = 0;
	private static final int REPONSE = 4;
    private static final int MANIPULATION = 2;
    private static final int COMMANDES = 1;
 
    private int state = MENU;

    public String processInput(String theInput) {
        String theOutput = null;

        if (state == MENU) {
            theOutput = "Que voulez-vous faire ? \n";
            theOutput += "1 - Afficher les commandes disponibles \n";
            theOutput += "2 - Manipuler les données \n";
            
            
            state = REPONSE;
        } else if (state == REPONSE) {
            if (theInput.equalsIgnoreCase("2")) {
            	state = MANIPULATION;            	
            } else if (theInput.equalsIgnoreCase("1")) {
                state = COMMANDES;
            } else {
            	theOutput = "Vous n'avez pas entrée une valeur correcte. \n "
            			+ "Entrez une valeur entre 1 et 2 : ";
            	state=REPONSE;
            }
        } else if (state == MANIPULATION) {
        		//manipulation
        } else if (state == COMMANDES) {
            // Liste commande
        	state = MENU;
        }
        return theOutput;
    }

}
