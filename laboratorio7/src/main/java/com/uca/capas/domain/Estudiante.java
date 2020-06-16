package com.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public", name="estudiante")
public class Estudiante {
	
	@Id
	@Column(name="c_usuario")
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue(generator="estudiante_c_usuario_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "estudiante_c_usuario_seq", sequenceName = "public.estudiante_c_usuario_seq", allocationSize = 1)
	private Integer codigoEstudiante;
	
	@Column(name="nombre")
	@Size(message="El nombre no debe tener mas de 20 caracteres", max = 20)
	@NotEmpty(message="Este campo no puede estar vacío") 
	private String nombre;
	
	@Column(name="apellido")
	@Size(message="El apellido no debe tener mas de 20 caracteres", max = 20)
	@NotEmpty(message="Este campo no puede estar vacío") 
	private String apellido;
	
	@Column(name="carne")
	@Size(message="El carnet debe tener 8 digitos", min=8, max=8)
	private String carnet;
	
	@Column(name="carrera")
	@Size(message="EL nombre de la carrera no debe tener mas de 30 caracteres", max = 30)
	@NotEmpty(message="El campo no puede estar vacío") 
	private String carrera;
	 
	//Constructor vacío
	public Estudiante() {
		
	}
	
	public Integer getCodigoEstudiante() {
		return codigoEstudiante;
	}
	public void setCodigoEstudiante(Integer codigoEstudiante) {
		this.codigoEstudiante = codigoEstudiante;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCarnet() {
		return carnet;
	}
	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
}
