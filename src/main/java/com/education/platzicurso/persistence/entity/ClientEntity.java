package com.education.platzicurso.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "client", schema = "platzi")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@NotNull
	@Column(length = 40)
	private String name;

	@NotNull
	@Column(length = 100)
	private String lastname;

	@NotNull
	@Column(name = "number_mobile")
	private Integer mobile;

	@Column(length = 80)
	private String address;

	@NotNull
	@Column(name = "address_email", length = 70)
	private String addressEmail;

	@NotNull
	@OneToMany(mappedBy = "client")
	private List<Purchase> purchase;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressEmail() {
		return addressEmail;
	}

	public void setAddressEmail(String addressEmail) {
		this.addressEmail = addressEmail;
	}

	public List<Purchase> getPurchase() {
		return purchase;
	}

	public void setPurchase(List<Purchase> purchase) {
		this.purchase = purchase;
	}

}
