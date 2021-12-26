package itunes.tests;

import io.qameta.allure.Description;
import itunes.models.TVShowData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static itunes.filters.CustomLogFilter.customLogFilter;
import static itunes.tests.Specs.request;
import static itunes.tests.Specs.responseSpec;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.Matchers.is;

public class TVShowSuite extends Testbase {

    @Test
    @DisplayName("Successful search")
    @Description("Search for 'Devs' tv show in")
    public void positiveSearch() {
        TVShowData data = given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .queryParam("term", "Devs")
                .queryParam("media", "tvShow")
                .when()
                .get("")
                .then()
                .spec(responseSpec)
                .extract().as(TVShowData.class);

        assertEquals(1499138726, data.getResults().get(0).getArtistId());
        assertEquals("Devs", data.getResults().get(0).getArtistName());
        assertEquals("Lily enlists ex-boyfriend Jamie’s help to " +
                        "investigate Sergei’s disappearance. She begins to question",
                data.getResults().get(0).getShortDescription());
    }

    @Test
    @DisplayName("Search items amount")
    @Description("Check if search returns correct amount")
    public void resultsAmount() {
        TVShowData data = given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .queryParam("term", "Devs")
                .queryParam("media", "tvShow")
                .when()
                .get("")
                .then()
                .spec(responseSpec)
                .extract().as(TVShowData.class);

        assertEquals(9, data.getResultCount());
    }

    @Test
    @DisplayName("Search with collection name")
    @Description("Check if collectionName parameter works")
    public void collectionName() {
        TVShowData data = given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .queryParam("term", "Mr. Robot")
                .queryParam("collectionName", "Mr. Robot, Season 1")
                .queryParam("media", "tvShow")
                .when()
                .get("")
                .then()
                .spec(responseSpec)
                .extract().as(TVShowData.class);

        assertEquals("MR. ROBOT follows Elliot, a cyber-security engineer by day and " +
                        "vigilante hacker by night, who gets",
                data.getResults().get(2).getShortDescription());
    }

    @Test
    @DisplayName("Search for TV show which does not exist")
    @Description("Empty search result")
    public void emptyResult() {
        TVShowData data = given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .queryParam("term", "123d")
                .queryParam("media", "tvShow")
                .when()
                .get("")
                .then()
                .spec(responseSpec)
                .extract().as(TVShowData.class);

        assertEquals(0, data.getResultCount());
        assertTrue(data.getResults().isEmpty());
    }

    @Test
    @DisplayName("Result limit")
    @Description("Check if result limit works")
    public void resultLimitation() {
        TVShowData data = given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .queryParam("term", "Fargo")
                .queryParam("media", "tvShow")
                .queryParam("limit", 12)
                .when()
                .get("")
                .then()
                .spec(responseSpec)
                .extract().as(TVShowData.class);

        assertEquals(12, data.getResultCount());
    }

    @Test
    @DisplayName("Invalid media type search")
    @Description("Check error message when media type is incorrect")
    public void invalidMediaType() {
        given()
                .spec(request)
                .filter(customLogFilter().withCustomTemplates())
                .queryParam("term", "Fargo")
                .queryParam("media", "some random media type")
                .when()
                .get("")
                .then()
                .statusCode(400)
                .body("errorMessage", is("Invalid value(s) for key(s): [mediaType]"));
    }
}
