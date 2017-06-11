package de.booxware.service;

import de.booxware.domain.Address;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by stas on 10/06/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AddressServiceTest {
    private static final String DUPLICATE_PLACE = "Moscow, Russia";

    @Autowired
    AddressService addressService;

    @Test
    public void getAllAddresses() throws Exception {
        List<Address> allAddresses = addressService.getAllAddresses();
        allAddresses
                .stream()
                .filter(address -> address.getAddressStr().equals(DUPLICATE_PLACE))
                .findFirst()
                .ifPresent(address -> {
                    int lat = address.getLatitude().intValue();
                    int lon = address.getLongitude().intValue();
                    assertEquals(lat, 55);
                    assertEquals(lon, 37);
                });
    }

    @Test
    public void saveBadNewPosition() throws Exception {
        boolean b = addressService.saveNewPosition(DUPLICATE_PLACE);
        assertEquals(false, b);
    }

}