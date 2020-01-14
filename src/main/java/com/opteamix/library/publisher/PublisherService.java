package com.opteamix.library.publisher;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.opteamix.library.publisher.exception.LibraryResourceAlreadyExistsException;
import com.opteamix.library.publisher.exception.LibraryResourceNotFoundException;

@Service
public class PublisherService {

	@Autowired
	private PublisherRepository publisherRepository;

	public void addPublisher(Publisher publisherToBeAdded) 
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
	}

	public Publisher getPublisher(Integer publisherId) throws LibraryResourceNotFoundException {
		Optional<PublisherEntity> publisherEntity = publisherRepository.findById(publisherId);
		Publisher publisher = null;
		if(publisherEntity.isPresent()) {
			PublisherEntity pe = publisherEntity.get();
			publisher = createPublisherFromEntity(pe);
		} else {
			throw new LibraryResourceNotFoundException("Publisher Id " + publisherId + " not found");
		}
		return publisher;
	}

	private Publisher createPublisherFromEntity(PublisherEntity pe) {
		return new Publisher(pe.getPublisherId(), pe.getName(), pe.getEmailId(), pe.getPhoneNumber());
	}

}
