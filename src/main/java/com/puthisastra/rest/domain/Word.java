package com.puthisastra.rest.domain;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@NotNull
	@ApiModelProperty(value = "Category ID key Parent for Each Word", required = true, example = "2")
	private Category category;
	
//	@ManyToOne
//	@JoinColumn(name = "translation_id")
//	@NotNull
//	@ApiModelProperty(value = "Translation for Each Word", required = true, example = "")
//	private Translation translation;
	@ManyToMany
	private List<Translation> translation;
	
	@Column(length = 20)
	@NotNull
	@ApiModelProperty(value = "Word key for eacher translation ", required = true, example = "BOOK")
	private String word_key;
	
	@Column(length = 20)
	@Length(max = 255)
	@NotNull
	@ApiModelProperty(value = "Word in English", required = true, example = "Book")
	private String word_en;
	
	@Column(length = 20)
	@Length(max = 255)
	@NotNull
	@ApiModelProperty(value = "Word in Khmer", required = true, example = "សៀវភៅ")
	private String word_kh;
	
	@Column(length = 20)
	@Length(max = 255)
	@NotNull
	@ApiModelProperty(value = "Word in Franch", required = true, example = "Book")
	private String word_fn;

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
	
	public String getWord_en() {
		return word_en;
	}

	public void setWord_en(String word_en) {
		this.word_en = word_en;
	}

	public String getWord_kh() {
		return word_kh;
	}

	public void setWord_kh(String word_kh) {
		this.word_kh = word_kh;
	}

	public String getWord_fn() {
		return word_fn;
	}

	public void setWord_fn(String word_fn) {
		this.word_fn = word_fn;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Translation> getTranslation() {
		return translation;
	}

	public void setTranslation(List<Translation> translation) {
		this.translation = translation;
	}
	
//	public Translation getTranslate() {
//		return translation;
//	}
//
//	public void setTranslate(Translation translation) {
//		this.translation = translation;
//	}
	

}
