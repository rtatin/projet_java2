package isen.contactapp.BDD;

import static isen.contactapp.BDD.Connection.getDataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import isen.contactapp.entities.Person;

/**
 * Permet d'effectuer des requêtes en fonction de ce que l'on recherche
 * @author rtwam
 */
public class PersonDao {
	
    /**
     * Appel la BDD pour récupérer les personnes
     * @return liste composée de toutes les personnes présentes dans la
     * BDD
     */
    public  List<Person> SelectAllFromPerson(){
        List<Person> listOfPersons=new ArrayList<>();
        try ( Connection connection = getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement() )  {
                try (ResultSet results= statement.executeQuery("SELECT * FROM person ")){
                    while( results.next()) {
                        Person person=new Person(
                                results.getInt( 1 ),
                                results.getString( "lastname" ),
                                results.getString( "firstname" ),
                                results.getString( "phone" ),
                                results.getString( "nickname" ),
                                results.getString( "address" ),
                                (results.getDate("birthday") == null)? null : results.getDate("birthday").toLocalDate(),
                                results.getString( "email" ),
                                results.getString("category"));
                        
                        listOfPersons.add(person);

                    }
                }
            }
        }
        catch (SQLException e){
            //Manage Exeption
        	throw new RuntimeException("Your Database is dumb as a donkey", e);        }
        return listOfPersons;
    }
	
    /**
     * Récupère une liste de personnes en fonction de leur ID
     * @param id
     * @return liste de personnes en fonction de leur ID
     */
    public  List<Person> getPersonById(String id) {
    	List<Person> listOfPersons=new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM person WHERE id =? ")) {
                statement.setString(1, id);
                try (ResultSet results = statement.executeQuery()) {
                	while (results.next()) {
                		Person person=new Person(
                                results.getInt( 1 ),
                                results.getString( "lastname" ),
                                results.getString( "firstname" ),
                                results.getString( "phone" ),
                                results.getString( "nickname" ),
                                results.getString( "address" ),
                                (results.getDate("birthday") == null)? null : results.getDate("birthday").toLocalDate(),
                                results.getString( "email" ),
                                results.getString("category"));
                		listOfPersons.add(person);
                    }
                }
            }
        } catch (SQLException e ){
            //manage Exeption
        	throw new RuntimeException("Your Database is dumb as a donkey", e);        }
        return listOfPersons;
    }
	
    /**
     * Récupère les personnes en fonction de leur catégorie attribuée
     * @param category
     * @return liste composée des personnes en fonction de leur catégorie
     */
    public  List<Person> getPersonByCategory(String category) {
    	List<Person> listOfPersons=new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM person WHERE category =? ")) {
                statement.setString(1, category);
                try (ResultSet results = statement.executeQuery()) {
                	while (results.next()) {
                		Person person=new Person(
                                results.getInt( 1 ),
                                results.getString( "lastname" ),
                                results.getString( "firstname" ),
                                results.getString( "phone" ),
                                results.getString( "nickname" ),
                                results.getString( "address" ),
                                (results.getDate("birthday") == null)? null : results.getDate("birthday").toLocalDate(),
                                results.getString( "email" ),
                                results.getString("category"));
                		listOfPersons.add(person);
                    }
                }
            }
        } catch (SQLException e ){
            //manage Exeption
        	throw new RuntimeException("Your Database is dumb as a donkey", e);        }
        return listOfPersons;
    }
	
    /**
     * Méthode qui ajoute une personne dans la BDD
     * @param person
     * @return
     */
    public Person addPerson(Person person) {
    	if(CheckAlreadyExist(person)!=false) {
    		return null;
    	}
        try(Connection connection=getDataSource().getConnection()){
            String sqlQuery = "insert into person(lastname,firstname,nickname,phone,email,address,birthday,category) VALUES(?,?,?,?,?,?,?,?)";
            try(PreparedStatement statement=connection.prepareStatement(
                    sqlQuery,Statement.RETURN_GENERATED_KEYS)){
            	statement.setString(1, person.getLastname());
            	statement.setString(2, person.getFirstname());
            	statement.setString(3, person.getNickname());
            	statement.setString(4, person.getPhone());
            	statement.setString(5, person.getMail());
				statement.setString(6, person.getAddress());
				statement.setDate(7, person.getBirthdate()!=null? Date.valueOf(person.getBirthdate()) : null);
				statement.setString(8, person.getCategory());
				statement.executeUpdate();
				
				try (ResultSet keys = statement.getGeneratedKeys()) {
					keys.next();
					person.setId(keys.getInt(1));
					return person;
				}
            }
        }catch (SQLException e){
            //Manage Execption
        	throw new RuntimeException("Your Database is dumb as a donkey", e);
        }
    }
    
    /**
     * Permet de rechercher une personne en fonction de search
     * @param search élément qu'il faut chercher dans la BDD
     * @return 
     */
    public  List<Person> SelectAllWhereContain(String search){
    	List<Person> listOfPersons=new ArrayList<>();
    	if(search.isEmpty()) {
    		return listOfPersons;
    	}
    	
        try ( Connection connection = getDataSource().getConnection()) {
        	String sql = "SELECT * FROM person WHERE lastname  LIKE ? OR firstname  LIKE ? OR phone  LIKE ? OR nickname  LIKE ? OR address  LIKE ? OR email  LIKE ? ";
            try (PreparedStatement statement = connection.prepareStatement(sql) )  {
            	for(int i=1;i<7;i++) {
            		statement.setString(i, "%" + search + "%");
            	}
                try (ResultSet results= statement.executeQuery()){
                    while( results.next()) {
                        Person person=new Person(
                                results.getInt( 1 ),
                                results.getString( "lastname" ),
                                results.getString( "firstname" ),
                                results.getString( "phone" ),
                                results.getString( "nickname" ),
                                results.getString( "address" ),
                                (results.getDate("birthday") == null)? null : results.getDate("birthday").toLocalDate(),
                                results.getString( "email" ),
                                results.getString("category"));
                        listOfPersons.add(person);

                    }
                }
            }
        }
        catch (SQLException e){
            //Manage Exeption
        	throw new RuntimeException("Your Database is dumb as a donkey", e);
        	}
        return listOfPersons;
    }
    
    /**
     * Supprime une personne de la BDD
     * @param idPerson id de la personne à supprimer
     */
    public void deletePerson(Integer idPerson) {
		try (Connection connection =  getDataSource().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement("DELETE FROM person WHERE id = ?")) {
				statement.setInt(1, idPerson);
				statement.executeUpdate();
			}
		}
		
		catch (SQLException e) {
			throw new RuntimeException("Your Database is dumb as a donkey", e);
		}			
	}
    
    /**
     * Vérifie si la personne en question existe déjà dans la BDD
     * @param person, personne pour laquelle on cherche son existence dans la BDD
     * @return
     */
    public Boolean CheckAlreadyExist(Person person) {
    	 try ( Connection connection = getDataSource().getConnection()) {
			String sqlQuery = "SELECT * FROM person WHERE lastname = ? AND firstname = ?";
			try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
				statement.setString(1, person.getLastname());
				statement.setString(2, person.getFirstname());
				
				try (ResultSet results = statement.executeQuery()) {
					if (results.next()) {
						return true;
					}
					else {
						return false;
					}
				}
			}
    	}
    	 catch (SQLException e) {
    		 throw new RuntimeException("Your Database is dumb as a donkey", e);
 		}
 	}
    
    /**
     * Met à jour les coordonnées d'une personne
     * @param person
     */
    public void UpdatePerson(Person person) {
		 try ( Connection connection = getDataSource().getConnection()) {
			String sqlQuery = "UPDATE person SET lastname=?, firstname=?, nickname=?, phone=?, email=?, address=?, birthday=?, category=? WHERE id=?";
			try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
				statement.setString(1, person.getLastname());
				statement.setString(2, person.getFirstname());
				statement.setString(3, person.getNickname());
				statement.setString(4, person.getPhone());
				statement.setString(5, person.getMail());
				statement.setString(6, person.getAddress());
				statement.setDate(7, person.getBirthdate()!=null? Date.valueOf(person.getBirthdate()) : null);
				statement.setString(8, person.getCategory());
				statement.setInt(9,  person.getId());
				statement.executeUpdate();
				System.out.println(person.getId());
			}
		}
		catch (SQLException e) {
			 throw new RuntimeException("Your Database is dumb as a donkey", e);
		}
		
	}
    
}
