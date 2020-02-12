package BDD;

import static BDD.Connection.getDataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Person;

public class PersonDao {
	
	public  List<Person> SelectAllFromPerson() throws Exception{
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
                                results.getString( "email" ));
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
                                results.getString( "email" ));
                		listOfPersons.add(person);
                    }
                }
            }
        } catch (SQLException e ){
            //manage Exeption
        	throw new RuntimeException("Your Database is dumb as a donkey", e);        }
        return listOfPersons;
    }
	
    public  List<Person> getPersonByLastname(String lastname) {
    	List<Person> listOfPersons=new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM person WHERE lastname =? ")) {
                statement.setString(1, lastname);
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
                                results.getString( "email" ));
                		listOfPersons.add(person);
                    }
                }
            }
        } catch (SQLException e ){
            //manage Exeption
        	throw new RuntimeException("Your Database is dumb as a donkey", e);        }
        return listOfPersons;
    }

    public  List<Person> getPersonByFirstname(String name) {
    	List<Person> listOfPersons=new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM person WHERE firstname =? ")) {
                statement.setString(1, name);
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
                                results.getString( "email" ));
                        listOfPersons.add(person);
                    }
                }
            }
        } catch (SQLException e ){
            //manage Exeption
        	throw new RuntimeException("Your Database is dumb as a donkey", e);        }
        return listOfPersons;
    }

    public List<Person> getPersonByNumber(String number) {
    	List<Person> listOfPersons=new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM person WHERE phone =? ")) {
                statement.setString(1, number);
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
                                results.getString( "email" ));
                        listOfPersons.add(person);
                    }
                }
            }
        } catch (SQLException e ){
            //manage Exeption
        	throw new RuntimeException("Your Database is dumb as a donkey", e);        }
        return listOfPersons;
    }

    public  List<Person> getPersonByNickname(String nickname) {
    	List<Person> listOfPersons=new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM person WHERE nickname =? ")) {
                statement.setString(1, nickname);
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
                                results.getString( "email" ));
                        listOfPersons.add(person);
                    }
                }
            }
        } catch (SQLException e ){
            //manage Exeption
        	throw new RuntimeException("Your Database is dumb as a donkey", e);        }
        return listOfPersons;
    }

    public List<Person> getPersonByEmail(String email) {
    	List<Person> listOfPersons=new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM person WHERE email =? ")) {
                statement.setString(1, email);
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
                                results.getString( "email" ));
                        listOfPersons.add(person);
                    }
                }
            }
        } catch (SQLException e ){
            //manage Exeption
        	throw new RuntimeException("Your Database is dumb as a donkey", e);        }
        return listOfPersons;
    }

    public List<Person> getPersonByAddress(String address) {
    	List<Person> listOfPersons=new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM person WHERE address =? ")) {
                statement.setString(1, address);
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
                                results.getString( "email" ));
                        listOfPersons.add(person);
                    }
                }
            }
        } catch (SQLException e ){
            //manage Exeption
            throw new RuntimeException("Your Database is dumb as a donkey", e);
        }
        return listOfPersons;
    }

    public Person addPerson(Person person) {
        try(Connection connection=getDataSource().getConnection()){
            String sqlQuery = "insert into person(lastname,firstname,nickname,phone,email,address,birthday) VALUES(?,?,?,?,?,?,?)";
            try(PreparedStatement statement=connection.prepareStatement(
                    sqlQuery,Statement.RETURN_GENERATED_KEYS)){
            	statement.setString(1, person.getLastname());
            	statement.setString(2, person.getFirstname());
            	statement.setString(3, person.getNickname());
            	statement.setString(4, person.getPhone());
            	statement.setString(5, person.getMail());
				statement.setString(6, person.getAddress());
				statement.setDate(7, person.getBirthdate()!=null? Date.valueOf(person.getBirthdate()) : null);
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
    
    public  List<Person> SelectAllWhereContain(String search){
    	List<Person> listOfPersons=new ArrayList<>();
    	if(search.isEmpty()) {
    		return listOfPersons;
    	}
    	
        try ( Connection connection = getDataSource().getConnection()) {
        	String sql = "SELECT * FROM person WHERE lastname  LIKE ?";
            try (PreparedStatement statement = connection.prepareStatement(sql) )  {
            	statement.setString(1, "%" + search + "%");
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
                                results.getString( "email" ));
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
}
