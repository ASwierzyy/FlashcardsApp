package pl.edu.pja.tpo02.flashcardsapp.repository;


import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.tpo02.flashcardsapp.model.Entry;

import java.util.List;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {

    List<Entry> findByPolishContainingIgnoreCaseOrEnglishContainingIgnoreCaseOrGermanContainingIgnoreCase(String pl , String en, String ge);
    List<Entry> findAll(Sort sort);
}