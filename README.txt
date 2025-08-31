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
  cd Documents\BookRecoomender_FXB2

PER INIZIALIZZARE IL SERVER:

  psql -U postgres
  CREATE DATABASE BookRecommender OWNER postgres;


Per ricrearlo da zero:
  createdb BookRecommender
  psql -U postgres -d BookRecommender -f bin/schema.sql

ESECUZIONE:
	
	Avvia ServerEXE.bat
	Inserire le credenziali.	

	Avvia ClientEXE.bat
