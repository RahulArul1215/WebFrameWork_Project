package com.example.bankwebsite.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bankwebsite.model.Bank;


@Repository
public interface BankRepositary extends JpaRepository<Bank,Integer> {
    // boolean existsByUsername(String username);
    boolean existsByUsrname(String usrname);

    @Query(
        value = "Select * from bank where usrid < :val" , nativeQuery = true
    )
    List<Bank> findIdLessThan(@Param ("val") int id);
}

