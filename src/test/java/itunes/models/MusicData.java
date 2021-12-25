package itunes.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicData {

    @JsonProperty("resultCount")
    private Integer resultCount;

    @JsonProperty("results")
    private List<Music> results;
}
