package entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static BDD.Connection.getDataSource;

public class Person {
    protected int id;               // NOT NULL
    protected String lastname;      // NOT NULL
    protected String firstname;     // NOT NULL
    protected String phone;         // NOT NULL
    protected String nickname;      // NULL
    protected String address;       // NULL
    protected Date birthdate;  // NULL
    protected String mail;          // NULL


    public Person (String lastname,String firstname,String phone,String nickname,String address,Date birthdate,String mail){
        this.firstname=firstname;
        this.phone=phone;
        this.lastname=lastname;
        this.phone=phone;
        this.nickname=nickname;
        this.address=address;
        this.birthdate=birthdate;
        this.mail=mail;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMail() {
        return mail;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static List<Person> SelectAllFromPerson() throws Exception{
        List<Person> listOfPersons=new ArrayList<>();
        try ( Connection connection = getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement() )  {
                try (ResultSet results= statement.executeQuery("SELECT * FROM person")){
                    while( results.next()) {
                        Person person=new Person(
                                //results.getInt( 1 ),
                                results.getString( "lastname" ),
                                results.getString( "firstname" ),
                                results.getString( "phone" ),
                                results.getString( "nickname" ),
                                results.getString( "address" ),
                                results.getDate( "birthday" ),
                                results.getString( "email" ));
                        listOfPersons.add(person);

                    }
                }
            }
        }
        catch (SQLException e){
            //Manage Exeption
            e.printStackTrace();
        }
        return listOfPersons;
    }

    public static Person getPersonByLastname(String lastname) {
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM person WHERE lastname =? ")) {
                statement.setString(1, lastname);
                try (ResultSet results = statement.executeQuery()) {
                    if (results.next()) {
                        return new Person(
                                //results.getInt( 1 ),
                                results.getString( "lastname" ),
                                results.getString( "firstname" ),
                                results.getString( "phone" ),
                                results.getString( "nickname" ),
                                results.getString( "address" ),
                                results.getDate( "birthday" ),
                                results.getString( "email" ));
                    }
                }
            }
        } catch (SQLException e ){
            //manage Exeption
            e.printStackTrace();
        }
        return null;
    }

    public static Person getPersonByFirstname(String name) {
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM person WHERE firstname =? ")) {
                statement.setString(1, name);
                try (ResultSet results = statement.executeQuery()) {
                    if (results.next()) {
                        return new Person(
                                //results.getInt( 1 ),
                                results.getString( "lastname" ),
                                results.getString( "firstname" ),
                                results.getString( "phone" ),
                                results.getString( "nickname" ),
                                results.getString( "address" ),
                                results.getDate( "birthday" ),
                                results.getString( "email" ));
                    }
                }
            }
        } catch (SQLException e ){
            //manage Exeption
            e.printStackTrace();
        }
        return null;
    }

    public static Person getPersonByNumber(String number) {
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM person WHERE phone =? ")) {
                statement.setString(1, number);
                try (ResultSet results = statement.executeQuery()) {
                    if (results.next()) {
                        return new Person(
                                //results.getInt( 1 ),
                                results.getString( "lastname" ),
                                results.getString( "firstname" ),
                                results.getString( "phone" ),
                                results.getString( "nickname" ),
                                results.getString( "address" ),
                                results.getDate( "birthday" ),
                                results.getString( "email" ));
                    }
                }
            }
        } catch (SQLException e ){
            //manage Exeption
            e.printStackTrace();
        }
        return null;
    }

    public static Person getPersonByNickname(String nickname) {
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM person WHERE nickname =? ")) {
                statement.setString(1, nickname);
                try (ResultSet results = statement.executeQuery()) {
                    if (results.next()) {
                        return new Person(
                                //results.getInt( 1 ),
                                results.getString( "lastname" ),
                                results.getString( "firstname" ),
                                results.getString( "phone" ),
                                results.getString( "nickname" ),
                                results.getString( "address" ),
                                results.getDate( "birthday" ),
                                results.getString( "email" ));
                    }
                }
            }
        } catch (SQLException e ){
            //manage Exeption
            e.printStackTrace();
        }
        return null;
    }

    public static Person getPersonByEmail(String email) {
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM person WHERE email =? ")) {
                statement.setString(1, email);
                try (ResultSet results = statement.executeQuery()) {
                    if (results.next()) {
                        return new Person(
                                //results.getInt( 1 ),
                                results.getString( "lastname" ),
                                results.getString( "firstname" ),
                                results.getString( "phone" ),
                                results.getString( "nickname" ),
                                results.getString( "address" ),
                                results.getDate( "birthday" ),
                                results.getString( "email" ));
                    }
                }
            }
        } catch (SQLException e ){
            //manage Exeption
            e.printStackTrace();
        }
        return null;
    }

    public static Person getPersonByAddress(String address) {
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM person WHERE address =? ")) {
                statement.setString(1, address);
                try (ResultSet results = statement.executeQuery()) {
                    if (results.next()) {
                        return new Person(
                                //results.getInt( 1 ),
                                results.getString( "lastname" ),
                                results.getString( "firstname" ),
                                results.getString( "phone" ),
                                results.getString( "nickname" ),
                                results.getString( "address" ),
                                results.getDate( "birthday" ),
                                results.getString( "email" ));
                    }
                }
            }
        } catch (SQLException e ){
            //manage Exeption
            e.printStackTrace();
        }
        return null;
    }

    public void addPerson(String name) {
        try(Connection connection=getDataSource().getConnection()){
            String sqlQuery = "insert into genre(name)"+"VALUES(?)";
            try(PreparedStatement statement=connection.prepareStatement(
                    sqlQuery,Statement.RETURN_GENERATED_KEYS)){
                statement.setString(1,name);
                statement.executeUpdate();

                ResultSet ids =statement.getGeneratedKeys();
                if(ids.next()){
                    return ;
                }
            }
        }catch (SQLException e){
            //Manage Execption
            e.printStackTrace();
        }
        return ;
    }

    public static void main(String[] args) throws Exception {
        List<Person> listOfPersons=new ArrayList<Person>();
        Person person;
        listOfPersons=Person.SelectAllFromPerson();
        for (Person var : listOfPersons)
        {
            System.out.println(var.lastname+ " " +var.firstname+ " ");
        }


        System.out.println("\n"+"\n");
        person=Person.getPersonByLastname("Darth");
        System.out.println(person.lastname+ " " +person.firstname+ " ");

        person=Person.getPersonByFirstname("Anakin");
        System.out.println(person.lastname+ " " +person.firstname+ " ");

        person=Person.getPersonByAddress("Naboo");
        System.out.println(person.lastname+ " " +person.firstname+ " ");

        person=Person.getPersonByEmail("Yoda.Master@isen.yncrea.fr");
        System.out.println(person.lastname+ " " +person.firstname+ " ");

        person=Person.getPersonByNickname("The whise");
        System.out.println(person.lastname+ " " +person.firstname+ " ");

        person=Person.getPersonByNumber("0787654321");
        System.out.println(person.lastname+ " " +person.firstname+ " ");
        //List<Person> persons = person.SelectFromData();
    }




}
