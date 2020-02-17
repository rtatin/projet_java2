package BDD;

import static org.assertj.core.api.Assertions.tuple;
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
	
	private PersonDao personDao = new PersonDao();
	
	
	@Before
	public void initDb() throws Exception {
		Connection connection = getDataSource().getConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate("DELETE FROM person");
		statement.executeUpdate("INSERT INTO person (id,lastname,firstname,nickname,phone,email,address,birthday ) VALUES (1, 'Master', 'Yoda', NULL, '0612345678', 'Yoda.Master@isen.yncrea.fr', 'Dagobah', NULL)");
		statement.executeUpdate("INSERT INTO person (id,lastname,firstname,nickname,phone,email,address,birthday ) VALUES (2, 'Skywalker', 'Anakin', 'Darth Vader', '0687654321', 'Anakin.Skywalker@isen.yncrea.fr', 'Tatouine', NULL)");
		statement.executeUpdate("INSERT INTO person (id,lastname,firstname,nickname,phone,email,address,birthday ) VALUES (3, 'Sheev', 'Palpatine', 'Darth Sidious', '0987654321', 'Palpatine.Sheev@isen.yncrea.fr', 'Naboo', '1989-01-13')");
		statement.executeUpdate("INSERT INTO person (id,lastname,firstname,nickname,phone,email,address,birthday ) VALUES (4, 'Darth', 'Plagueis', 'The whise', '0412345678', NULL, NULL, '1970-02-21')");
		statement.executeUpdate("INSERT INTO person (id,lastname,firstname,nickname,phone,email,address,birthday ) VALUES (5, 'Droide', 'R2D2', 'R2', '0787654321', NULL, NULL, NULL)");
		statement.executeUpdate("INSERT INTO person (id,lastname,firstname,nickname,phone,email,address,birthday ) VALUES (6, 'Droide', 'C3PO', NULL, '0787654322', NULL, 'Tatouine', NULL)");
		statement.close();
		connection.close();
	}	
	
	
	@Test
	public void shouldListGenres() throws Exception {
		// WHEN
		List<Person> person = personDao.SelectAllFromPerson();
		// THEN
		assertThat(person).hasSize(6);
		assertThat(person).extracting( "id", "lastname","firstname","nickname","phone","email","address","birthday").containsOnly(
				tuple( 1,  "Master", "Yoda", null, "0612345678", "Yoda.Master@isen.yncrea.fr", "Dagobah", null),
				tuple( 2,  "Skywalker", "Anakin", "Darth Vader", "0687654321", "Anakin.Skywalker@isen.yncrea.fr", "Tatouine", null),
				tuple( 3,  "Sheev", "Palpatine", "Darth Sidious", "0987654321", "Palpatine.Sheev@isen.yncrea.fr", "Naboo", LocalDate.of(1989,Month.JANUARY,13)),
				tuple( 4,  "Darth", "Plagueis", "The whise", "0412345678", null, null, LocalDate.of(1970,Month.FEBRUARY,21)),
				tuple( 5,  "Droide", "R2D2", "R2", "0787654321", null, null, null),
				tuple( 6,  "Droide", "C3PO", null, "0787654322", null, "Tatouine", null));
	}

	@Test
	 public void shouldListById() {
		// WHEN
		List<Person> person=personDao.getPersonById("6");
		// THEN
		assertThat(person).hasSize(1);
		assertThat(person).extracting( "id", "lastname","firstname","nickname","phone","email","address","birthday").containsOnly(
				tuple( 6,  "Droide", "C3PO", null, "0787654322", null, "Tatouine", null));
	}
	
	@Test
	public void shouldListByIdEror()  {
		// WHEN
		List<Person> person = personDao.getPersonById("");
		// THEN
		assertThat(person).hasSize(0);
	}
	
	@Test
	 public void shouldListByLastname() {
		// WHEN
		List<Person> person=personDao.getPersonByLastname("Droide");
		// THEN
		assertThat(person).hasSize(2);
		assertThat(person).extracting( "id", "lastname","firstname","nickname","phone","email","address","birthday").containsOnly(
				tuple( 5,  "Droide", "R2D2", "R2", "0787654321", null, null, null),
				tuple( 6,  "Droide", "C3PO", null, "0787654322", null, "Tatouine", null));
	}
	
	@Test
	public void shouldListByLastnameEror()  {
		// WHEN
		List<Person> person = personDao.getPersonByLastname("");
		// THEN
		assertThat(person).hasSize(0);
	}
	
	@Test
	 public void shouldListByFirstname() {
		// WHEN
		List<Person> person=personDao.getPersonByFirstname("Yoda");
		// THEN
		assertThat(person).hasSize(1);
		assertThat(person).extracting( "id", "lastname","firstname","nickname","phone","email","address","birthday").containsOnly(
				tuple( 1,  "Master", "Yoda", null, "0612345678", "Yoda.Master@isen.yncrea.fr", "Dagobah", null));
	}
	
	@Test
	public void shouldListByFirstnameEror()  {
		// WHEN
		List<Person> person = personDao.getPersonByFirstname("");
		// THEN
		assertThat(person).hasSize(0);
	}
	
	@Test
	 public void shouldListByNickname() {
		// WHEN
		List<Person> person=personDao.getPersonByNickname("Darth Vader");
		// THEN
		assertThat(person).hasSize(1);
		assertThat(person).extracting( "id", "lastname","firstname","nickname","phone","email","address","birthday").containsOnly(
				tuple( 2,  "Skywalker", "Anakin", "Darth Vader", "0687654321", "Anakin.Skywalker@isen.yncrea.fr", "Tatouine", null));
	}
	
	@Test
	public void shouldListByNicknameEror()  {
		// WHEN
		List<Person> person = personDao.getPersonByNickname("");
		// THEN
		assertThat(person).hasSize(0);
	}
	
	@Test
	 public void shouldListByPhone() {
		// WHEN
		List<Person> person=personDao.getPersonByNumber("0612345678");
		// THEN
		assertThat(person).hasSize(1);
		assertThat(person).extracting( "id", "lastname","firstname","nickname","phone","email","address","birthday").containsOnly(
				tuple( 1,  "Master", "Yoda", null, "0612345678", "Yoda.Master@isen.yncrea.fr", "Dagobah", null));
	}
	
	@Test
	public void shouldListByPhoneEror()  {
		// WHEN
		List<Person> person = personDao.getPersonByNumber("");
		// THEN
		assertThat(person).hasSize(0);
	}
	
	@Test
	 public void shouldListSearch() {
		// WHEN
		List<Person> person=personDao.SelectAllWhereContain("a");
		// THEN
		assertThat(person).hasSize(5);
		assertThat(person).extracting( "id", "lastname","firstname","nickname","phone","email","address","birthday").containsOnly(
				tuple( 1,  "Master", "Yoda", null, "0612345678", "Yoda.Master@isen.yncrea.fr", "Dagobah", null),
				tuple( 2,  "Skywalker", "Anakin", "Darth Vader", "0687654321", "Anakin.Skywalker@isen.yncrea.fr", "Tatouine", null),
				tuple( 3,  "Sheev", "Palpatine", "Darth Sidious", "0987654321", "Palpatine.Sheev@isen.yncrea.fr", "Naboo", LocalDate.of(1989,Month.JANUARY,13)),
				tuple( 4,  "Darth", "Plagueis", "The whise", "0412345678", null, null, LocalDate.of(1970,Month.FEBRUARY,21)),
				tuple( 6,  "Droide", "C3PO", null, "0787654322", null, "Tatouine", null));
	}
	
	@Test
	public void shouldListSearchEror()  {
		// WHEN
		List<Person> person = personDao.SelectAllWhereContain("");
		// THEN
		assertThat(person).hasSize(0);
	}
	
	@Test
	public void shouldAddPerson() throws Exception {
		// WHEN 
		PersonDao personDao=new PersonDao();
		Person personToCreate = new Person ("Binks","Jar jar","0612345677","Missa","Naboo",null,"jarjar@gmail.com");
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
}
