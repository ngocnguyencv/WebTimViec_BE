package com.casemd6_be.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data@AllArgsConstructor
@NoArgsConstructor
public class Notify {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDateTime dateTime;
	private Boolean status;

	@ManyToOne
	private NotifyType notifyType;

	@ManyToOne
	private Account account;

	@ManyToOne
	private Company company;

	@ManyToOne
	private Job job;
}
