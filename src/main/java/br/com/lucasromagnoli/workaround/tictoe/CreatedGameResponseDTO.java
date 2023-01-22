package br.com.lucasromagnoli.workaround.tictoe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class CreatedGameResponseDTO {

    @JsonProperty("game_id")
    private UUID id;

    public static CreatedGameResponseDTO from(Game game) {
        return CreatedGameResponseDTO.builder()
                .id(game.getId())
                .build();
    }
}
