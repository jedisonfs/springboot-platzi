package com.education.platzicurso.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.education.platzicurso.persistence.entity.PurchaseItemPk;

@Entity
@Table(name = "purchase_item", schema = "platzi")
public class PurchaseItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PurchaseItemPk id;

	@NotNull
	private Integer quantity;

	@NotNull
	@Column(precision = 16)
	private Double total;

	@NotNull
	private Boolean state;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId(value = "purchaseId")
	@JoinColumn(name = "fk_purchase_id", insertable = false, updatable = false, nullable = false)
	private Purchase purchase;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_producto", nullable = false)
	private Product products;

	public PurchaseItemPk getId() {
		return id;
	}

	public void setId(PurchaseItemPk id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

}
