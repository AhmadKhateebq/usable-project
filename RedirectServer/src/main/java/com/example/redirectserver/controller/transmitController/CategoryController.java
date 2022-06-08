package com.example.redirectserver.controller.transmitController;

import com.example.redirectserver.service.AuthService;
import com.example.redirectserver.util.Client;
import com.example.redirectserver.util.URLS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    Client client;
    @Autowired
    AuthService service;

    @GetMapping("/")
    public ResponseEntity<Object> getCategory(@RequestParam Map<String, String> params,
                                              @RequestHeader Map<String, String> headers,
                                              @RequestBody(required = false) Optional<Object> body) {
        int code = service.validate (headers);
        if (code == 200) {
            return client.exchangeWithBody (URLS.CATEGORY_URL, body);
        } else
            return ResponseEntity.status (code).build ();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@RequestParam Map<String, String> params,
                                                  @RequestHeader Map<String, String> headers,
                                                  @RequestBody(required = false) Optional<Object> body, @PathVariable int id) {
        int code = service.validate (headers);
        if (code == 200) {
            return client.exchangeWithBody (URLS.CATEGORY_URL + "/" + id, body);
        } else
            return ResponseEntity.status (code).build ();
    }
}
