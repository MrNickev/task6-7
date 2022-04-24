package tasks.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@Controller
public class HeaderControllerTask6 {
    @GetMapping("/get-request-headers")
    public ResponseEntity<String> getHeaders(@RequestHeader Map<String, String> headers)  {
        var jsonArr = new JsonArray();
        try {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                var item = new JsonObject();
                item.addProperty("Header " + entry.getKey(), entry.getValue());
                jsonArr.add(item);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>("Incorrect data", HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(jsonArr.toString(), HttpStatus.OK);
    }
}
