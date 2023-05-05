package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Product;
import com.example.demo.helper.MyExcelHelper;
import com.example.demo.repository.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo repo;
	 public void save(MultipartFile file) {
		 try {
		List<Product> products=	MyExcelHelper.convertExcelToListOfProduct(file.getInputStream());
		 	
		 repo.saveAll(products);
		} catch (IOException e) { 
			e.printStackTrace();
		}
	 }
	 public List<Product> getAllProducts(){
		  return this.repo.findAll();
	 }
}
