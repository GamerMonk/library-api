package com.opteamix.library.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opteamix.library.publisher.exception.LibraryResourceAlreadyExistsException;
import com.opteamix.library.publisher.exception.LibraryResourceNotFoundException;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {

	@Autowired
	private PublisherService publisherService;

	@GetMapping("/{publisherId}")
	public ResponseEntity<?> getPublisher(@PathVariable Integer publisherId) {
		
		Publisher publisher = null;
		try {
			publisher = publisherService.getPublisher(publisherId);
		} catch (LibraryResourceNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(publisher, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> addPublisher(@RequestBody Publisher publisher){
		try {
			publisherService.addPublisher(publisher);
		} catch (LibraryResourceAlreadyExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(publisher, HttpStatus.CREATED);
	}
}
