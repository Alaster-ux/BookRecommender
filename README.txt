REQUISITI:
- Java 17+
- Maven 3.9+
- PostgreSQL 17 (installato e in esecuzione)

DATABASE:
- Nome DB: BookRecommender
- Porta: 5432
- Utente: postgres
- Password: root

PER ACCEDERE ALLA CARTELLA:
  cd Documents\delorenzo_758695

PER INIZIALIZZARE IL SERVER:

  psql -U postgres
  inserire la password
  CREATE DATABASE bookrecommender OWNER postgres;
  \q
  psql -U postgres -d BookRecommender -f bin/schema.sql

ESECUZIONE:
	
	Avvia ServerEXE.bat
	Inserire le credenziali.	

	Avvia ClientEXE.bat
