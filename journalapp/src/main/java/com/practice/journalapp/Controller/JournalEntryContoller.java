package com.practice.journalapp.Controller;

import com.practice.journalapp.Entity.JournalEntry;
import com.practice.journalapp.Service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryContoller {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAllJournalEntries() {
        return journalEntryService.getAllJournalEntries();
    }

    @PostMapping
    public JournalEntry createNewJournalEntry(@RequestBody JournalEntry newEntry) {
        journalEntryService.saveNewEntry(newEntry);
        return newEntry;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId) {
        return journalEntryService.getJournalEntryById(myId).orElse(null);
    }

    @DeleteMapping("/id/{myId}")
    public Optional<JournalEntry> deleteJournalEntryById(@PathVariable ObjectId myId) {
        return journalEntryService.deleteJournalEntryById(myId);
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry modifiedEntry) {
        return journalEntryService.updateJournalEntryById(id, modifiedEntry);
    }
}
