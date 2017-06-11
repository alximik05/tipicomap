package de.booxware.service;

import de.booxware.dao.AddressDao;
import de.booxware.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stas on 07/06/17.
 */
// maybe add some transactions or logging
@Service
public class AddressService {

    @Autowired
    private AddressDao addressDao;

//    @Autowired
//    private AddressDaoStub addressDao;

    @Autowired
    private GoogleService googleService;

    public boolean saveNewPosition(String userAddress) {
        try {
            Address fullAddress = googleService.getFullAddress(userAddress);
            addressDao.save(fullAddress);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public List<Address> getAllAddresses() {
        return addressDao.findAll();
    }




}
