package itunes.tests;

import io.qameta.allure.Description;
import itunes.models.MusicData;
import itunes.models.TVShowData;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static itunes.filters.CustomLogFilter.customLogFilter;
import static itunes.tests.Specs.request;
import static itunes.tests.Specs.responseSpec;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MusicSuite extends Testbase{
    @Test
    @Description("Search for song")
    public void searchSong() {
        MusicData data = given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .queryParam("term", "tapping out")
                .queryParam("media", "music")
                .when()
                .get("")
                .then()
                .spec(responseSpec)
                .extract().as(MusicData.class);

        assertEquals("Issues", data.getResults().get(0).getArtistName());
        assertEquals("Rock", data.getResults().get(0).getPrimaryGenreName());
        assertEquals("Tapping Out - Single", data.getResults().get(0).getCollectionName());

    }

    @Test
    @Description("Search for album")
    public void searchAlbum() {
        TVShowData data = given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .queryParam("term", "Dirt")
                .queryParam("media", "music")
                .queryParam("entity", "album")
                .when()
                .get("")
                .then()
                .spec(responseSpec)
                .extract().as(TVShowData.class);

        assertEquals("Alice In Chains", data.getResults().get(0).getArtistName());
        assertEquals("Hard Rock", data.getResults().get(0).getPrimaryGenreName());
        assertEquals(13, data.getResults().get(0).getTrackCount());
    }

    @Test
    @Description("Check if result limit works")
    public void resultLimitation() {
        TVShowData data = given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .queryParam("term", "Dirt")
                .queryParam("media", "music")
                .queryParam("entity", "album")
                .queryParam("limit", 22)
                .when()
                .get("")
                .then()
                .spec(responseSpec)
                .extract().as(TVShowData.class);

        assertEquals(22, data.getResultCount());
    }

    @Test
    @Description("Search for artist who doesn't exist")
    public void noResults() {
        TVShowData data = given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .queryParam("term", "some random artist name")
                .queryParam("media", "music")
                .when()
                .get("")
                .then()
                .spec(responseSpec)
                .extract().as(TVShowData.class);

        assertEquals(0, data.getResultCount());
        assertTrue(data.getResults().isEmpty());
    }
}
