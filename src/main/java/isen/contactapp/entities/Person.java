package isen.contactapp.entities;

import static isen.contactapp.BDD.Connection.getDataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * L'objet person
 * @author rtwam
 */
public class Person {
	
    /**
     * id de la personne
     */
    protected int id;               // NOT NULL

    /**
     * nom de famille
     */
    protected String lastname;      // NOT NULL

    /**
     * pr�nom
     */
    protected String firstname;     // NOT NULL

    /**
     * num�ro de t�l�phone
     */
    protected String phone;         // NOT NULL

    /**
     * Surnom
     */
    protected String nickname;      // NULL

    /**
     * Adresse
     */
    protected String address;       // NULL

    /**
     * Date d'anniversaire
     */
    protected LocalDate birthday;  	// NULL

    /**
     * Adresse mail
     */
    protected String email;         // NULL

    /**
     * Cat�gorie
     */
    protected String category;		//NOT NULL

    /**
     * Constructeur de l'objet person
     * @param lastname
     * @param firstname
     * @param phone
     */
    public Person(String lastname,String firstname,String phone) {
    	this.firstname=firstname;
        this.phone=phone;
        this.lastname=lastname;
        this.nickname=null;
        this.address=null;
        this.birthday=null;
        this.email=null;
        this.id=-1;
        this.category="Other";
    }
    
    /**
     * Autre constructeur de person
     * @param id
     * @param lastname
     * @param firstname
     * @param phone
     * @param category
     */
    public Person (int id,String lastname,String firstname,String phone,String category){
    	this.firstname=firstname;
        this.phone=phone;
        this.lastname=lastname;
        this.phone=phone;
        this.id=id;
        this.category=category;
        
    }
    
    /**
     * Autre constructeur de person
     * @param lastname
     * @param firstname
     * @param phone
     * @param nickname
     * @param address
     * @param birthday
     * @param email
     * @param category
     */
    public Person (String lastname,String firstname,String phone,String nickname,String address,LocalDate birthday,String email,String category){
    	this.firstname=firstname;
        this.phone=phone;
        this.lastname=lastname;
        this.phone=phone;
        this.nickname=nickname;
        this.address=address;
        this.birthday=birthday;
        this.email=email;
        this.category=category;
    }

    /**
     * Autre constructeur de person
     * @param id
     * @param lastname
     * @param firstname
     * @param phone
     * @param nickname
     * @param address
     * @param birthday
     * @param email
     * @param category
     */
    public Person (int id,String lastname,String firstname,String phone,String nickname,String address,LocalDate birthday,String email,String category){
    	this.id=id;
    	this.firstname=firstname;
        this.phone=phone;
        this.lastname=lastname;
        this.phone=phone;
        this.nickname=nickname;
        this.address=address;
        this.birthday=birthday;
        this.email=email;
        this.category=category;
    }
    
    /**
     * R�cup�re la cat�gorie de la personne
     * @return
     */
    public String getCategory() {
	   return this.category.toString();
   }

    /**
     * R�cup�re la cat�gorie de la personne
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * R�cup�re l'adresse de la personne
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * R�cup�re la date d'anniversaire de la personne
     * @return
     */
    public LocalDate getBirthdate() {
        return birthday;
    }

    /**
     * R�cup�re le pr�nom de la personne
     * @return
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * R�cup�re le nom de famille de la personne
     * @return
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * R�cup�re l'adresse mail de la personne
     * @return
     */
    public String getMail() {
        return email;
    }

    /**
     * R�cup�re le surnom de la personne
     * @return
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * R�cup�re le t�l�phone de la personne
     * @return 
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Ajoute l'adresse
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Ajoute la date d'anniversaire
     * @param birthday
     */
    public void setBirthdate(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Ajoute le pr�nom
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Ajoute l'ID
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Ajoute le nom de famille
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Ajoute l'adresse mail
     * @param email
     */
    public void setMail(String email) {
        this.email = email;
    }

    /**
     * Ajoute le surnom
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Ajoute le num�ro de t�l�phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * Ajoute la cat�gorie
     * @param category
     */
    public void setCategory(String category) {
    	this.category=category;
    }
}
