package com.groupeisi.gestionEtudiant.repository;

import com.groupeisi.gestionEtudiant.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
