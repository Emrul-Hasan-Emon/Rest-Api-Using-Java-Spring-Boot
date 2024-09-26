package com.practice.journalapp.Service;

import com.practice.journalapp.Entity.JournalEntry;
import com.practice.journalapp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveNewEntry(JournalEntry newEntry) {
        newEntry.setDateTime(LocalDateTime.now());
        journalEntryRepository.save(newEntry);
    }

    public List<JournalEntry> getAllJournalEntries() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public Optional<JournalEntry> deleteJournalEntryById(ObjectId id) {
        Optional<JournalEntry> entry = journalEntryRepository.findById(id);
        journalEntryRepository.deleteById(id);
        return entry;
    }

    public JournalEntry updateJournalEntryById(ObjectId id, JournalEntry updatedEntry) {
        JournalEntry entry = journalEntryRepository.findById(id).orElse(null);
        if (entry != null) {
            entry.setTitle(isNotEmpty(updatedEntry.getTitle()) ? updatedEntry.getTitle() : entry.getTitle());
            entry.setContent(isNotEmpty(updatedEntry.getContent()) ? updatedEntry.getContent() : entry.getContent());
            saveNewEntry(entry);
        }
        return entry;
    }

    private Boolean isNotEmpty(String title) {
        return title != null && !title.isEmpty();
    }
}
