package com.example.bankwebsite.service;

import java.util.List;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.bankwebsite.model.Bank;
import com.example.bankwebsite.model.BankDescription;
import com.example.bankwebsite.repositary.BankDescriptionRepositary;
import com.example.bankwebsite.repositary.BankRepositary;

@Service
public class BankServices {
    @Autowired
    public BankRepositary bankRepositary;

    @Autowired
    public BankDescriptionRepositary bankDescriptionRepositary;

    public Bank saveBank(Bank bank)
    {
        return bankRepositary.save(bank);
    }

    public Bank getBank(int a)
    {
        return bankRepositary.findById(a).orElse(null);
    }

    public void deleteBank(int id)
    {
        bankRepositary.deleteById(id);
    }

    public boolean existsByUsrname(String usrname) {
        return bankRepositary.existsByUsrname(usrname);
    }
    
    // PageNation

    // public Page<Bank> getNation(int pageNo,int siz)
    // {
    //     return bankRepositary.findAll(PageRequest.of(pageNo, siz));
    // }
    


    // pagination list
    public java.util.List<Bank> getNationList(int pg,int sz)
    {
        // Page<Bank> pq=bankRepositary.findAll(PageRequest.of(pg, sz));
        // return pq.getContent();

        // -------------or-----------------

            return bankRepositary.findAll(PageRequest.of(pg, sz)).getContent();
    }
    
    // sorting
    public java.util.List<Bank> getSortingList(int pg,int sz,String col)
    {
        // Page<Bank> pq=bankRepositary.findAll(PageRequest.of(pg, sz));
        // return pq.getContent();
    
        // -------------or-----------------
    
            return bankRepositary.findAll(PageRequest.of(pg, sz,Sort.by(Direction.ASC,col))).getContent();
    }


    //using Query
    public List<Bank> findIdLessThan(int id)
    {
        return bankRepositary.findIdLessThan(id);
    }

    //using onetoone mapping
    public BankDescription saveBankDescription(BankDescription bankDescription)
    {
        return bankDescriptionRepositary.save(bankDescription);
    }
    public BankDescription getBankDescription(int id){
        return bankDescriptionRepositary.findById(id).orElse(null);
    }
    public void deleteBankDesi(int id)
    {
        bankDescriptionRepositary.deleteById(id);
    }

}
