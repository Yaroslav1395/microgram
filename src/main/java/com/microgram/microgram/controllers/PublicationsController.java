package com.microgram.microgram.controllers;

import com.microgram.microgram.dto.PublicationDto;
import com.microgram.microgram.services.PublicationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publications")
@RequiredArgsConstructor
public class PublicationsController {
    private final PublicationsService publicationsService;

    @GetMapping("/createPublicationsTable")
    public void createPublicationTable(){
        publicationsService.createPublicationsTable();
    }
    @GetMapping("/dropPublicationsTable")
    public void dropPublicationTable(){
        publicationsService.dropPublicationTable();
    }
    @PostMapping("/createNewPublication")
    public ResponseEntity<Long> createNewPublication(@RequestBody PublicationDto publicationDto){
        return new ResponseEntity<>(publicationsService.createNewPublication(publicationDto), HttpStatus.OK);
    }
    @GetMapping("/getPublicationForUser/{userId}")
    public ResponseEntity<List<PublicationDto>> getPublicationsForUser(@PathVariable String userId){
        try {
            return new ResponseEntity<>(publicationsService.getPublicationForUser(Integer.parseInt(userId)), HttpStatus.OK);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return new ResponseEntity<>(publicationsService.emptyListOfPublications(), HttpStatus.OK);
        }
    }
    @GetMapping("/getPublicationsForUserBySubscriptions/{userId}")
    public ResponseEntity<List<PublicationDto>> getPublicationsForUserBySubscriptions(@PathVariable String userId){
        try {
            return new ResponseEntity<>(publicationsService.getPublicationsForUserBySubscriptions(Integer.parseInt(userId)), HttpStatus.OK);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return new ResponseEntity<>(publicationsService.emptyListOfPublications(), HttpStatus.OK);
        }
    }
}
