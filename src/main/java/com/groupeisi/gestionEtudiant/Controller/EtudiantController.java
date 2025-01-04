package com.groupeisi.gestionEtudiant.Controller;


import com.groupeisi.gestionEtudiant.Etudiant;
import com.groupeisi.gestionEtudiant.repository.EtudiantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/etudiants")
public class EtudiantController {

    final EtudiantRepository etudiantRepository;

    public EtudiantController(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @GetMapping
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        return new ResponseEntity<>(etudiantRepository.findAll(), HttpStatus.OK);
    }
    @PostMapping ResponseEntity<Etudiant> createEtudiant(@RequestBody Etudiant etudiant) {
        Etudiant etudiantCreated = etudiantRepository.save(etudiant);
        return new ResponseEntity<>(etudiantCreated, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantId(@PathVariable long id) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        return etudiant.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable long id, @RequestBody Etudiant etudiantDetails) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);

        if (etudiant.isPresent()) {
            Etudiant existingEtudiant = etudiant.get();
            existingEtudiant.setEmail(etudiantDetails.getEmail());

            Etudiant updateEtudiant = etudiantRepository.save(existingEtudiant);
            return new ResponseEntity<>(updateEtudiant, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable long id) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);

        if (etudiant.isPresent()) {
            etudiantRepository.delete(etudiant.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}