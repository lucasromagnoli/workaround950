package br.com.lucasromagnoli.workaround.tictoe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GameDetailsResposneDTO {
    @JsonProperty("details")
    private String uglyDetails;

    public GameDetailsResposneDTO(Game game) {
        this.uglyDetails = game.toString();
    }
}
