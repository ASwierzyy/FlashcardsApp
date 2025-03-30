package pl.edu.pja.tpo02.flashcardsapp.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.edu.pja.tpo02.flashcardsapp.exception.EntryNotFoundException;
import pl.edu.pja.tpo02.flashcardsapp.model.Entry;
import pl.edu.pja.tpo02.flashcardsapp.repository.EntryRepository;

import java.util.*;


@Service
public class EntryService {

    private final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Entry addEntry(String pl, String en, String ge){
        Long newId = entryRepository.count() + 1;
        Entry entry = new Entry(newId,pl,en,ge);
        return entryRepository.save(entry);
    }

    public Entry updateEntry (Entry entry) throws EntryNotFoundException {
        if (!entryRepository.existsById(entry.getId())) {
            throw new EntryNotFoundException();
        }
        return entryRepository.save(entry);
    }

    public void deleteEntry(Long id) throws EntryNotFoundException {
        if (!entryRepository.existsById(id)) {
            throw new EntryNotFoundException();
        }
        entryRepository.deleteById(id);
    }

    public Optional<Entry> getById(Long id) throws EntryNotFoundException {
        return entryRepository.findById(id);
    }


    public List<Entry> getAllSorted(String attribute, String option) {
        Sort.Direction direction = Sort.Direction.ASC;
        switch (option){
            case "1" -> direction = Sort.Direction.ASC;
            case "2" -> direction = Sort.Direction.DESC;
        }

        Sort sort = Sort.by(direction, attribute);
        return entryRepository.findAll(sort);
    }

    public List<Entry> search(String searchType, String searchValue) {
        return switch (searchType.toLowerCase()) {
            case "id" -> entryRepository.findById(Long.parseLong(searchValue))
                    .map(List::of)
                    .orElse(Collections.emptyList());

            case "word" -> entryRepository
                    .findByPolishContainingIgnoreCaseOrEnglishContainingIgnoreCaseOrGermanContainingIgnoreCase(
                            searchValue, searchValue, searchValue);
            default -> Collections.emptyList();
        };
    }

    public Optional<Entry> getRandomEntry() {
        List<Entry> entries = (List<Entry>) entryRepository.findAll();
        if (entries.isEmpty()) return Optional.empty();
        return Optional.of(entries.get(new Random().nextInt(entries.size())));
    }
}
















