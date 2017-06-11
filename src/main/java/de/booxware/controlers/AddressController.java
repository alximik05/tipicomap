package de.booxware.controlers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import de.booxware.domain.Address;
import de.booxware.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by stas on 07/06/17.
 */
@Controller
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/")
    public String start() {
        return "startPage.html";
    }

    @RequestMapping(value = "/saveAddress", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String saveNewLocation(@RequestParam(required = false, name = "address") String address) {
        boolean isSave = addressService.saveNewPosition(address);
        Gson gson = new Gson();
        JsonElement jsonElement = new JsonPrimitive(isSave);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("successError", jsonElement);
        return gson.toJson(jsonObject);
    }

    @GetMapping("/loadAllAddresses")
    @ResponseBody
    public String loadAllAddresses() {
        List<Address> allAddresses = addressService.getAllAddresses();
        Object[] objects = allAddresses.toArray();
        Gson gson = new Gson();
        return gson.toJson(objects);
    }

}
