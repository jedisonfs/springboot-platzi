package com.education.platzicurso.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.education.platzicurso.domain.PurchaseDTO;
import com.education.platzicurso.domain.service.PurchaseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;

	
	@ApiOperation(value = "Get all purchases", notes = "", tags = "PurchaseDTO")
	@ApiResponses(value = {
			@ApiResponse(code = 200 , message = "OK"),
			@ApiResponse(code = 404 , message = "Not Found PurchaseDTO"),
			@ApiResponse(code = 500 , message = "Internal Server Error")
	})
	@GetMapping("/all")
	public ResponseEntity<List<PurchaseDTO>> getAll() {
		return ResponseEntity.ok(purchaseService.getAll());
	}

	@GetMapping("/{id}")
	@ApiOperation( value = "Get all supermakert purchases by ID client", notes = "Get all supermakert purchases of ID client", tags = "ProductDTO")
	@ApiResponses(value = {
			@ApiResponse(code = 200 , message = "OK"),
			@ApiResponse(code = 404 , message = "Not Found PurchaseDTO"),
			@ApiResponse(code = 500 , message = "Internal Server Error")
	})
	public ResponseEntity<PurchaseDTO> getByClient(@PathVariable("id") String clientId) {
		return purchaseService.getByClient(clientId).map(purchase -> new ResponseEntity<>(purchase, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	@ApiOperation(value = "Store a purchase", tags = "PurchaseDTO")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 404, message = "Not Found Purcharse"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public ResponseEntity<PurchaseDTO> save(@RequestBody PurchaseDTO purchaseDTO) {
		return new ResponseEntity<>(purchaseService.save(purchaseDTO), HttpStatus.CREATED);
	}
}
