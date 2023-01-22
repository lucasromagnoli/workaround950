package br.com.lucasromagnoli.workaround.tictoe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerActionRequestDTO {
    @JsonProperty("player_id")
    private String id;

    @JsonProperty("board_position")
    private String position;
}
