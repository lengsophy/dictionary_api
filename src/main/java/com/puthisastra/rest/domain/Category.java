package com.puthisastra.rest.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.puthisastra.rest.config.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "tbl_category")
@ApiModel(description = "Category of A Dictionary")
public class Category extends BaseEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(value = "id of the Category", example = "2")
    private long id;
	
	@ManyToMany
	private List<Vocab> vocab;
	
	
	@Column(length = 40)
	@ApiModelProperty(value = "Category Primary Key for Each Category ", required = true, example = "HEALTH")
	private String category_en;
    
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getCategory_en() {
		return category_en;
	}

	public void setCategory_en(String category_en) {
		this.category_en = category_en;
	}
}
