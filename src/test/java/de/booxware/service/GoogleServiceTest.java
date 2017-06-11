package de.booxware.service;

import de.booxware.domain.Address;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by stas on 10/06/17.
 */
public class GoogleServiceTest {

    private static final GoogleService GOOGLE_SERVICE = new GoogleService();
    public static final String MOSCOW_CITY = "Moscow";
    private static final String MOSCOW_CITY_RUS = "Москва";
    private static final String STREET = "Tverskaya";
    private static final String BAD_ADDRESS = "jfksbjksfn4";

    @Test
    public void moscowCity() throws Exception {
        checker(MOSCOW_CITY);
    }

    @Test
    public void inRussianLanguage() throws Exception {
        checker(MOSCOW_CITY_RUS);
    }

    @Test
    public void streetInMoscow() throws Exception {
        checker(STREET);
    }

    @Test(expected = RuntimeException.class)
    public void getBadFullAddress() throws Exception {
        checker(BAD_ADDRESS);
    }


    private void checker(String placeInMoscow) {
        Address fullAddress = GOOGLE_SERVICE.getFullAddress(placeInMoscow);
        assertThat(fullAddress.getAddressStr(), notNullValue());
        int lat = fullAddress.getLatitude().intValue();
        int lon = fullAddress.getLongitude().intValue();
        assertEquals(lat, 55);
        assertEquals(lon, 37);
    }

}