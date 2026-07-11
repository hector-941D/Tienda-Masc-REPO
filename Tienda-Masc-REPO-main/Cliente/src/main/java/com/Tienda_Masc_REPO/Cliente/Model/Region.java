package com.Tienda_Masc_REPO.Cliente.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table (name = "Region")
public class Region {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idRegion;

    @Column
    private String nombreRegion;

    @OneToMany(mappedBy = "region")
    @ToString.Exclude
    private List<Comuna> comuna;
}
