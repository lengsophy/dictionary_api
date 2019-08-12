package com.puthisastra.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "tbl_word")
@ApiModel(description = "A Word in Dictionary")
public class Word {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(value = "id of the Word", example = "2")
    private long id;
	
	
	@OneToOne
	@JoinColumn(name="category_id")
	@ApiModelProperty(value = "Category ID key Parent for Each Word ", required = true, example = "HEALTH")
	private Category category;
	
	@Column(length = 20)
	@NotNull
	@ApiModelProperty(value = "Word key for eacher translation ", required = true, example = "BOOK")
	private String word_key;
	
	@Column(length = 20)
	@Length(max = 255)
	@NotNull
	@ApiModelProperty(value = "Word in English", required = true, example = "Book")
	private String translate_en;
	
	@Column(length = 20)
	@Length(max = 255)
	@NotNull
	@ApiModelProperty(value = "Word in Khmer", required = true, example = "សៀវភៅ")
	private String translate_kh;
	
	@Column(length = 20)
	@Length(max = 255)
	@NotNull
	@ApiModelProperty(value = "Word in Franch", required = true, example = "Book")
	private String translate_fn;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWord_key() {
		return word_key;
	}

	public void setWord_key(String word_key) {
		this.word_key = word_key;
	}

	public String getTranslate_en() {
		return translate_en;
	}

	public void setTranslate_en(String translate_en) {
		this.translate_en = translate_en;
	}

	public String getTranslate_kh() {
		return translate_kh;
	}

	public void setTranslate_kh(String translate_kh) {
		this.translate_kh = translate_kh;
	}

	public String getTranslate_fn() {
		return translate_fn;
	}

	public void setTranslate_fn(String translate_fn) {
		this.translate_fn = translate_fn;
	}
	

}
