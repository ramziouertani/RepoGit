package fr.bpifrance.fmg.ctg.fct.mock.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bpifrance.fmg.ctg.fct.mock.Model.Individu;

@Repository
public interface IndividuRepo extends JpaRepository<Individu, String> {

}
