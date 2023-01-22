package br.com.lucasromagnoli.workaround.tictoe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateGameRequestDTO {

    @JsonProperty("player1_id")
    private String player1Id;

    @JsonProperty("player2_id")
    private String player2Id;
}
