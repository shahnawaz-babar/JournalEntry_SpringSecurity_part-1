package net.engineeringdigest.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;

@Slf4j
@RestController
public class JournalEntryService {

	@Autowired
	private JournalEntryRepository journalEntryRepository;
	@Autowired
	private UserService userService;

	@Transactional
	public void saveEntry(JournalEntry myEntry, String userName) {
		try
		{
		User user = userService.findByUserName(userName);
		myEntry.setDate(LocalDateTime.now());
		JournalEntry saved = journalEntryRepository.save(myEntry);
		user.getJournalEntries().add(saved);
		userService.saveEntry(user);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			throw new RuntimeException("An error occured while saving the entry ",e);
		}
	}
	
	public void saveEntry(JournalEntry journalEntry)
	{
		 journalEntryRepository.save(journalEntry);
	}

	public List<JournalEntry> getAll() {
		return journalEntryRepository.findAll();
	}

	public Optional<JournalEntry> findById(ObjectId myId) {
		return journalEntryRepository.findById(myId);
	}

	public void deleteById(ObjectId id,String userName) {
		User user=userService.findByUserName(userName);	
		user.getJournalEntries().removeIf(x->x.getId().equals(id));
		userService.saveEntry(user);
		journalEntryRepository.deleteById(id);
	}

}
