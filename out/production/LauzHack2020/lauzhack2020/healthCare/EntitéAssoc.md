**Un patient va dans _1 et 1 seul_ hopital**<br>
<hr>

**Un patient vas dans _1 et 1 seul_ service**<br>
Un patient est identifié par son ID numéro de patient qui est unique, a aussi un firstName, LastName, age, un niveau de facteur de risque
complicationRiskPercent, s'il est a le covid ou non, et une unit qui lui est associé
<hr>

**Un hopital recoit _0_, 1, ou _plusieurs_ patient**<br>
Un hoptial est défini par son nom, sa location, et ses services, son nombre de lit libre total et sa capacité 
<hr>

**Un hopital contient _forcément plusieurs_ services**<br>
<hr>

**Un service appartient à _1 et 1 seul_ hopital et acceuille _0_, _1_ ou plusieurs patients**<br>
Un service est défini par son type, son capacité totale, son nombre de lits disponible, nb de lit utilisé par des covid
et nb de lit utilisés par d'autres malades 
  
  un patient occupe un lit dans un service qui appartient à un hopital, <br>
