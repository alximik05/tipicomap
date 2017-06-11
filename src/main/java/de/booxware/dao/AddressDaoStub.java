package de.booxware.dao;

import de.booxware.domain.Address;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by stas on 08/06/17.
 */
@Repository
public class AddressDaoStub {

    private static final List<Address> ADDRESS_LIST = new CopyOnWriteArrayList<>();
    static {
        ADDRESS_LIST.add(new Address("Lugovoi", 10.11111111, 20.11111111));
        ADDRESS_LIST.add(new Address("Red Square", 30.11111111 , 40.11111111));
        ADDRESS_LIST.add(new Address("Kutuzovskaya", 50.11111111 ,60.11111111));
    }

    public List<Address> findAll() {
        return ADDRESS_LIST;
    }

    public Address save(Address address) {
        ADDRESS_LIST.add(address);
        return address;
    }


}
