package BDD;

import static org.assertj.core.api.Assertions.tuple;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static isen.contactapp.BDD.Connection.getDataSource;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import isen.contactapp.BDD.PersonDao;
import isen.contactapp.entities.Person;

public class TestPersonDao {
	/**
	 * @rtwam
	 * Classe qui gère les tests sur les contacts
	 */
	private PersonDao personDao = new PersonDao();
	
	/**
	 * Initialise la base de données avec des contacts préremplis
	 */
	@Before
	public void initDb() throws Exception {
		Connection connection = getDataSource().getConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate("DELETE FROM person");
		statement.executeUpdate("INSERT INTO person (id,lastname,firstname,nickname,phone,email,address,birthday,category ) VALUES (1, 'Master', 'Yoda', NULL, '0612345678', 'Yoda.Master@isen.yncrea.fr', 'Dagobah', NULL,'Work')");
		statement.executeUpdate("INSERT INTO person (id,lastname,firstname,nickname,phone,email,address,birthday,category ) VALUES (2, 'Skywalker', 'Anakin', 'Darth Vader', '0687654321', 'Anakin.Skywalker@isen.yncrea.fr', 'Tatouine', NULL,'Other')");
		statement.executeUpdate("INSERT INTO person (id,lastname,firstname,nickname,phone,email,address,birthday,category ) VALUES (3, 'Sheev', 'Palpatine', 'Darth Sidious', '0987654321', 'Palpatine.Sheev@isen.yncrea.fr', 'Naboo', '1989-01-13','Work')");
		statement.executeUpdate("INSERT INTO person (id,lastname,firstname,nickname,phone,email,address,birthday,category ) VALUES (4, 'Darth', 'Plagueis', 'The whise', '0412345678', NULL, NULL, '1970-02-21','Family')");
		statement.executeUpdate("INSERT INTO person (id,lastname,firstname,nickname,phone,email,address,birthday,category ) VALUES (5, 'Droide', 'R2D2', 'R2', '0787654321', NULL, NULL, NULL,'Friend')");
		statement.executeUpdate("INSERT INTO person (id,lastname,firstname,nickname,phone,email,address,birthday,category ) VALUES (6, 'Droide', 'C3PO', NULL, '0787654322', NULL, 'Tatouine', NULL,'Friend')");
		statement.close();
		connection.close();
	}	
	

	
	/**
	 * Test si on peut bien lister les personnes présentes dans la bdd
	 */
	@Test
	public void shouldListPerson() throws Exception {
		// WHEN
		List<Person> person = personDao.SelectAllFromPerson();
		// THEN
		assertThat(person).hasSize(6);
		assertThat(person).extracting( "id", "lastname","firstname","nickname","phone","email","address","birthday","category").containsOnly(
				tuple( 1,  "Master", "Yoda", null, "0612345678", "Yoda.Master@isen.yncrea.fr", "Dagobah", null,"Work"),
				tuple( 2,  "Skywalker", "Anakin", "Darth Vader", "0687654321", "Anakin.Skywalker@isen.yncrea.fr", "Tatouine", null,"Other"),
				tuple( 3,  "Sheev", "Palpatine", "Darth Sidious", "0987654321", "Palpatine.Sheev@isen.yncrea.fr", "Naboo", LocalDate.of(1989,Month.JANUARY,13),"Work"),
				tuple( 4,  "Darth", "Plagueis", "The whise", "0412345678", null, null, LocalDate.of(1970,Month.FEBRUARY,21),"Family"),
				tuple( 5,  "Droide", "R2D2", "R2", "0787654321", null, null, null,"Friend"),
				tuple( 6,  "Droide", "C3PO", null, "0787654322", null, "Tatouine", null,"Friend"));
	}

	/**
	 * Regarde si le listage par ID est correct
	 */
	@Test
	 public void shouldListById() {
		// WHEN
		List<Person> person=personDao.getPersonById("6");
		// THEN
		assertThat(person).hasSize(1);
		assertThat(person).extracting( "id", "lastname","firstname","nickname","phone","email","address","birthday","category").containsOnly(
				tuple( 6,  "Droide", "C3PO", null, "0787654322", null, "Tatouine", null,"Friend"));
	}
	
	/**
	 * Requête par catégorie correct ou non
	 */
	@Test
	public void shouldListByCategory() {
		// WHEN
		List<Person> person = personDao.getPersonByCategory("Friend");
		// THEN
		assertThat(person).hasSize(2);
		assertThat(person).extracting( "id", "lastname","firstname","nickname","phone","email","address","birthday","category").containsOnly(
				tuple( 5,  "Droide", "R2D2", "R2", "0787654321", null, null, null,"Friend"),
				tuple( 6,  "Droide", "C3PO", null, "0787654322", null, "Tatouine", null,"Friend"));
	}
	
	/**
	 * Si on lui donne pas d'id, est censé retourner error
	 */
	@Test
	public void shouldListByIdEror()  {
		// WHEN
		List<Person> person = personDao.getPersonById("");
		// THEN
		assertThat(person).hasSize(0);
	}
	
	/**
	 * Devrait bien afficher ce que l'on cherche
	 */
	@Test
	 public void shouldListSearch() {
		// WHEN
		List<Person> person=personDao.SelectAllWhereContain("a");
		// THEN
		assertThat(person).hasSize(5);
		assertThat(person).extracting( "id", "lastname","firstname","nickname","phone","email","address","birthday","category").containsOnly(
				tuple( 1,  "Master", "Yoda", null, "0612345678", "Yoda.Master@isen.yncrea.fr", "Dagobah", null,"Work"),
				tuple( 2,  "Skywalker", "Anakin", "Darth Vader", "0687654321", "Anakin.Skywalker@isen.yncrea.fr", "Tatouine", null,"Other"),
				tuple( 3,  "Sheev", "Palpatine", "Darth Sidious", "0987654321", "Palpatine.Sheev@isen.yncrea.fr", "Naboo", LocalDate.of(1989,Month.JANUARY,13),"Work"),
				tuple( 4,  "Darth", "Plagueis", "The whise", "0412345678", null, null, LocalDate.of(1970,Month.FEBRUARY,21),"Family"),
				tuple( 6,  "Droide", "C3PO", null, "0787654322", null, "Tatouine", null,"Friend"));
	}
	
	/**
	 * Pareil que l'id mais pour le search
	 */
	@Test
	public void shouldListSearchEror()  {
		// WHEN
		List<Person> person = personDao.SelectAllWhereContain("");
		// THEN
		assertThat(person).hasSize(0);
	}
	
	/**
	 * Valider requête d'ajout de personne
	 */
	@Test
	public void shouldAddPerson() throws Exception {
		// WHEN 
		PersonDao personDao=new PersonDao();
		Person personToCreate = new Person ("Binks","Jar jar","0612345677","Missa","Naboo",null,"jarjar@gmail.com","Work");
		// WHEN we call our DAO to
		Person newPerson = personDao.addPerson(personToCreate);
		// THEN
		Connection connection = getDataSource().getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE lastname='Binks' AND firstname='Jar jar'");
		
		assertThat(resultSet.next()).isTrue();
		assertThat(resultSet.getInt("id")).isNotNull();
		assertThat(resultSet.getString("lastname")).isEqualTo("Binks");
		assertThat(resultSet.getString("firstname")).isEqualTo("Jar jar");
		assertThat(resultSet.getString("nickname")).isEqualTo("Missa");
		assertThat(resultSet.getString("phone")).isEqualTo("0612345677");
		assertThat(resultSet.getString("email")).isEqualTo("jarjar@gmail.com");
		assertThat(resultSet.getString("address")).isEqualTo("Naboo");
		assertThat(resultSet.getString("birthday")).isEqualTo(null);
		assertThat(resultSet.getString("category")).isEqualTo("Work");
		assertThat(resultSet.next()).isFalse();
		
		
		assertThat(resultSet.next()).isFalse();
		resultSet.close();
		statement.close();
		connection.close();
		
		assertThat(newPerson).isNotNull(); // We got a person
		assertThat(newPerson.getId()).isNotNull(); // the person has an id
		
		assertThat(newPerson.getLastname()).isEqualTo(personToCreate.getLastname());
		assertThat(newPerson.getFirstname()).isEqualTo(personToCreate.getFirstname());
		assertThat(newPerson.getNickname()).isEqualTo(personToCreate.getNickname());
		assertThat(newPerson.getPhone()).isEqualTo(personToCreate.getPhone());
		assertThat(newPerson.getAddress()).isEqualTo(personToCreate.getAddress());
		assertThat(newPerson.getMail()).isEqualTo(personToCreate.getMail());
	}

	/**
	 * Vérifie si la requête supprime bien la personne
	 */
	@Test
	public void shouldDelectPerson() throws SQLException {
		// WHEN 
				personDao.deletePerson(4);
				
				// THEN
				Connection connection = getDataSource().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE id = 4 ");
				assertThat(resultSet.next()).isFalse();
				resultSet.close();
				statement.close();
				connection.close();
		
	}
	
	/**
	 * Checke si existe déjà, doit retourner true
	 */
	@Test
	public void shouldCheckAlreadyExistTrue() throws SQLException {
		PersonDao personDao=new PersonDao();
		Person personToCreate = new Person ("Master", "Yoda", "0612345678", null , "Dagobah",null,"Yoda.Master@isen.yncrea.fr","Work");
		personDao.CheckAlreadyExist(personToCreate);
		//then
		assertTrue(personDao.CheckAlreadyExist(personToCreate));
	}
	
	/**
	 * Checke si existe déjà, doit retourner false
	 */
	@Test
	public void shouldCheckAlreadyExistFalse() throws SQLException {
		PersonDao personDao=new PersonDao();
		Person personToCreate = new Person ("Binks","Jar jar","0612345677","Missa","Naboo",null,"jarjar@gmail.com","Work");
		personDao.CheckAlreadyExist(personToCreate);
		//then
		assertFalse(personDao.CheckAlreadyExist(personToCreate));
	}
	
	/**
	 * Devrait update la personne
	 */	
	@Test
	public void shouldUpdatePerson() throws Exception {
		PersonDao personDao=new PersonDao();
		Person personToUpdate =personDao.getPersonById("1").get(0);
		personToUpdate.setNickname( "yoyo");
		personDao.UpdatePerson(personToUpdate);
		// THEN
		List<Person> person = personDao.getPersonById("1");
		assertThat(person).hasSize(1);
		assertThat(person).extracting( "id", "lastname","firstname","nickname","phone","email","address","birthday","category").containsOnly(
				tuple( 1,  "Master", "Yoda", "yoyo", "0612345678", "Yoda.Master@isen.yncrea.fr", "Dagobah", null,"Work"));
				
	}
}
