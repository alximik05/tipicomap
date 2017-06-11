package de.booxware.controlers;

import com.google.gson.Gson;
import com.sun.tools.javac.code.Attribute;
import de.booxware.domain.Address;
import de.booxware.service.AddressService;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by stas on 09/06/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    private static final List<Address> ADDRESS_LIST = new ArrayList<>();
    static {
        ADDRESS_LIST.add(new Address("Lugovoi", 10.00000000, 20.00000000));
        ADDRESS_LIST.add(new Address("Red Square", 30.00000000 , 40.00000000));
        ADDRESS_LIST.add(new Address("Kutuzovskaya", 50.00000000 ,60.00000000));
    }

    @Test
    public void loadAllAddresses() throws Exception {
        when(addressService.getAllAddresses()).thenReturn(ADDRESS_LIST);
        String addresses = addressController.loadAllAddresses();
        verify(addressService).getAllAddresses();
        assertThat(ADDRESS_LIST.size(), is(3));
        Gson gson = new Gson();
        List list = gson.fromJson(addresses, List.class);
        list.forEach(System.out::println);
        assertThat(ADDRESS_LIST.get(0).getAddressStr(), is("Lugovoi"));
        assertThat(ADDRESS_LIST.get(2).getLongitude(), is(60.00000000));
    }

}