package com.personalinformation.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="Employees")
@EntityListeners(AuditingEntityListener.class)
public class PersonalInformation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@NotBlank
	String name;
	
	@NotBlank
	String lastName;
	
	@NotBlank
	String address;

	int phoneNum;
	
	@NotBlank
	String hairColor;
}
