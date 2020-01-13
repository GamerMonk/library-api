package com.opteamix.library.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.opteamix.library.publisher.exception.LibraryResourceAlreadyExistsException;

@Service
public class PublisherService {

	@Autowired
	private PublisherRepository publisherRepository;

	public Publisher addPublisher(Publisher publisherToBeAdded) 
			throws LibraryResourceAlreadyExistsException {
		
		PublisherEntity publisherEntity = new PublisherEntity(
				publisherToBeAdded.getName(), 
				publisherToBeAdded.getEmail(), 
				publisherToBeAdded.getPhoneNumber());
		
		PublisherEntity addedPublisher = null;
		try {
			addedPublisher = publisherRepository.save(publisherEntity);
		} catch (DataIntegrityViolationException e) {
			throw new LibraryResourceAlreadyExistsException("Publisher already exists");
		}
		
		publisherToBeAdded.setPublisherId(addedPublisher.getPublisherId());
		return publisherToBeAdded;
	}

}
