package dev.abel.springbootredis.controller;

import dev.abel.springbootredis.domain.Roulette;
import dev.abel.springbootredis.domain.Bet;
import dev.abel.springbootredis.repository.BetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

@RestController
public class BetController {
    private String ROULETTE_KEY ="Roulette";
    private String BET_KEY ="Bet";

    private BetRepository Repository;
    public BetController(BetRepository betRepository) {
        this.Repository = betRepository;
    }
    @PostMapping("/roulette")
    public String createRoulette(@RequestBody Roulette ruleta) {
        String idRoulette = java.util.UUID.randomUUID().toString();
        ruleta.setId(idRoulette);
        ruleta.setName("Roulette_" + idRoulette);
        Repository.save(ruleta,ROULETTE_KEY, idRoulette);
        return "Roulette : "+ idRoulette+ " create SATISFACTORY!";
    }
    @GetMapping("/roulettes")
    public Map<String, Object> findAllRoulette() {
        return Repository.findAll(ROULETTE_KEY);
    }
    @PutMapping("/openroulette/{id}")
    public String openRoulette(@PathVariable String id){
        String Msg="";
        try {
            Roulette roulette = (Roulette) Repository.findById(id,ROULETTE_KEY);
            if(roulette.getState().equals("Open")){
                Msg = "DENY, state = Open";
            }else{
                roulette.setState("Open");
                Repository.save(roulette, ROULETTE_KEY,id);
                Msg = "SATISFACTORY";
            }
            return "Open Status roulette "+ id + " : "+ Msg;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Roulette width id "+id+" NO EXIST", ex);
        }
    }
    @GetMapping("/bets")
    public Map<String, Object> findAllBet() {
        return Repository.findAll(BET_KEY);
    }
    @PatchMapping ("/closeBet/{idRoulette}")
    public Object closeBet(@PathVariable String idRoulette){
        Object response;
        try {
            Roulette roulette = (Roulette) Repository.findById(idRoulette, ROULETTE_KEY);
            response = changeState(roulette);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Roulette width id "+idRoulette+" NO EXIST", e);
        }
        return response;
    }
    @GetMapping("/ruleta/{id}")
    public Object findById(@PathVariable String id) {
        return Repository.findById(id,ROULETTE_KEY);
    }

    @PostMapping("/bet")
    public String createBetRepository(@RequestBody Bet bet) {
        String idRoulette = bet.getIdRoulette();
        try {
            Roulette roulette = (Roulette) Repository.findById(idRoulette,ROULETTE_KEY);
            return createBet(bet,roulette);
        }catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Roulette width id "+idRoulette+" NO EXIST", ex);
        }
    }

    @DeleteMapping("/delrepository/{id}")
    public void delete(@PathVariable String id) {
        Repository.delete(id);
    }
    public String createBet(Bet bet, Roulette roulette){
        String Msg="";
        if(roulette.getState().equals("Open")){
            String UUID = java.util.UUID.randomUUID().toString();
            bet.setId(UUID);
            Repository.save(bet,BET_KEY, UUID);
            Msg="Bet create SATISFACTORY";
        }else{
            Msg="Cant create Bet, Roulette is "+roulette.getState();
        }
        return Msg;
    }
    public Object changeState(Roulette roulette){
        if(roulette.getState().equals("Open")){
            roulette.setState("Close");
            Repository.save(roulette, ROULETTE_KEY, roulette.getId());
            return Repository.getBetsRoulette(roulette.getId());
        }else{
            return "Roulette is Close";
        }
    }
}
