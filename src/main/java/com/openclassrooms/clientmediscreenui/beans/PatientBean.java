package com.openclassrooms.clientmediscreenui.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientBean {
    private Long id;
    private String prenom;
    private String nom;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateDeNaissance;
    private String adressePostale;
    private String numeroDeTelephone;

//    @Override
//    public String toString()
//    {
//        return "ProductBean{" +
//                "id=" + id +
//                ", prenom='" + prenom + '\'' +
//                ", nom='" + nom + '\'' +
//                ", adresse='" + adressePostale + '\'' +
//                ", dob=" + dateDeNaissance +
//                '}';
//    }
}
