package itunes.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Music {

    @JsonProperty("wrapperType")
    private String wrapperType;
    @JsonProperty("kind")
    private String kind;
    @JsonProperty("artistId")
    private Integer artistId;
    @JsonProperty("collectionId")
    private Integer collectionId;
    @JsonProperty("trackId")
    private Integer trackId;
    @JsonProperty("artistName")
    private String artistName;
    @JsonProperty("collectionName")
    private String collectionName;
    @JsonProperty("trackName")
    private String trackName;
    @JsonProperty("collectionCensoredName")
    private String collectionCensoredName;
    @JsonProperty("trackCensoredName")
    private String trackCensoredName;
    @JsonProperty("artistViewUrl")
    private String artistViewUrl;
    @JsonProperty("collectionViewUrl")
    private String collectionViewUrl;
    @JsonProperty("trackViewUrl")
    private String trackViewUrl;
    @JsonProperty("previewUrl")
    private String previewUrl;
    @JsonProperty("artworkUrl30")
    private String artworkUrl30;
    @JsonProperty("artworkUrl60")
    private String artworkUrl60;
    @JsonProperty("artworkUrl100")
    private String artworkUrl100;
    @JsonProperty("collectionPrice")
    private Double collectionPrice;
    @JsonProperty("trackPrice")
    private Double trackPrice;
    @JsonProperty("releaseDate")
    private String releaseDate;
    @JsonProperty("collectionExplicitness")
    private String collectionExplicitness;
    @JsonProperty("trackExplicitness")
    private String trackExplicitness;
    @JsonProperty("discCount")
    private Integer discCount;
    @JsonProperty("discNumber")
    private Integer discNumber;
    @JsonProperty("trackCount")
    private Integer trackCount;
    @JsonProperty("trackNumber")
    private Integer trackNumber;
    @JsonProperty("trackTimeMillis")
    private Integer trackTimeMillis;
    @JsonProperty("country")
    private String country;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("primaryGenreName")
    private String primaryGenreName;
    @JsonProperty("isStreamable")
    private Boolean isStreamable;
}
