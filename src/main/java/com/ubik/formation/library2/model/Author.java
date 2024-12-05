package com.ubik.formation.library2.model;

import jakarta.persistence.*;

@Entity
@Table(name="author")
public class Author {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "author_sequence_generator"
    )
    @SequenceGenerator(
            name = "author_sequence_generator",
            sequenceName = "author_sequence",
			allocationSize=1
    )
    private Long id;

	@Column(name = "last_name")
    private String lastName;

	@Column(name = "first_name")
    private String firstName;

	public Author() {}

	public Author(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
	}
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) { this.lastName = lastName; }
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getName() {
		return this.firstName + " " + this.lastName;
	}
	
}
