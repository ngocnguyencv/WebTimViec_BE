package com.casemd6_be.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String shortName;
	private String code;
	private int numberOfEmployees;
	private String googleMap;
	private String website;

	@ManyToOne
	private Account account;
}
