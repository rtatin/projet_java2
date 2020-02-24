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

public class Person {
	
    protected int id;               // NOT NULL
    protected String lastname;      // NOT NULL
    protected String firstname;     // NOT NULL
    protected String phone;         // NOT NULL
    protected String nickname;      // NULL
    protected String address;       // NULL
    protected LocalDate birthday;  	// NULL
    protected String email;         // NULL
    protected String category;		//NOT NULL

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
    
    public Person (int id,String lastname,String firstname,String phone,String category){
    	this.firstname=firstname;
        this.phone=phone;
        this.lastname=lastname;
        this.phone=phone;
        this.id=id;
        this.category=category;
        
    }
    
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
    
   public String getCategory() {
	   return this.category.toString();
   }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getBirthdate() {
        return birthday;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMail() {
        return email;
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

    public void setBirthdate(LocalDate birthday) {
        this.birthday = birthday;
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

    public void setMail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setCategory(String category) {
    	this.category=category;
    }
   
    


}
