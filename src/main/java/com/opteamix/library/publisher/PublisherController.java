package com.opteamix.library.publisher;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opteamix.library.publisher.exception.LibraryResourceAlreadyExistsException;
import com.opteamix.library.publisher.exception.LibraryResourceNotFoundException;
import com.opteamix.library.util.LibraryApiUtil;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {

	@Autowired
	private PublisherService publisherService;

	@GetMapping("/{publisherId}")
	public ResponseEntity<?> getPublisher(@PathVariable Integer publisherId,
			@RequestHeader(value = "trace-id", defaultValue = "") String traceId) {
		Publisher publisher = null;
		
		if(LibraryApiUtil.doesStringValueExist(traceId)) {
			traceId = UUID.randomUUID().toString();
		}
		try {
			publisher = publisherService.getPublisher(publisherId, traceId);
		} catch (LibraryResourceNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(publisher, HttpStatus.OK);
	}
	
	@PutMapping("/{publisherId}")
	public ResponseEntity<?> updatePublisher(@PathVariable Integer publisherId, 
			@RequestBody Publisher publisher) {
		try {
			publisher.setPublisherId(publisherId);
			publisherService.updatePublisher(publisher);
		} catch (LibraryResourceNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(publisher, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> addPublisher(@RequestBody Publisher publisher, 
			@RequestHeader(value = "trace-id", defaultValue = "") String traceId){
		if(LibraryApiUtil.doesStringValueExist(traceId)) {
			traceId = UUID.randomUUID().toString();
		}
		
		try {
			publisherService.addPublisher(publisher, traceId);
		} catch (LibraryResourceAlreadyExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(publisher, HttpStatus.CREATED);
	}
}
