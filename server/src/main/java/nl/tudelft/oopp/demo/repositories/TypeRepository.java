package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
}
