package kr.ac.hansung.cse.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.ToString;

@ToString
@Entity
public class CartItem implements Serializable{ // Serialization : ��ü -> JSON���·� �ٲٱ� ���ؼ�
	private static final long serialVersionUID = -8547717040075526957L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne // CartItem�� many
	@JoinColumn(name="cartId")
	// Cart Ŭ������ List<CartItem> cartItems�� ���� CartItem Ŭ������ �̵� -> CartItem Ŭ�������� �� Cart Ŭ������ �̵� (�����)
	// �̷��� ��ȯ ����Ŭ�� ���� ���� @JsonIgnore���
	// Cart Ŭ���� -> CartItem Ŭ���� (��)
	@JsonIgnore
	private Cart cart; // ����� n��1
	
	@ManyToOne // CartItem�� many
	@JoinColumn(name="productId")
	private Product product;
	
	private int quantity;
	private double totalPrice;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
