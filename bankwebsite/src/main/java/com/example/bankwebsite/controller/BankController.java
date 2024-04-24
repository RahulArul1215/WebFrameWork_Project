package com.example.bankwebsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankwebsite.model.Bank;
import com.example.bankwebsite.model.BankDescription;
import com.example.bankwebsite.service.BankServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;







@RestController
public class BankController {
    @Autowired
    public BankServices bankServices;
    

    // public BankController(BankServices bankServices)
    // {
    //     this.bankServices=bankServices;
    // }

    @SuppressWarnings("rawtypes")
    @PostMapping("/banksignin")
    public ResponseEntity postMethodName(@RequestBody Bank bank) {
        if(bankServices.existsByUsrname(bank.getUsrname())){
            return ResponseEntity.status(409).body("USer Already exist");
        }
        Bank ob=bankServices.saveBank(bank);
        // return new ResponseEntity<>("Account has been created", HttpStatus.ACCEPTED);
        return ResponseEntity.status(200).body(ob);
    }
    
    @SuppressWarnings("rawtypes")
    @GetMapping("/bank/login/{id}")
    public ResponseEntity getMethodName(@PathVariable ("id") int a) {
            Bank oh= bankServices.getBank(a);
            return ResponseEntity.status(200).body(oh);
    }

    // Putmappinng with validation
    @SuppressWarnings("rawtypes")
    @PutMapping("/update/{id}/{val}")
    public ResponseEntity updatePassWord(@PathVariable int id, @PathVariable String val) {
        Bank oldbank = bankServices.getBank(id);
        if(oldbank!=null){
            oldbank.setUsrname(val);
            bankServices.saveBank(oldbank);
            return new ResponseEntity<>("Updated", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("not Updated", HttpStatus.NOT_ACCEPTABLE);
    }
    
    @SuppressWarnings("rawtypes")
    @DeleteMapping("/bank/{id}")
    public ResponseEntity deleteBank(@PathVariable ("id") int n)
    {
        Bank existiBank=bankServices.getBank(n);
        if(existiBank!=null)
        {
            bankServices.deleteBank(n);
            return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
        }
        // return new ResponseEntity<>("No data found", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(404).body("data not found");
    }


    //using native Query
    

    // PageNation-with full content
    // @GetMapping("/bankopop/{pn}/{sz}")
    // public Page<Bank> getMethodName(@PathVariable ("pn") int pn,@PathVariable("sz") int sz) {
    //     return bankServices.getNation(pn, sz);
    // }
    
    // pagination with only list of objects using getContent()
    @GetMapping("/bankopop/{pn}/{sz}")
    public List<Bank> getMethodName(@PathVariable ("pn") int pn,@PathVariable("sz") int sz) {
        return bankServices.getNationList(pn, sz);
    }
    

    //sorting
    @GetMapping("/bankopop/{pn}/{sz}/{col}")
    public List<Bank> getMethodName(@PathVariable ("pn") int pn,@PathVariable("sz") int sz,@PathVariable ("col") String col) {
        return bankServices.getSortingList(pn, sz,col);
    }

    // Query based data retrivel
    @GetMapping("/bankquery/{id}")
    public ResponseEntity<List<Bank>> getBanksWithIdLessThan(@PathVariable("id") int id) {
        List<Bank> banks = bankServices.findIdLessThan(id);
        if (banks != null) {
            return ResponseEntity.status(300).body(banks);
        }
        return ResponseEntity.notFound().build();
    }

    //onetoone mapping
    @PostMapping("/onetoone")
    public BankDescription postMethodName(@RequestBody BankDescription bankDescription) {
        return bankServices.saveBankDescription(bankDescription);        
    }

    @GetMapping("/onetoone/get/{id}")
    public BankDescription getDescriptionName(@PathVariable("id") int id) {
        return bankServices.getBankDescription(id);
    }

    @DeleteMapping("/onetoonedelete/{id}")
    public BankDescription deleteBankDescription(@PathVariable("id") int id){
        bankServices.deleteBankDesi(id);
        return null;
    }
// -------onetomany-------------------------
    @PostMapping("/onetoMany")
    public BankDescription postonetomanyName(@RequestBody BankDescription  b) {
      Bank bank=b.getBank();
      Bank ex=bankServices.getBank(bank.getUsrid());
      b.setBank(ex);
      return bankServices.saveBankDescription(b);
    }
}