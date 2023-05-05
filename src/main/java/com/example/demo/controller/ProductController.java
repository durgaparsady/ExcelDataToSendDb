package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Product;
import com.example.demo.helper.MyExcelHelper;
import com.example.demo.service.ProductService;

@RestController
@CrossOrigin("*")
public class ProductController {
	@Autowired
     private ProductService service;
	 
	@PostMapping("/product/upload")
	public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file){
		if(MyExcelHelper.checkExcelFormat(file)) {
			service.save(file);
			return ResponseEntity.ok(Map.of("message","File is Uploaded and Data is save to the DB"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Upload Excel File");
	}
		@GetMapping("/products")
		public List<Product>getallProduct(){
			return service.getAllProducts();
		}
}
