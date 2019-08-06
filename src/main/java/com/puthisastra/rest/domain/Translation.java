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

@Entity(name = "tbl_translation")
@ApiModel(description = "A Word in Dictionary")
public class Translation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(value = "id of the Category", example = "2")
    private long id;
	
	@Column(length = 40)
	@ApiModelProperty(value = "Category Primary Key for Each Word ", required = true, example = "HEALTH")
	private String category_key;

	@Column(length = 20)
	@Length(max = 255)
	@NotNull
	@ApiModelProperty(value = "Category name in English", required = false, example = "HEALTH")
	private String category_name_en;
	
	@Column(length = 20)
	@Length(max = 255)
	@NotNull
	@ApiModelProperty(value = "Category name in Khmer", required = false, example = "សុខភាព")
	private String category_name_kh;
	
	@Column(length = 20)
	@Length(max = 255)
	@NotNull
	@ApiModelProperty(value = "Category name in Franch", required = false, example = "Santé")
	private String category_name_fn;
	
	@Column(length = 20)
	@ApiModelProperty(value = "Date Created Category", required = false, example = "10-10-2010")
	private Date created_at;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategory_key() {
		return category_key;
	}

	public void setCategory_key(String category_key) {
		this.category_key = category_key;
	}

	public String getCategory_name_en() {
		return category_name_en;
	}

	public void setCategory_name_en(String category_name_en) {
		this.category_name_en = category_name_en;
	}

	public String getCategory_name_kh() {
		return category_name_kh;
	}

	public void setCategory_name_kh(String category_name_kh) {
		this.category_name_kh = category_name_kh;
	}

	public String getCategory_name_fn() {
		return category_name_fn;
	}

	public void setCategory_name_fn(String category_name_fn) {
		this.category_name_fn = category_name_fn;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	

}
