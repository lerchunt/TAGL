package fr.uga.erods;

public interface ClientItf <T>{
	
	public void connect(Serveur s);
	public void disconnect();
	public void laddKey();
	public void raddKey();
	public void rmKey();
	public T getKey();
	public void setKey();

}
