package com.challenge.textarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TextareaService {
    @Autowired
    private TextareaRepository textareaRepository;

    public TextareaService(TextareaRepository textareaRepository) {
        this.textareaRepository = textareaRepository;
    }

    public AreaRecord getRecord(String id) {
        Optional<AreaRecord> res = textareaRepository.findById(id);
        return res.get();
    }
    public Iterable<AreaRecord> getRecords() {
        Iterable<AreaRecord> res = textareaRepository.findAll();
        return res;
    }
    public void setRecord(AreaRecord record) {
        textareaRepository.save(record);
    }
}
