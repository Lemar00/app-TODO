package cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import sn.ept.git.seminaire.cicd.dto.TagDTO;
import sn.ept.git.seminaire.cicd.dto.vm.TagVM;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.fail;






public class TagResourceSteps {


    private ResponseEntity<TagDTO> response;
    private ResponseEntity<String> responseString;


    private ResponseEntity<Map<String, List<TagDTO>>> responseList;
    private List<TagDTO> tags;
    private UUID tagId;
    private TagDTO retrievedTag;

    private List<TagDTO> retrievedTags;




    private RestTemplate restTemplate = new RestTemplate();
    private String baseUrl = "http://localhost:8080/cicd/api/tags";
    private String tagName;
    private Map<String, Object> tagListResponse;

    @Given("the user is on the TagResource endpoint")
    public void the_user_is_on_the_TagResource_endpoint() {
        
    }

    @When("the user adds a new tag")
    public void the_user_adds_a_new_tag() {
        tagName = "Nom_Tag-" + System.currentTimeMillis();

        
        TagVM tagVM = new TagVM();
        tagVM.setName(tagName);
        tagVM.setDescription("Description du nouveau tag");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        this.response = restTemplate.postForEntity(baseUrl, tagVM, TagDTO.class);;

        tagId = response.getBody().getId();
    }

    @Then("the response should contain the newly created tag")
    public void the_response_should_contain_the_newly_created_tag() {
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        TagDTO createdTag = response.getBody();
        assertNotNull(createdTag);
        assertNotNull(createdTag.getId());
    }

    @When("the user deletes the newly created tag")
    public void the_user_deletes_the_newly_created_tag() {
        TagDTO createdTag = response.getBody();
        assertNotNull(createdTag);
        tagId = createdTag.getId();

        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                baseUrl + "/" + tagId,
                HttpMethod.DELETE,
                null,
                Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());

    }

    @Then("the tag should be deleted from the system")
    public void the_tag_should_be_deleted_from_the_system() {
        try {
            restTemplate.getForEntity(baseUrl + "/" + tagId, TagDTO.class);
            fail("Expected HttpClientErrorException to be thrown");
        } catch (HttpClientErrorException ex) {
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        }
    }

    @When("the user requests a tag by its ID")
    public void the_user_requests_a_tag_by_its_ID() {
        the_user_adds_a_new_tag(); 
       retrievedTag = restTemplate.getForObject(baseUrl + "/" + tagId, TagDTO.class);
        assertNotNull(retrievedTag);
    }

    @Then("the response should contain the tag with the specified ID")
    public void the_response_should_contain_the_tag_with_the_specified_ID() {
        assertEquals(tagId, retrievedTag.getId());

    }


    @When("the user updates the tag")
    public void the_user_updates_the_tag() {
        TagVM updatedTag = new TagVM();
        updatedTag.setName("Nouveau nom du Tag" + System.currentTimeMillis());
        updatedTag.setDescription("Nouvelle description du tag");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<TagDTO> updatedResponse = restTemplate.exchange(
                baseUrl + "/" + tagId,
                HttpMethod.PUT,
                new HttpEntity<>(updatedTag, headers),
                TagDTO.class
        );

        this.response = updatedResponse;
    }

    @Then("the response should contain the updated tag")
    public void the_response_should_contain_the_updated_tag() {
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        TagDTO updatedTag = response.getBody();
        assertNotNull(updatedTag);

    }


    @When("the user requests all tags")
    public void the_user_requests_all_tags() {
        responseString = restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class);
    }

    @Then("the response should have a status code of 200 OK")
    public void the_response_should_contain_a_list_of_tags() {
        assertEquals(HttpStatus.OK, responseString.getStatusCode());
    }

    @Then("the response should contain a non-empty list of tags")
    public void the_response_should_contain_non_empty_list_of_tags() {
        assertFalse(responseString.getBody().isEmpty());
    }


    private HttpStatus httpStatus;
    @When("the user tries to create a new tag with invalid data")
    public void the_user_tries_to_create_a_new_tag_with_invalid_data() {
        TagVM tagVM = new TagVM();
        tagVM.setName(""); 
        tagVM.setDescription("Description du nouveau tag");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            this.response = restTemplate.postForEntity(baseUrl, tagVM, TagDTO.class);
            this.httpStatus = response.getStatusCode();
        } catch (HttpClientErrorException e) {
            this.httpStatus = e.getStatusCode();
        }

    }

    @Then("the response should indicate a validation error")
    public void the_response_should_indicate_a_validation_error() {
        assertEquals(HttpStatus.BAD_REQUEST, httpStatus);
        assertNull(response);

    }


    private HttpStatus responseStatus;
    private String errorMessage;
    @When("the user adds a new tag with the name {string}")
    public void the_user_adds_a_new_tag_with_an_existing_name(String tagName) {
        TagVM tagVM = new TagVM();
        tagVM.setName(tagName);
        tagVM.setDescription("Description du nouveau tag");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            restTemplate.postForEntity(baseUrl, tagVM, TagDTO.class);
        } catch (HttpClientErrorException e) {
            responseStatus = e.getStatusCode();
            errorMessage = e.getResponseBodyAsString();
        }
    }

    @Then("the response should contain a conflict status")
    public void the_response_should_contain_a_conflict_status() {
        assertEquals(HttpStatus.CONFLICT, responseStatus);
    }

    @And("the response should contain an error message")
    public void the_response_should_contain_an_error_message() {
        assertNotNull(errorMessage);
    }



}
