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
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    Client client;
    @Autowired
    AuthService service;

    @GetMapping("/")
    public ResponseEntity<Object> getOrder(@RequestParam Map<String, String> params,
                                           @RequestHeader Map<String, String> headers,
                                           @RequestBody(required = false) Optional<Object> body) {
        int code = service.validate (headers);
        if (code == 200) {
            return client.exchangeWithBody (URLS.ORDER_URL, body);
        } else
            return ResponseEntity.status (code).build ();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@RequestParam Map<String, String> params,
                                               @RequestHeader Map<String, String> headers,
                                               @RequestBody(required = false) Optional<Object> body,
                                               @PathVariable int id) {
        int code = service.validate (headers);
        if (code == 200) {
            return client.exchangeWithBody (URLS.ORDER_URL + "/" + id, body);
        } else
            return ResponseEntity.status (code).build ();
    }
    @PostMapping("/")
    public ResponseEntity<Object> addOrder(@RequestParam Map<String, String> params,
                                           @RequestHeader Map<String, String> headers,
                                           @RequestBody(required = false) Optional<Object> body) {
        int code = service.validate (headers);
        if (code == 200) {
            return client.exchangeWithBody (URLS.ORDER_URL, body);
        } else
            return ResponseEntity.status (code).build ();
    }
}
