package com.ubik.formation.library2.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tag")
public class Tag {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "tag_sequence_generator"
    )
    @SequenceGenerator(
        name = "tag_sequence_generator",
        sequenceName = "tag_sequence",
		allocationSize = 1
    )
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Book> books = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
