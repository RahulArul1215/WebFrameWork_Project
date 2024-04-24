package com.example.bankwebsite.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankwebsite.model.BankDescription;

public interface BankDescriptionRepositary extends JpaRepository<BankDescription,Integer> {
    
}
