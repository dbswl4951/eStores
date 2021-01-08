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
public class Cart implements Serializable{ // Serialization : ��ü -> JSON���·� �ٲٱ� ���ؼ�
	private static final long serialVersionUID = 2959933019120878868L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // table��� column����ؼ� id ����
	private int id;
	
	// mappedBy="cart" cart�� forienkey���� �˷���
	// ���� cartItem column ���� ���� X
	// CascadeType.ALL : cart ����/���� -> cartItem���� ���� ����/����
	// @OneToMany default FetchType : lazy, @ManyToOne default FetchType : eager
	// FetchType.EAGER : cart ���� ��, �ڵ������� cartItem�� �о����
	@OneToMany(mappedBy="cart",cascade=CascadeType.ALL,fetch=FetchType.EAGER) 
	private List<CartItem> cartItems = new ArrayList<CartItem>(); // ����� N��1����
	
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
