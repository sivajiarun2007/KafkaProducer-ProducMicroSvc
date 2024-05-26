package com.appsdeveloperblog.ws.products.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.ws.products.model.Product;
import com.appsdeveloperblog.ws.products.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	private ProductService producService;
	
	public ProductsController(ProductService producService) {
		this.producService = producService;
	}

	@PostMapping("/create")
	public ResponseEntity<String> createProduct(@RequestBody Product product) {
		String productId = producService.createProduct(product);
		return new ResponseEntity<>("Product created: " + productId, HttpStatus.CREATED);
	}
	
}
