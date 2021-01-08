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
public class CartItem implements Serializable{ // Serialization : 객체 -> JSON형태로 바꾸기 위해서
	private static final long serialVersionUID = -8547717040075526957L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne // CartItem이 many
	@JoinColumn(name="cartId")
	// Cart 클래스의 List<CartItem> cartItems로 인해 CartItem 클래스로 이동 -> CartItem 클래스에서 또 Cart 클래스로 이동 (양방향)
	// 이러한 순환 사이클을 막기 위해 @JsonIgnore사용
	// Cart 클래스 -> CartItem 클래스 (끝)
	@JsonIgnore
	private Cart cart; // 양방향 n대1
	
	@ManyToOne // CartItem이 many
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
