package com.example.redirectserver.controller;

import com.example.redirectserver.service.AuthService;
import com.example.redirectserver.util.Client;
import com.example.redirectserver.util.URLS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class RedirectController {
    @Autowired
    Client client;
    @Autowired
    AuthService service;

    @GetMapping("/")
    public ResponseEntity<Object> getAnyThing(@RequestParam Map<String, String> params,
                                              @RequestHeader Map<String, String> headers,
                                              @RequestBody(required = false) Optional<Object> body) {
        System.out.println ("Headers:\n" + headers);
        System.out.println ("PARAMS:\n" + params);
        System.out.println ("BODY:\n" + body);
        int code = service.validate (headers);
        if (code == 200) {
            return getAnyThings (params, headers, body);
        } else
            return ResponseEntity.status (code).build ();
    }

    @GetMapping("/a")
    public ResponseEntity<Object> getAnyThings(@RequestParam Map<String, String> params,
                                               @RequestHeader Map<String, String> headers,
                                               @RequestBody(required = false) Optional<Object> body) {
        StringBuilder sb = new StringBuilder ();
        sb.append ("HEADERS:\n");
        headers.forEach ((k, v) -> sb.append (k + " " + v).append ("\n"));
        sb.append ("PARAMS:\n");
        params.forEach ((k, v) -> sb.append (k + " " + v).append ("\n"));
        sb.append ("BODY:\n");
        sb.append (body);
        return ResponseEntity.ok (sb.toString ());
    }

    private ResponseEntity exchange(String url, Optional<Object> body) {
        System.out.println (url);
        System.out.println (body.get ());
        return (client.exchangeWithBody (url, body.get ()));
    }
}
