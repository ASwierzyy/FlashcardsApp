package pl.edu.pja.tpo02.flashcardsapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.tpo02.flashcardsapp.displayStrategy.DisplayWords;
import pl.edu.pja.tpo02.flashcardsapp.exception.EntryNotFoundException;
import pl.edu.pja.tpo02.flashcardsapp.model.Entry;
import pl.edu.pja.tpo02.flashcardsapp.service.EntryService;

import java.util.List;

@RestController
@RequestMapping("/api/entries")
public class FlashcardsRestController {

    private final EntryService entryService;
    private final DisplayWords displayWords;

    public FlashcardsRestController(EntryService entryService, DisplayWords displayWords) {
        this.entryService = entryService;
        this.displayWords = displayWords;
    }

    @GetMapping
    public List<Entry> getAll(@RequestParam(defaultValue = "id") String sortBy,
                              @RequestParam(defaultValue = "1") String order) {
        List<Entry> entries = entryService.getAllSorted(sortBy, order);
        entries.forEach(e -> {
            e.setPolish(displayWords.display(e.getPolish()));
            e.setEnglish(displayWords.display(e.getEnglish()));
            e.setGerman(displayWords.display(e.getGerman()));
        });
        return entries;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entry> getById(@PathVariable Long id) {
        try {
            return entryService.getById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (EntryNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Entry create(@RequestBody Entry entry) {
        return entryService.addEntry(entry.getPolish(), entry.getEnglish(), entry.getGerman());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entry> update(@PathVariable Long id, @RequestBody Entry entry) {
        entry.setId(id);
        try {
            Entry updated = entryService.updateEntry(entry);
            return ResponseEntity.ok(updated);
        } catch (EntryNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            entryService.deleteEntry(id);
            return ResponseEntity.noContent().build();
        } catch (EntryNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<Entry> search(@RequestParam String type, @RequestParam String value) {
        List<Entry> results = entryService.search(type, value);

        results.forEach(e -> {
            e.setPolish(displayWords.display(e.getPolish()));
            e.setEnglish(displayWords.display(e.getEnglish()));
            e.setGerman(displayWords.display(e.getGerman()));
        });

        return results;
    }

}