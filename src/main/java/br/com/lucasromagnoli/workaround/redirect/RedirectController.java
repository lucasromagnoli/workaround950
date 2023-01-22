package br.com.lucasromagnoli.workaround.redirect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/")
public class RedirectController {

    private static final Map<String, RedirectMetadata> SHORTED_URL = new HashMap<>();

    static {
        SHORTED_URL.put("blau2y", new RedirectMetadata("blau2y", "https://google.com"));
        SHORTED_URL.put("vasdc2", new RedirectMetadata("vasdc2", "https://youtube.com"));
        SHORTED_URL.put("jkasd7", new RedirectMetadata("jkasd7", "https://twitter.com"));
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/r/{hash}")
    public RedirectView redirect(@PathVariable("hash") String hash) {
        return new RedirectView(SHORTED_URL.get(hash).endpoint());
    }

    @GetMapping()
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok(SHORTED_URL.entrySet().stream().map(stringRedirectMetadataEntry -> {
            String key = stringRedirectMetadataEntry.getKey();
            RedirectMetadata value = stringRedirectMetadataEntry.getValue();

            return String.format("<ul>%s<li>%s</li></ul>", key, value);
        }).collect(Collectors.joining()));
    }

    @PostMapping("/i")
    public RedirectView insert(@RequestBody String endpoint) {
        String hash = randomUUID().toString().substring(0, 5);
        SHORTED_URL.put(hash, new RedirectMetadata(hash, endpoint));
        return new RedirectView("http://localhost:8080/");
    }

    @GetMapping("/principal")
    public RedirectView principal() {
        String token = randomUUID().toString();
        RedirectView redirectView = new RedirectView("/valida-token?token=" + token, true);
        redirectView.setPropagateQueryParams(true);
        return redirectView;
    }

    @GetMapping("/valida-token")
    public RedirectView validaToken(@RequestParam("token") String token, @RequestParam("callback") String callback) {
        return new RedirectView(callback + "?token-valido=true&token=" + token, false);
    }
}
