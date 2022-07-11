# XMLiVebServisi

Potrebno je pokrenuti dve exist baze i dve fuseki baze uz pomoć sledećih komandi<br>
Takođe je potrebno napraviti kolekciju "metadata" u obe fuseki baze<br>

Potom je potrebno pokrenuti dva backend i frontend servera<br>

exist baze podataka:<br>
  docker run -d -p 8086:8080 -p 8443:8443 --name existGra existdb/existdb<br>

  docker run -d -p 8087:8080 -p 8444:8444 --name existSlu existdb/existdb<br>
  
fuseki baze podataka:<br>
  docker run --init -d -p  3030:3030 -e ADMIN_PASSWORD=pw123 --name fusekiGra stain/jena-fuseki<br>
  
  docker run --init -d -p  3031:3030 -e ADMIN_PASSWORD=pw123 --name fusekiSlu stain/jena-fuseki<br>
