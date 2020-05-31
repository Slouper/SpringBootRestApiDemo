package cz.adastra.integration;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static java.lang.String.format;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import cz.adastra.app.SpringDemoApplication;
import cz.adastra.data.Person;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = SpringDemoApplication.class)
class IntegrationTest {
  private static final String baseUrl = "http://localhost:%s";

  @Value("${server.port}")
  private String serverPort;

  @Test
  void personsResourceTest() {
    List<Person> persons =
        Arrays.asList(get(getBaseUrl() + "/persons").as(Person[].class));

    if (persons.isEmpty()) {
      throw new AssertionError("Is expected that at least one person is returned, but empty collection retrieved.");
    }
  }

  @Test
  void personsByNameResourceTest() {
    List<Person> persons =
        Arrays.asList(
            given()
                .queryParam("name", "Petr")
                .get(getBaseUrl() + "/persons")
                .as(Person[].class));

    if (persons.isEmpty()) {
      throw new AssertionError("Is expected that at least one person is returned, but empty collection retrieved.");
    }
  }

  @Test
  void createPersonTest() {
    Person person = new Person();
    String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    person.setName("UnitTestName" + currentDateTime);
    person.setSurname("UnitTestSurName" + currentDateTime);

    Response response = given()
        .log()
        .all()
        .contentType(ContentType.JSON)
        .body(person)
        .post(getBaseUrl() + "/persons/create");

    System.out.println(response.asString());

    response
        .then()
        .assertThat()
        .statusCode(201);
  }

  private String getBaseUrl() {
    return format(baseUrl, serverPort);
  }
}