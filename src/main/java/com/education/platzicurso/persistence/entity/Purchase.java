package com.education.platzicurso.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "purchase", schema = "platzi")
public class Purchase implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer purchaseId;

	@NotNull
	@Column(name = "client_id", length = 20)
	private String clientId;

	@Column(name = "date")
	private LocalDateTime date;

	@Column(name = "payment_method")
	private String paymentMethod;

	@Column(name = "comment", length = 300)
	private String comment;

	@Column(length = 1)
	private String state;

	@ManyToOne
	@JoinColumn(name = "client", insertable = false, updatable = false)
	private Client client;

	@OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
	private List<PurchaseItem> products;

	public Integer getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<PurchaseItem> getProducts() {
		return products;
	}

	public void setProducts(List<PurchaseItem> products) {
		this.products = products;
	}

}
