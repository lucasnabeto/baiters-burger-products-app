package br.com.fiap.baitersburger.products.adapters.in.controller;

import br.com.fiap.baitersburger.products.BaitersBurgerProductsApiApplication;
import br.com.fiap.baitersburger.products.adapters.in.controller.dto.ProductRequestDto;
import br.com.fiap.baitersburger.products.core.domain.model.Category;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BaitersBurgerProductsApiApplication.class)
@ActiveProfiles("test")
public class InsertProductStepsDefinition {

    private final TestRestTemplate testRestTemplate;

    private ProductRequestDto dto;
    private ResponseEntity<Void> response;

    public InsertProductStepsDefinition(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    @Given("that I own a product with the following characteristics")
    public void givenThatIOwnAProductWithTheFollowingCharacteristics(DataTable dataTable) {
        Map<String, String> productData = dataTable.asMaps().getFirst();

        dto = new ProductRequestDto(productData.get("productName"),
                Category.fromString(productData.get("category")),
                new BigDecimal(productData.get("price")),
                productData.get("description"),
                Arrays.stream(productData.get("imagesUrls").split(",")).toList());
    }

    @When("I make a POST request to the API")
    public void whenIMakeAPOSTRequestToTheAPI() {
        var request = new HttpEntity<>(dto);
        response = testRestTemplate.exchange("/api/v1/products",
                HttpMethod.POST,
                request,
                Void.class);
    }

    @Then("the product should be successfully registered in the database")
    public void thenTheProductShouldBeSuccessfullyRegisteredInTheDatabase() {
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
