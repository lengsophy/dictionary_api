package com.puthisastra.rest.domain;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.puthisastra.rest.config.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "tbl_vocab")
@ApiModel(description = "A Vocab in Dictionary")
public class Vocab extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(value = "id of the Vocab", example = "2")
    private long id;
	
	@OneToMany(mappedBy="vocab",cascade=CascadeType.ALL)   
	private List<TagVocab> tagVocab;
	
	@OneToMany(mappedBy="vocab",cascade=CascadeType.ALL)   
	private List<CategoryVocab> categoryVocab;
	
	@Column(length = 20)
	@Length(max = 255)
	@NotNull
	@ApiModelProperty(value = "Vocab in English", required = true, example = "Book")
	private String key_en;
	
	@Column(length = 20)
	@Length(max = 255)
	@NotNull
	@ApiModelProperty(value = "Vocab's Description in English", required = true, example = "Book Is somethink that can read and write on!")
	private String description_en;
	
	@Column(length = 20)
	@Length(max = 255)
	@NotNull
	@ApiModelProperty(value = "Vocab's image in English", required = true, example = "Url Path")
	private String image_url;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getKey_en() {
		return key_en;
	}

	public void setKey_en(String key_en) {
		this.key_en = key_en;
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
