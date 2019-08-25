package com.puthisastra.rest.dto;

public class CreateVocabDTO {
	private Long category_id;
	private Long tag_id;
	private String key_en;
	private String description_en;
	private String image_url;
	
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	
	public String getKey_en() {
		return key_en;
	}
	
	public void setKey_en(String key_en) {
		this.key_en = key_en;
	}
	
	public Long getTag_id() {
		return tag_id;
	}
	public void setTag_id(Long tag_id) {
		this.tag_id = tag_id;
	}
	public String getDescription_en() {
		return description_en;
	}
	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
}
