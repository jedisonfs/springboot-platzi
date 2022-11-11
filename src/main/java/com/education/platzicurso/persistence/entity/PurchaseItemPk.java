package com.education.platzicurso.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PurchaseItemPk implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "purchase_id")
	private Integer purchaseId;

	@Column(name = "id_product")
	private Integer productId;

	public Integer getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}
