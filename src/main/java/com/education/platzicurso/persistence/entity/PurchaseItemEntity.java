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

@Entity
@Table(name = "purchase_item", schema = "platzi")
public class PurchaseItemEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PurchaseItemPkEntity id;

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
	private PurchaseEntity purchaseEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_producto", nullable = false)
	private ProductEntity products;

	public PurchaseItemPkEntity getId() {
		return id;
	}

	public void setId(PurchaseItemPkEntity id) {
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

	public PurchaseEntity getPurchase() {
		return purchaseEntity;
	}

	public void setPurchase(PurchaseEntity purchaseEntity) {
		this.purchaseEntity = purchaseEntity;
	}

	public ProductEntity getProducts() {
		return products;
	}

	public void setProducts(ProductEntity products) {
		this.products = products;
	}

}
