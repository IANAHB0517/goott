package com.jspbasic.vo;

public class ProductVo {
	private String name;
	private int qty;
	private int price;
	private String color;
	
	public ProductVo(String name, int qty, int price, String color) {
		super();
		this.name = name;
		this.qty = qty;
		this.price = price;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "ProductVo [name=" + name + ", qty=" + qty + ", price=" + price + ", color=" + color + "]";
	}
	
	
	
}
