package com.example.justransform

/**
 * HttpResponse object to hold the response status and body of an API Request
 */
class HttpResponse {

    private int status
    private String body

    HttpResponse(status, body) {
        this.status = status
        this.body = body
    }

    int getStatus() {
        return status
    }

    String getBody() {
        return body
    }
}
