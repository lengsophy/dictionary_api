package com.puthisastra.rest.dto;
public class CreateWordDTO {
	private Long category_id;
	private String word_key;
	private String word_en;
	private String word_fn;
	private String word_kh;
	private String translate_en;
	private String translate_fn;
	private String translate_kh;
	
	
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
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
	public String getWord_fn() {
		return word_fn;
	}
	public void setWord_fn(String word_fn) {
		this.word_fn = word_fn;
	}
	public String getWord_kh() {
		return word_kh;
	}
	public void setWord_kh(String word_kh) {
		this.word_kh = word_kh;
	}
	public String getTranslate_en() {
		return translate_en;
	}
	public void setTranslate_en(String translate_en) {
		this.translate_en = translate_en;
	}
	public String getTranslate_fn() {
		return translate_fn;
	}
	public void setTranslate_fn(String translate_fn) {
		this.translate_fn = translate_fn;
	}
	public String getTranslate_kh() {
		return translate_kh;
	}
	public void setTranslate_kh(String translate_kh) {
		this.translate_kh = translate_kh;
	}
	
}
