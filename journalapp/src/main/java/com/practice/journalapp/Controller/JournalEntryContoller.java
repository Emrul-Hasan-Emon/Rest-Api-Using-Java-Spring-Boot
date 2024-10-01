package com.practice.journalapp.Controller;

import com.practice.journalapp.Entity.JournalEntry;
import com.practice.journalapp.Service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryContoller {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntries() {
        List<JournalEntry> allJournalEntries = journalEntryService.getAllJournalEntries();
        if (!allJournalEntries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(allJournalEntries);
        }
        return ResponseEntity.status(HttpStatus.OK).body("No journal entry found");
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createNewJournalEntry(@RequestBody JournalEntry newEntry) {
        journalEntryService.saveNewEntry(newEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEntry);
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntryById = journalEntryService.getJournalEntryById(myId);
        if (journalEntryById.isPresent()) {
            return ResponseEntity.ok(journalEntryById.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
        journalEntryService.deleteJournalEntryById(myId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry modifiedEntry) {
        JournalEntry entry = journalEntryService.updateJournalEntryById(id, modifiedEntry);
        return ResponseEntity.status(HttpStatus.OK).body(entry);
    }
}
