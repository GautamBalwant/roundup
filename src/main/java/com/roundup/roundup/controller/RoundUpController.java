package com.roundup.roundup.controller;

import com.roundup.roundup.model.client.saving.SavingsGoalResponse;
import com.roundup.roundup.service.impl.RoundUpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

/**
 * Controller class to expose the round up endpoint.
 */
@RestController
@RequestMapping("/api/v2/account")
public class RoundUpController {

    @Autowired
    private RoundUpServiceImpl roundUpServiceImpl;


    /**
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/roundup")
    public ResponseEntity<Void> postRoundUpSavings(@RequestHeader("Authorization") String authorization) {
        roundUpServiceImpl.roundUp(authorization);
         return ResponseEntity.ok().build();
    }
}
