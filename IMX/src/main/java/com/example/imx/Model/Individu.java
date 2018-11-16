package com.example.imx.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="individu")
public class Individu {

	@Id
	public String cle;
	
	@Column(name="type")	
	public String type;
	
	@Column(name="nom")
	public String nom;
	
	@Column(name="parent")
	public String parent;
	
	@Column(name="status")
	public String status;
	
	@Column(name="siege")
	public String siege;
		

	public Individu() {
	}

	public String getCle() {
		return cle;
	}

	public void setCle(String cle) {
		this.cle = cle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSiege() {
		return siege;
	}

	public void setSiege(String siege) {
		this.siege = siege;
	}
	

	
	
	
}
