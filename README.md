projet_java2 réalisé par Tatin Raphael, Remael Achille et Vonck Theophile.

Les informations de la BDD se trouvent dans le fichier conf.properties dans lequel se trouvent le driver, l url, le login et le mot de passe.
 Nous avons utilisé la base de donées SQL grace a PHPmyadmin de wampServeur pour emerger la BDD. Celle que nous avons utilisé se trouve dans resources/sql
 
 Nous avons realisé les tests de requetes a la BDD, ils se trouvent dans le dossier test
 
 notre base de don�e est constituée d une seule table person qui est composée des champs suivants: 
 -id non null et en auto incrementation
 -firstname non null
 -lastame non null 
 -phone non null
 -bithdate
 -email
 -nickname
 -category non null, s'il n est pas renseigné, il est mis par defaut a Other
 -adresse / l adresse est une concatenation des AddresseField , PostalCodeField, CityField et ContryField séparés par ";" pour permettre de realiser une fonction split
 