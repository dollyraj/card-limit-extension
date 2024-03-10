package com.credit.cardlimit.controller;

import com.credit.cardlimit.enums.Status;
import com.credit.cardlimit.model.Account;
import com.credit.cardlimit.model.Offer;
import com.credit.cardlimit.services.AccountService;
import com.credit.cardlimit.services.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class HomeController {

    private final AccountService accountService;
    private final OfferService offerService;

    public HomeController(AccountService accountService, OfferService offerService) {
        this.accountService = accountService;
        this.offerService = offerService;
    }

    @GetMapping("/v1/")
    public String Greetings(){
        return "Hi There! Welcome to Credit Card Limit Application !";
    }

    @PostMapping("/addAccount")
    public ResponseEntity<Account> addAccount(@RequestBody Account account)
    {
        return accountService.addAccount(account);
    }

    @GetMapping("/getAccount/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId)
    {
        return accountService.getAccount(accountId);
    }

    @PostMapping("/createLimitOffer/{accountId}")
    public ResponseEntity<Offer> createLimitOffer( @PathVariable(value = "accountId") Long accountId,
                                                   @RequestBody Offer offerRequest)
    {
        return offerService.createLimitOffer(accountId,offerRequest);
    }

    @GetMapping("/getLimitOffer/{accountId}")
    public ResponseEntity<List<Offer>> getLimitOffer(@PathVariable(value = "accountId") Long accountId)
    {
        return offerService.getLimitOffer(accountId);
    }

    @PostMapping("/actLimitOffer/{limitId}/{status}")
    public ResponseEntity<Offer> actLimitOffer( @PathVariable(value = "limitId") Long limitId,
                                                @PathVariable(value = "status") Status status)
    {
        return offerService.actLimitOffer(limitId,status);
    }
}
