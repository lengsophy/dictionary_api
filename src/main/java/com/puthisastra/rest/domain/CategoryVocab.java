package com.puthisastra.rest.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.puthisastra.rest.config.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "tbl_category_vocab")
@ApiModel(description = "Tags and Vocab Relationship")
public class CategoryVocab extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(value = "ID of the CatVocab", example = "2")
    private long id;
	
	@OneToOne
	@JoinColumn(name="category_id")
	@NotNull
	@ApiModelProperty(value = "Tag ID", required = true, example = "2")
	private Category category;
	
	
	@OneToOne
	@JoinColumn(name="vocab_id")
	@NotNull
	@ApiModelProperty(value = "Vocab ID", required = true, example = "2")
	private Vocab vocab;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	public Vocab getVocab() {
		return vocab;
	}

	public void setVocab(Vocab vocab) {
		this.vocab = vocab;
	}
	
	
}
