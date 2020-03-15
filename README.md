projet_java2 realiser par Tatin Raphael, Remael Achile et Vonck Theophile.

Les information de la BDD se trouve dans le fichier conf.properties dans lequelle on retrouve le driver, l url, le login et le mots de passe.
 Nous avons utilser la base de donée SQL grace a PHPmyadmin de wampServeur pour emerger la base de donée. La base de donné que nous avons utiliser se trouve dans ressources/sql
 
 Nous avons realiser les teste de requet a la base de donée, il se trouve dans le dossier test
 
 notre base de donée est constituer d une seule table person qui est composer des champs: 
 -id non null est en auto incrementation
 -firstname non null
 -lastame non null 
 -phone non null
 -bithdate
 -email
 -nickname
 -category non null, si il n est pas rensseigner il est mis par defaut a Other
 -addresse / l addresse est une concatenation des AddresseField , PostalCodeField ,CityField et ContryField separer par ";" pour permetre de realiser une fonction split
 