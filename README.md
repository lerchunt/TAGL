# TAGL
					=========================
						Projet TAGL
					=========================



Membres du Projet: 
Julie Catania
Thomas Lerchundi


*Fonctionnalités implémentées:
		
		Nous avons implémenté un gestionnaire stockage Clé /valeur . 
		Il fonctionne sous une approche Client/Serveur (utilisation de sockets) et de manière Multi-Threadé. 
		Le sytème de stockage permet aussi la manipulation de structure de donées Complexes.
		Une partie importante des fonctionnalités proposées par Redis ont été implémentée. 
		La liste des fonctionnalités est détaillée ci dessous.



* Liste des fonctionalités similaires à Redis:

		LPUSH <Key> <values> : insertion des values par la droite dans Key
		LPUSHX <Key> <values> : insertion des values par la droite si Key existe
		RPUSH <Key> <values> : insertion des values par la gauche dans Key
		RPUSHX <Key> <values> : insertion des values par la gauche dans Key
		LINSERT <Key> mode value1 value2 : insertion de value2 en fonction du mode dans Key\nmode=BEFORE ou AFTER
		LPOP <Key> : supprime et retourne le 1er élément de la liste dans Key 
		RPOP <Key> : supprime et retourne le dernier élément de la liste dans Key 
		LLEN <Key> : renvoie le nombre d'éléments de la liste dans Key 
		LSET <Key> index value : insertion de value à l'index de la liste dans Key 
		DEL <Key> : supprime Key et renvoie le nombre d'éléments supprimés 
		FLUSHALL : supprime toutes les clés 
		EXISTS <Key> : renvoie 1 si Key existe, 0 sinon 
		GET <Key> : renvoie la ou les valeurs de Key 
		LRANGE <Key> start end : renvoie les valeurs de Key compris entre start et end 
		GETSET <Key> <values> : remplace les valeurs de Key par values et retourne les anciennes 
		SET <Key> <values> : remplace les valeurs de Key par values et créer la clé si elle est inexistante 
		APPEND Key <value> : concatène la valeur à la clé et renvoie la longueur finale 
		DECR <Key> : décrémente la valeur de Key si c'est un entier 
		INCR <Key> : incrémente la valeur de Key si c'est un entier 
		ECHO String : affiche la String 
		COMMAND : affiche les commandes 
		HSET <Key> <Field> <value>: met à jour la valeur de Field, la créer si existe pas 
		HGET <Key> <Field> : renvoie la valeurs de Field dans Key 
		HDEL <Key> <Field> : supprime Field de Key 
		HEXISTS <Key> <Field> : renvoie 1 si Field existe, 0 sinon 
		HKEYS <Key> : renvoie la liste des Fields de Key 
		HLEN <Key> : renvoie le nombre de Field de la Key 
		HSTRLEN <Key> <Field> : renvoie la longueur de la valeur du Field 
		HVALS <Key> : affiche les valeurs de chaque Field dans leur ordre d'insertion 
		HINCRBY <Key> <Field> <int> : incrément la valeur de Field de int 


* Tests Unitaires:

		Les tests unitaires qui ont été effectués couvrent chacune des commandes ci-dessus. L’ensemble des tests est disponible dans les fichiers TransactionTest.java et MenuTest.java


* Utilisation du système :

		Pour compiler 
			-depuis le répertoire TAGL/ : 
			mvn compile
	
		Pour lancer les tests:
			-depuis le répertoire TAGL/ : 
			mvn compile

		
		Pour lancer l’application : 
			-se placer dans le répertoire TAGL/projet/target/classes/

				1) Executer le Serveur : java main.java.fr.uga.erods.Serveur Multi 4444
				2) Executer un Client :  java main.java.fr.uga.erods.Client localhost 4444 
				(On peut executer autant de client que l'on souhaite)


* FeedBack :

	Travis:
		Dans l’ensemble, nous n’avons pas compris immédiatement l’importance d’utiliser un outil d'intégration continue pour notre projet. 
		Cependant, nous nous sommes vite rendus compte de son aspect pratique, qui nous a permis de nous assurer automatiquement de la bonne prise en compte des modifications que nous effectuons au cours du temps, et ce indépendemment de nos machines locales.

	Sur le projet en général : 
		Dans l’ensemble, ce projet nous a permis de nous faire une idée du fonctionnement général d’un projet en entreprise. 
		La prise en main des diverses technologies s’est révélée intéressante, même si de temps en temps,le développement global du système de stockage a pris le dessus sur l’apprentissage des techniques appliquées au développement logiciel.
