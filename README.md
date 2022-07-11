# XMLiVebServisi

Potrebno je pokrenuti dve exist baze i dve fuseki baze uz pomoć sledećih komandi<br>
Takođe je potrebno napraviti kolekciju "metadata" u obe fuseki baze

Potom je potrebno pokrenuti dva backend i frontend servera

exist baze podataka:
  docker run -d -p 8086:8080 -p 8443:8443 --name existGra existdb/existdb

  docker run -d -p 8087:8080 -p 8444:8444 --name existSlu existdb/existdb
  
fuseki baze podataka:
  docker run --init -d -p  3030:3030 -e ADMIN_PASSWORD=pw123 --name fusekiGra stain/jena-fuseki
  
  docker run --init -d -p  3031:3030 -e ADMIN_PASSWORD=pw123 --name fusekiSlu stain/jena-fuseki
