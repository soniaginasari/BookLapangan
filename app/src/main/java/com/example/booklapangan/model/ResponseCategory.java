package com.example.booklapangan.model;

import java.util.List;

public class ResponseCategory{
	private List<CategoryItem> category;

	public void setCategory(List<CategoryItem> category){
		this.category = category;
	}

	public List<CategoryItem> getCategory(){
		return category;
	}

	@Override
 	public String toString(){
		return 
			"ResponseCategory{" + 
			"category = '" + category + '\'' + 
			"}";
		}
}