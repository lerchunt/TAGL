package fr.uga.erods;

import java.util.ArrayList;
import java.util.LinkedList;

public interface ClientItf <T>{
	/*
	public void connect(Serveur s);
	public void disconnect();
	*/
	
	public String LPUSH(String key, LinkedList<T> value); //value est une liste : ajout de value par la gauche et renvoie nombre final d'éléments
	public String RPUSH(String  key, LinkedList<T> value); //value est une liste : ajout de value par la droite
	public String LPUSHX(String key, LinkedList<T> value); //LPUSH que si la liste existe
	public String RPUSHX(String key, LinkedList<T> value); //RPUSH que si la liste existe
	
	public String LINSERT(String key, String mode, T value, T insert); //mode = BEFORE ou AFTER. insert la valeur "insert" avant ou après la valeur "value"
	public String LPOP(String key); //supprime et retourne le 1er élément de la liste de key
	public String RPOP(String key); //supprime et retourne le dernier élément de la liste de key
	public String LLEN(String key); //renvoie le nombre d'élement de la liste
	public String LSET(String key, int index, T value); //met à l'index la value
	public String SREM(String key, LinkedList<String> value); // supprime la ou les valeurs et renvoie le nombre de membre supprimer
	
	
	public String DEL(LinkedList <String> key); //supprime une liste de clé et revoie le nombre d'élément supprimer et "OK" si que 1 élément
	public String FLUSHALL(); //supprime toutes les clés
	public String EXISTS (String key); //renvoie 1 si trouvé 0 sinon	
	
	public T GET(String key); //donne la/les valeurs de la clé
	public String LRANGE(String key, int start, int end); //renvoie les valeurs de start à end de Key
	
	public T GETSET(String key, LinkedList<T> value); //remplace la valeur et retourne l'ancienne
	public String SET(String key, LinkedList<T> value); //modifie la valeur d'une clé et la créer si existe pas renvoie OK si ok
	
	public String APPEND(String key, T value); //ajoute la valeur à la clé (concaténation) et renvoie la longueur finale
	public String DECR(String key); //décrémente la valeur et renvoie la nouvelle valeur, si pas en int --> error !
	public String INCR(String key); //incrémente la valeur et renvoie la nouvelle valeur, si pas en int --> error !
	
	public String ECHO(String message); //renvoie le message
	public String COMMAND(); //retourne la liste des commandes disponibles
	
	
	public String HSET (String key, String field, T value); //Si clé existe pas: créer (renvoie 0), sinon met à jours (renvoie 1).
	public T HGET (String key, String field); //renvoie la valeur de field
	public String HDEL(String key, String field); //renvoie 1 si field a bien été supprimé, 0 si existe pas
	public String HEXISTS(String key, String field); //renvoie 1 si field existe, 0 sinon
	public ArrayList<String> HKEYS(String key); //renvoie liste des fields de la clé
	public String HLEN(String key); //renvoie le nombre de fields de la clé
	public String HSTRLEN(String key, String field); //renvoie la longueur de la valeur du field
	public ArrayList<String> HVALS(String key); //renvoie toutes les valeurs de tous les fields dans l'ordre d'insertion des fields et des valeurs
	public String HINCRBY(String key, String field, int valIncr); //incrémente la valeur du field et renvoie la nouvelle valeur
	
	
		
}
