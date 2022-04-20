package tasks.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tasks.task6Classes.Product;

@Controller
public class ControllerTask6 {
        @PostMapping("/get-id-product")
    public ResponseEntity<String> addIdInInfo(@RequestBody String gotString)  {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Product product;
        try {
            product = gson.fromJson(gotString, Product.class);
            product.createProductId();
        }
        catch (Exception e) {
            return new ResponseEntity<>("Incorrect data", HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(gson.toJson(product), HttpStatus.OK);
    }
}

