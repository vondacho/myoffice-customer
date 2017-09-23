package edu.noia.myoffice.customer.ui.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static edu.noia.myoffice.customer.ui.rest.UiResource.UI_ENDPOINT_PATH;
import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = UI_ENDPOINT_PATH)
public class UiResource {

    public static final String UI_ENDPOINT_PATH = "/hello";

    @GetMapping
    public ResponseEntity create(@RequestParam(name = "who", required = false) String who) {
        return ok(Optional.ofNullable(who)
                .map(w -> "Hello " + w + "!")
                .orElse("Hello world!"));
    }
}
