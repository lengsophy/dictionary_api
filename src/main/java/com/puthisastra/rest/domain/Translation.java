package com.puthisastra.rest.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.puthisastra.rest.config.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "tbl_translate")
@ApiModel(description = "Translation of each word")
public class Translation extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(value = "id of the Word", example = "2")
    private long id;
	
	
//	@OneToOne(mappedBy="translation")
//	@JsonIgnore
//	private Word word;
	
	@ManyToMany
	@JsonIgnore
	private List<Word> WordList;
	
	@Column(length = 20)
	@Length(max = 255)
	@NotNull
	@ApiModelProperty(value = "Translate in English", required = true, example = "Book")
	private String translate_en;
	
	@Column(length = 20)
	@Length(max = 255)
	@NotNull
	@ApiModelProperty(value = "Translate in Khmer", required = true, example = "សៀវភៅ")
	private String translate_kh;
	
	@Column(length = 20)
	@Length(max = 255)
	@NotNull
	@ApiModelProperty(value = "Translate in Franch", required = true, example = "Book")
	private String translate_fn;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<Word> getWordList() {
		return WordList;
	}

	public void setWordList(List<Word> wordList) {
		WordList = wordList;
	}
	
//	public Word getWord() {
//		return word;
//	}
//
//	public void setWord(Word word) {
//		this.word = word;
//	}
	
}
