package com.challenge.textarea;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.Area;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class TextareaController {

    @Autowired
    private TextareaService textareaService;

    public TextareaController(TextareaService textareaService) {
        this.textareaService = textareaService;
    }
    @GetMapping("/content")
    public ResponseEntity<String> getRecord(@RequestParam String id) {
        AreaRecord res = textareaService.getRecord(id);
        if (res == null) {
            return new ResponseEntity<String>("Item not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(res.getContent(), HttpStatus.OK);
    }
    @PutMapping("/content")
    public ResponseEntity<String> setRecord(@RequestBody AreaRecord record) {
        textareaService.setRecord(record);
        //Need to handle error case
        return new ResponseEntity<String>("Saved successfully!", HttpStatus.OK);
    }
    @GetMapping("/all-content")
    public ResponseEntity<String> getRecords() {
        Iterable<AreaRecord> res = textareaService.getRecords();
        StringBuilder body = new StringBuilder();
        for (AreaRecord item : res) {
            body.append(item.getContent());
        }
        return new ResponseEntity<String>(body.toString(), HttpStatus.OK);
    }
}