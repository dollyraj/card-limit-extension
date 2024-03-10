package com.credit.cardlimit.repository;

import com.credit.cardlimit.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
}
