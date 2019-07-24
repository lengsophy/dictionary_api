package com.puthisastra.rest.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "A Person in Puthisastra")
public class Person {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(value = "id of the person", example = "2")
    private long id;
	
	@Column(length = 20)
	@NotNull
	@ApiModelProperty(value = "firstname of the person", required = true, example = "John")
	private String firstname;
	
	@Column(length = 20)
	@Length(max = 20)
	@NotNull
	@ApiModelProperty(value = "lastname of the person", required = true, example = "Doe")
	private String lastname;
	
	@ApiModelProperty(value = "age of the person", required = false, example = "76")
	private Integer age;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
}
