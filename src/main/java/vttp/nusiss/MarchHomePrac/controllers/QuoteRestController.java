package vttp.nusiss.MarchHomePrac.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.nusiss.MarchHomePrac.services.QuoteService;

@RestController
@RequestMapping(path="/quote",produces=MediaType.APPLICATION_JSON_VALUE)
public class QuoteRestController {
    
    @Autowired
    private QuoteService quoteSvc;

    @GetMapping
    public ResponseEntity<String>getQuote(
    @RequestHeader(name="X-ID", required=false)String id,
    @RequestParam(name="count",defaultValue="1") Integer count
    ){
        Collection<String> quotes = quoteSvc.getQuotes(count);
        
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        quotes.stream()
        .filter((String q) -> {
            return q.length() >3;
        })
        .map((String q) ->{
            return Json.createObjectBuilder()
            .add("quote", q)
            .add("timestamp", System.currentTimeMillis())
            .build();
        })
        .forEach((JsonObject o)->{
            arrBuilder.add(o);
        });

        JsonArray quoteArray = arrBuilder.build();


        return ResponseEntity.ok()
        .header("X-ID",id)
        .body(quoteArray.toString());


        
    }




}
