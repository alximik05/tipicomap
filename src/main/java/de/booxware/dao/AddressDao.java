package de.booxware.dao;

import de.booxware.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by stas on 07/06/17.
 */
public interface AddressDao extends JpaRepository<Address, Integer> {
}
