package itunes.tests;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;

public class Testbase {
    @BeforeAll
    static void setup() {
        RestAssured.registerParser("text/javascript", Parser.JSON);
    }
}
