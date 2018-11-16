package com.example.imx.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.imx.Model.Individu;

@Repository
public interface IndividuRepo extends JpaRepository<Individu, String> {

}
