package kr.ac.hansung.cse.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.ToString;

@ToString
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userId")
	private int id;
	
	@NotEmpty(message="The username must not be null")
	private String username;
	@NotEmpty(message="The password must not be null")
	private String password;
	@NotEmpty(message="The email must not be null")
	private String email;
	
	@OneToOne(optional=false,cascade=CascadeType.ALL) // user 저장시, 자동적으로 shippingAddress도 저장
	@JoinColumn(unique=true)
	private ShippingAddress shippingAddress; // 1대1관계
	
	// optional=false : null값일 수 X. 만드시 값이 들어가야 함
	// cascade=CascadeType.ALL : user 저장시, 자동적으로 shippingAddress도 저장
	@OneToOne(optional=false,cascade=CascadeType.ALL) 
	@JoinColumn(unique=true)
	// 실제 DB에서 column명 cart_id
	private Cart cart; // User와 Cart는 1대1관계
	
	private boolean enabled = false;
	private String authority;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
}
