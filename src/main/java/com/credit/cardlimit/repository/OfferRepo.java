package com.credit.cardlimit.repository;

import com.credit.cardlimit.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepo extends JpaRepository<Offer,Long> {
    @Query(value = "from Offer where status is null and offerExpiryTime >= CURRENT_TIMESTAMP "
            + "and offerActivationTime<= CURRENT_TIMESTAMP and account.accountId = ?1")
    List<Offer> findActiveOffers(Long accountId);
}
