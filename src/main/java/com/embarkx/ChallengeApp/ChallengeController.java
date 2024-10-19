package com.embarkx.ChallengeApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChallengeController {
  private   ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping("/challenges")
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        return new  ResponseEntity<>(ChallengeService.getAllChallenges(),HttpStatus.OK);
    }

    @PostMapping("/challenges")
    public   ResponseEntity<String> addChallenge(@RequestBody Challenge challenge) {
        boolean isChallengeAdded=challengeService.addChallenge(challenge);
        if(isChallengeAdded) {
            return new ResponseEntity<>("Challenge added successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Challenge not added successfully",HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("/challenges/{month}")
    public ResponseEntity<Challenge> getChallenges(@PathVariable  String month) {
         Challenge challenge=  ChallengeService.getChallenges(month);
         if(challenge!=null){
             return new ResponseEntity<>(challenge, HttpStatus.OK);
         }
         else{
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
    }
   @PutMapping("/challenges/{id}")
    public ResponseEntity<String>updateChallenge(@PathVariable Long id,@RequestBody Challenge updatedChallenge){
      boolean isChallengeUpdated=  ChallengeService.updateChallenge(id,updatedChallenge);

     if(isChallengeUpdated){
         return  new ResponseEntity<>("Challenge updated Succesfully",HttpStatus.OK);

     }
     else {
         return new ResponseEntity<>("Challenge not updated Succesfully ",HttpStatus.NOT_FOUND);
     }
    }
    @DeleteMapping("/challenges/{id}")
    public ResponseEntity<String>deleteChallenge(@PathVariable Long id){
        boolean isChallengeDeleted=challengeService.deleteChallenge(id);
        if(isChallengeDeleted){
            return  new ResponseEntity<>("Challenge Deleted Succesfully",HttpStatus.OK);

        }
        else {
            return new ResponseEntity<>("Challenge not Deleted Succesfully ",HttpStatus.NOT_FOUND);
        }


    }
}
