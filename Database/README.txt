#Progetto Basi Di Dati Di Giovanni Miccio

#Linee Guida
I file .SQL sono stati divisi secondo categorie e sono tutti presenti nelle varie cartelle.

Per facilità nella creazione e nella popolazione del db è stato fornito un file .backup che, una volta inserito, si preoccuperà 
della creazione dello schema, popolando le varie tabelle.

Per utilizzare cantiere_db.backup da pgAdmin 4:
1. Creare un nuovo database.
2. Click con il tasto destro del mouse sul db appena creato e nel menù a tendina scegliere la voce "Restore".
3. Formato del file: "Custom or Tar"
4. Nome file: recuperare il file cantiere_db.backup
5. Numero operazioni: vuoto
6. Nome del ruolo: scegliere un ruolo.
7. Cliccare su ripristina.

