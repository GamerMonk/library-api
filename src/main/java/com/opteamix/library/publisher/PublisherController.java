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

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {

	@Autowired
	private PublisherService publisherService;

	@GetMapping("/{publisherId}")
	public Publisher getPublisher(@PathVariable Integer publisherId) {
		return new Publisher(publisherId, "Amit", "amitbrahma@hotmail.com", "8951089745");
	}
	
	@PostMapping
	public ResponseEntity<?> addPublisher(@RequestBody Publisher publisher){
		try {
			publisher = publisherService.addPublisher(publisher);
		} catch (LibraryResourceAlreadyExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(publisher, HttpStatus.CREATED);
	}
}