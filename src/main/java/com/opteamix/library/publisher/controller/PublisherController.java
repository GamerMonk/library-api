package com.opteamix.library.publisher.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opteamix.library.publisher.model.Publisher;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {

	@GetMapping("/{publisherId}")
	public Publisher getPublisher(@PathVariable String publisherId) {
		return new Publisher(publisherId, "Amit", "amitbrahma@hotmail.com", "8951089745");
	}
}
