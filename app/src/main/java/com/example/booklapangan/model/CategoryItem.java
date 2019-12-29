package com.example.booklapangan.model;

import com.google.gson.annotations.SerializedName;

public class CategoryItem{
	@SerializedName("id")
	private String id;

	@SerializedName("category_name")
	private String category_name;

	public void setId_kategori(String id){
		this.id = id;
	}

	public String getId_kategori(){
		return id;
	}

	public void setCategory_name(String category_name){
		this.category_name = category_name;
	}

	public String getCategory_name(){
		return category_name;
	}

	@Override
 	public String toString(){
		return 
			"CategoryItem{" + 
			"category_name = '" + category_name + '\'' +
			"}";
		}
}
