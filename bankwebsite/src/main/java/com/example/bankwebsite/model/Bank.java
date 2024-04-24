// package com.example.bankwebsite.model;

// import com.fasterxml.jackson.annotation.JsonFormat;

// import jakarta.persistence.CascadeType;

// // import com.fasterxml.jackson.annotation.JsonIgnore;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToOne;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// public class Bank {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     int usrid;
//     int accountno;
//     String usrname;
//     String password;

//     // @OneToOne(cascade = CascadeType.ALL)
//     @OneToOne
//     private BankDescription bankDescription;
// }
// ;
package com.example.bankwebsite.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usrid;
    private int accountno;
    private String usrname;
    private String password;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<BankDescription> bankDescriptions;
}
