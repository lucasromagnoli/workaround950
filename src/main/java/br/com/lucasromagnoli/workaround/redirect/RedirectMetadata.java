package br.com.lucasromagnoli.workaround.redirect;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
public class RedirectMetadata {
    private final String key;
    private final String endpoint;
    private Long clicks = 0L;

    public String endpoint() {
        this.clicks++;
        return this.endpoint;
    }

    @Override
    public String toString() {
        return "RedirectMetadata{" +
                "endpoint='<a href=\"http://localhost:8080/r/" + key + "\" target=\"_blank\">"+endpoint+"</a>" +
                ", clicks=" + clicks +
                '}';
    }
}
