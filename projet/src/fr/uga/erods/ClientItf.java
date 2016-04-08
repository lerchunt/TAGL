package fr.uga.erods;

public interface ClientItf <T>{
	
	public void connect(Serveur s);
	public void disconnect();
	public void laddKey(T key);
	public void raddKey(T key);
	public void rmKey(T key);
	public T getKey();
	public T getValue();
	public void setValue(T key);
	

}
