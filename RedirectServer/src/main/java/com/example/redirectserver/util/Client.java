package com.example.redirectserver.util;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

import org.springframework.http.*;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


public class Client {

    private final RestTemplate restTemplate;

    public Client() {
        restTemplate = new RestTemplate (new CustomHttpComponentsClientHttpRequestFactory ());
    }

    public ResponseEntity exchangeWithBody(String url, Object requestBody) {
        return
                restTemplate.exchange (
                        url,
                        HttpMethod.GET,
                        new HttpEntity<> (requestBody),
                        String.class
                );
    }

    public ResponseEntity exchangeWithHeader(String url, Object requestBody) {
        HttpHeaders headers = new HttpHeaders ();
        headers.setAccept (Collections.singletonList (MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<> ("body", headers);
        return restTemplate.exchange (url, HttpMethod.POST, entity, String.class);
    }

    public ResponseEntity exchangeWithHeaders(String url, Map<String, String> headers) {
        return restTemplate.exchange (
                url,
                HttpMethod.POST,
                new HttpEntity<String> (createHeaders (headers)),
                String.class
        );
    }

    public static final class CustomHttpComponentsClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {
        @Override
        protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
            if (HttpMethod.GET.equals (httpMethod)) {
                return new HttpEntityEnclosingGetRequestBase (uri);
            }
            return super.createHttpUriRequest (httpMethod, uri);
        }
    }

    private static final class HttpEntityEnclosingGetRequestBase extends HttpEntityEnclosingRequestBase {

        public HttpEntityEnclosingGetRequestBase(final URI uri) {
            super.setURI (uri);
        }

        @Override
        public String getMethod() {
            return HttpMethod.GET.name ();
        }
    }

    HttpHeaders createHeader(String headerName, String header) {
        return new HttpHeaders () {{
            set (headerName, header);
        }};
    }

    HttpHeaders createHeaders(Map<String, String> map) {
        return new HttpHeaders () {{
            setAll (map);
        }};
    }
}