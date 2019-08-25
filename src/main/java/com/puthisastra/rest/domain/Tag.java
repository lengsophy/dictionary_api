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

@Entity(name = "tbl_tag")
@ApiModel(description = "Tags Term Year of each Vocab")
public class Tag extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(value = "id of the Tag", example = "2")
    private long id;
	
	@OneToMany(mappedBy="tag",cascade=CascadeType.ALL)   
	private List<TagVocab> tagVocab;
	@Column(length = 20)
	@Length(max = 255)
	@NotNull
	@ApiModelProperty(value = "Vocab's Year", required = true, example = "1")
	private String year;
	
	@Column(length = 20)
	@Length(max = 255)
	@NotNull
	@ApiModelProperty(value = "Vocab's Term", required = true, example = "3")
	private String term;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
}
