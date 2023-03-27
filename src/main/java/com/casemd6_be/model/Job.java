package com.casemd6_be.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String code;
    private Double salaryMin;
    private Double salaryMax;
    private String address;
    private int expYear;
    private Date expiredDate;
    private String description;
    private int quantity;
    private String gender;
    private int status;

    @ManyToOne
    private Location location;

    @ManyToOne
    private Company company;

    @ManyToOne
	private Category category;
}
