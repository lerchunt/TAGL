package fr.uga.erods;

public class ServerProtocol<T> implements ClientItf<T> {
	
    public String processInput(String theInput) {
        String theOutput = null;
        
        
        
        /*
        

        if (state == WAITING) {
            theOutput = "Knock! Knock!";
            state = SENTKNOCKKNOCK;
        } else if (state == SENTKNOCKKNOCK) {
            if (theInput.equalsIgnoreCase("Who's there?")) {
                theOutput = clues[currentJoke];
                state = SENTCLUE;
            } else {
                theOutput = "You're supposed to say \"Who's there?\"! " +
			    "Try again. Knock! Knock!";
            }
        } else if (state == SENTCLUE) {
            if (theInput.equalsIgnoreCase(clues[currentJoke] + " who?")) {
                theOutput = answers[currentJoke] + " Want another? (y/n)";
                state = ANOTHER;
            } else {
                theOutput = "You're supposed to say \"" + 
			    clues[currentJoke] + 
			    " who?\"" + 
			    "! Try again. Knock! Knock!";
                state = SENTKNOCKKNOCK;
            }
        } else if (state == ANOTHER) {
            if (theInput.equalsIgnoreCase("y")) {
                theOutput = "Knock! Knock!";
                if (currentJoke == (NUMJOKES - 1))
                    currentJoke = 0;
                else
                    currentJoke++;
                state = SENTKNOCKKNOCK;
            } else {
                theOutput = "Bye.";
                state = WAITING;
            }
        }
        */
        
        
        return theOutput;
    }

	@Override
	public void connect(Serveur s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void laddKey(T key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void raddKey(T key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rmKey(T key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(T key) {
		// TODO Auto-generated method stub
		
	}

}
