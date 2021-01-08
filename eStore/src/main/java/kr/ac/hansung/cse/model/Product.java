package kr.ac.hansung.cse.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.ToString;

@ToString
@Entity
@Table(name="product")
public class Product implements Serializable{
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", menufacture="
				+ menufacture + ", unitInStock=" + unitInStock + ", description=" + description + ", imageFilename="
				+ imageFilename + "]";
	}
	private static final long serialVersionUID = 4583652850396187269L;
	@Id
	// 5����(���� ����)������ AUTO�ָ� ���̺� ���� ��
	// IDENTITY�� �ָ� table���� ��� �÷��� ���� �� (�� ������ ������)
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="product_id")
	private int id;
	
	@NotNull(message="The product name must not be null")
	private String name;
	private String category;
	@Min(value=0,message="The product price must not be less than zero")
	private int price;
	@NotNull(message="The product menufacture must not be null")
	private String menufacture;
	@NotNull(message="The product unitInStock must not be null")
	private int unitInStock;
	private String description;
	@Transient
	private MultipartFile productImage; // ���� ������ �̹���, ������ �� => �̰� DB�� ����X
	private String imageFilename; // ���� DB�� �����ϴ� ���� (�̹����� �̸�)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public int getUnitInStock() {
		return unitInStock;
	}
	public void setUnitInStock(int unitInStock) {
		this.unitInStock = unitInStock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMenufacture() {
		return menufacture;
	}
	public void setMenufacture(String menufacture) {
		this.menufacture = menufacture;
	}
	public MultipartFile getProductImage() {
		return productImage;
	}
	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}
	public String getImageFilename() {
		return imageFilename;
	}
	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}
}