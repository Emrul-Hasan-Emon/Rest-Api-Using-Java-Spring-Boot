package com.practice.journalapp.Controller;

import com.practice.journalapp.Entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryContoller {
    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAllJournalEntries() {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public Boolean createNewJournalEntry(@RequestBody JournalEntry newEntry) {
        journalEntries.put(newEntry.getId(), newEntry);
        return true;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId) {
        return journalEntries.get(myId);
    }

    @DeleteMapping("/id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable Long myId) {
        return journalEntries.remove(myId);
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntryById(@PathVariable Long id, @RequestBody JournalEntry modifiedEntry) {
        return journalEntries.put(id, modifiedEntry);
    }
}
