package com.ubik.formation.library2.model;

import jakarta.persistence.*;

@Entity
@Table(name="usr")
public class User {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_sequence_generator"
    )
    @SequenceGenerator(
        name = "user_sequence_generator",
        sequenceName = "usr_sequence",
		allocationSize = 1
    )
    private long id;

    private String username;
    private String login;
    private String password;
    private String email;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
}
