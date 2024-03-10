package com.credit.cardlimit.services;

import com.credit.cardlimit.enums.LimitType;
import com.credit.cardlimit.enums.Status;
import com.credit.cardlimit.exceptions.ForbiddenException;
import com.credit.cardlimit.exceptions.ResourceAlreadyExistsException;
import com.credit.cardlimit.exceptions.ResourceNotFoundException;
import com.credit.cardlimit.model.Account;
import com.credit.cardlimit.model.Offer;
import com.credit.cardlimit.repository.AccountRepo;
import com.credit.cardlimit.repository.OfferRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OfferService {
    private final AccountRepo accountRepo;
    private final OfferRepo offerRepo;

    public OfferService(AccountRepo accountRepo, OfferRepo offerRepo)
    {
        this.accountRepo = accountRepo;
        this.offerRepo = offerRepo;
    }

    public ResponseEntity<Offer> createLimitOffer(Long accountId, Offer offerRequest)
    {
        Account account = accountRepo.findById( accountId ).
                orElseThrow( () -> new ResourceNotFoundException( "Account Id: " + accountId + " Not found" ) );

        if( offerRepo.existsById( offerRequest.getLimitId() ))
        {
            throw new ResourceAlreadyExistsException( "Limit Id: " + offerRequest.getLimitId() + " already exists" );
        }

        if( offerRequest.getOfferActivationTime().compareTo( offerRequest.getOfferExpiryTime() ) > 0
                || new Date().compareTo( offerRequest.getOfferExpiryTime() ) > 0 )
        {
            throw new ForbiddenException( "Expiry Date not valid" );
        }

        if( offerRequest.getLimitType() == LimitType.ACCOUNT_LIMIT )
        {
            if( offerRequest.getNewLimit() > account.getAccountLimit() )
            {
                offerRequest.setAccount( account );
                offerRepo.save( offerRequest );
            }
            else
            {
                throw new ForbiddenException( "New Account Limit is less than current Account Limit" );
            }
        }

        else if( offerRequest.getLimitType() == LimitType.PER_TRANSACTION_LIMIT )
        {
            if( offerRequest.getNewLimit() > account.getPerTransactionLimit() )
            {
                offerRequest.setAccount( account );
                offerRepo.save( offerRequest );
            }
            else
            {
                throw new ForbiddenException( "New Per-Transaction Limit is less than current Per-Transaction Limit" );
            }
        }

        return new ResponseEntity<>( offerRequest , HttpStatus.CREATED );
    }

    public ResponseEntity<List<Offer>> getLimitOffer(Long accountId )
    {
        if( ! accountRepo.existsById( accountId ) )
        {
            throw new ResourceNotFoundException( "Account Id: " + accountId + " Not found" );
        }

        List<Offer> offers = offerRepo.findActiveOffers( accountId );

        if( offers.size() == 0 )
        {
            return new ResponseEntity<>( offers , HttpStatus.NO_CONTENT );
        }

        return new ResponseEntity<>( offers , HttpStatus.OK );
    }

    public ResponseEntity<Offer> actLimitOffer( Long limitId, Status status )
    {
        Offer offer = offerRepo.findById( limitId ).
                orElseThrow( () -> new ResourceNotFoundException( "Limit Id: " + limitId + " Not found" ) );;

        if( new Date().compareTo( offer.getOfferActivationTime() ) < 0
                || new Date().compareTo(offer.getOfferExpiryTime()) > 0
                || offer.getStatus() != null )
        {
            throw new ForbiddenException( "Offer no longer valid" );
        }

        if( status == Status.ACCEPTED )
        {
            offer.setStatus(status);
            Account account = offer.getAccount();
            if(offer.getLimitType()==LimitType.ACCOUNT_LIMIT)
            {
                account.setLastAccountLimit(account.getAccountLimit());
                account.setAccountLimit(offer.getNewLimit());
                account.setAccountLimitUpdateTime(new Date());
            }

            else if( offer.getLimitType()==LimitType.PER_TRANSACTION_LIMIT )
            {
                account.setLastPerTransactionLimit( account.getPerTransactionLimit() );
                account.setPerTransactionLimit( offer.getNewLimit() );
                account.setPerTransactionLimitUpdateTime( new Date() );
            }
            offerRepo.save(offer);
        }

        return new ResponseEntity<>( offer, HttpStatus.OK );
    }
}
