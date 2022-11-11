package com.education.platzicurso.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.education.platzicurso.domain.ProductDTO;
import com.education.platzicurso.domain.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ExampleProperty;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/all")
	@ApiOperation( value = "Get all supermakert products", notes = "Get all supermakert products", tags = "ProductDTO")
	@ApiResponses({
		@ApiResponse(code = 200 , message = "OK"),
		@ApiResponse(code = 404 , message = "ProductDTO not found")
	})
	public ResponseEntity<List<ProductDTO>> getAll() {
		return ResponseEntity.ok().body(productService.getAll());
	}

	@GetMapping("/{id}")
	@ApiOperation( value = "Get product", notes = "Search a product with an ID ", tags = "ProductDTO")
	@ApiResponses({
		@ApiResponse(code = 200 , message = "OK"),
		@ApiResponse(code = 404, message = "Not Found ProductDTO")
	})
	public ResponseEntity<ProductDTO> getProduct(@ApiParam(value = "The ID of the product", required = true, example = "7")
			@PathVariable("id") int productId) {
		return productService.getProduct(productId).map(product -> new ResponseEntity<>(product, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/category/{id}")
	@ApiOperation( value = "Search by category  ", notes = "Search a product with an ID of category", tags = "ProductDTO")
	@ApiResponses({
		@ApiResponse(code = 200 , message = "OK"),
		@ApiResponse(code = 404 , message = "ProductDTO not found")
	})
	public ResponseEntity<List<ProductDTO>> getByCategory(@ApiParam(value = "The ID of the category", required = true, example = "2")
			@PathVariable("id") int categoryId) {
		return productService.getByCategory(categoryId)
				.map(products -> new ResponseEntity<>(products, HttpStatus.CREATED))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/save")
	@ApiOperation(value = "Store product in supermarket", tags = "ProductDTO")
	@ApiResponses({
		@ApiResponse(code = 201, message = "OK"),
		@ApiResponse(code = 300, message = "Missing data entry error"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public ResponseEntity<ProductDTO> save(@ApiParam(value = "Body send product")
			@RequestBody ProductDTO productDTO) {
		return new ResponseEntity<>(productService.save(productDTO), HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	@ApiResponses({
		@ApiResponse(code = 201, message = "OK"),
		@ApiResponse(code = 300, message = "Missing data entry error")
	})
	public ResponseEntity delete(@ApiParam(name = "The ID of product to wanted")
			@PathVariable("id") int productId) {
		if (productService.delete(productId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
