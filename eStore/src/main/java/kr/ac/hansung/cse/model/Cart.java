package kr.ac.hansung.cse.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.ToString;

@ToString
@Entity
public class Cart implements Serializable{ // Serialization : 객체 -> JSON형태로 바꾸기 위해서
	private static final long serialVersionUID = 2959933019120878868L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // table대신 column사용해서 id 생성
	private int id;
	
	// mappedBy="cart" cart가 forienkey임을 알려줌
	// 실제 cartItem column 생성 되지 X
	// CascadeType.ALL : cart 저장/삭제 -> cartItem까지 같이 저장/삭제
	// @OneToMany default FetchType : lazy, @ManyToOne default FetchType : eager
	// FetchType.EAGER : cart 읽을 때, 자동적으로 cartItem도 읽어들임
	@OneToMany(mappedBy="cart",cascade=CascadeType.ALL,fetch=FetchType.EAGER) 
	private List<CartItem> cartItems = new ArrayList<CartItem>(); // 양방향 N대1관계
	
	private double grandTotal;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
}
