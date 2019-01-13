package com.beemob.becastle.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by truongnguyen on 12/9/18.
 */
@RestController
public class TruongController {

    private final static Logger logger = LoggerFactory.getLogger(TruongController.class);

    private final TruongService mChestService;

    private ObjectMapper mObjectMapper = new ObjectMapper();

    @Autowired
    TruongController(TruongService chestService) {
        this.mChestService = chestService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> createChest(@RequestBody Truong chest) {


        Truong saved = mChestService.create(chest);
        if (saved != null) {
            return new ResponseEntity(saved, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> findOneById(@PathVariable("id") Long id) {
        Truong chest = mChestService.findOneById(id);
        if(chest != null) {
            return new ResponseEntity(chest, HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

}
