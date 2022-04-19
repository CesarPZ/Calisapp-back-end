package com.calisapp.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Table(name = "exercise")
/*----------------------------------------------------------------
 	Descripcion:	Clase generada para el almacenamiento de ejercicio,
 	  				con sus caracteristicas.
	Fecha: 			19/04/2022
----------------------------------------------------------------*/
public class Exercise {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column
	private String name;
	@Column
	private Integer complexityNumber;
	@Column
	private String description;
	@Column
	private String urlVideo;

	public Exercise() { }
		
	public Exercise(String name, Integer complexityNumber, 
			String description, String urlVideo) {
		this.name = name;
		this.complexityNumber = complexityNumber;
		this.description = description;
		this.urlVideo = urlVideo;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getComplexityNumber() {
		return complexityNumber;
	}

	public void setComplexityNumber(Integer complexityNumber) {
		this.complexityNumber = complexityNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<String> getMusculos() {
		return musculos;
	}

	public void setMusculos(List<String> musculos) {
		this.musculos = musculos;
	}
	
	public String getUrlVideo() {
		return urlVideo;
	}

	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}
	
}