package de.booxware.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import de.booxware.domain.Address;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by stas on 09/06/17.
 */
@Service
public class GoogleService {

    private static final String API_KEY = "AIzaSyC7BsGIH4wjU6x2YfNPGDGF3MJRUiplxjI";
    private static final GeoApiContext GEO_API_CONTEXT = new GeoApiContext().setApiKey(API_KEY);

    Address getFullAddress(String userAddress) {
        try {
            GeocodingResult[] results = GeocodingApi.geocode(GEO_API_CONTEXT, userAddress).await();
            if (results.length != 0) {
                GeocodingResult result = results[0];
                return new Address(result.formattedAddress, result.geometry.location.lat, result.geometry.location.lng);
            } else {
                throw new RuntimeException("Address : " + userAddress + " didn't find in Google Maps");
            }
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return new Address("default", 00.00000000,00.00000000);
    }
}
