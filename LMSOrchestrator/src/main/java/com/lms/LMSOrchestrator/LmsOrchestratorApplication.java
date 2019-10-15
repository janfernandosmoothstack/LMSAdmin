package com.lms.LMSOrchestrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.lms.LMSAdmin.pojo.Author;
import com.lms.LMSAdmin.pojo.Book;
import com.lms.LMSAdmin.pojo.Borrower;
import com.lms.LMSAdmin.pojo.LibraryBranch;
import com.lms.LMSAdmin.pojo.Publisher;


@SpringBootApplication
@RestController
@RequestMapping(value = "/LMSOrchestrator")

public class LmsOrchestratorApplication {
	
	@Autowired
	RestTemplate restTemp;
	
	private final String adminUri = "http://localhost:8070/LMSAdmin";
	
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LmsOrchestratorApplication.class, args);
	}
	
	
	/*
	 * Author Orchestrator
	 */
	
	//Create author
	@PostMapping(value = "/LMSAdmin/author/authorName/{authorName}", 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> insertAuthor(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable String authorName) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		return restTemp.exchange(adminUri + "/author/authorName/{authorName}", HttpMethod.POST, new HttpEntity<Object>(headers), 
				String.class, authorName);
	}
	
	//Update author
	@PutMapping(value = "/LMSAdmin/author/authorId/{authorId}/authorName/{authorName}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> updateAuthor(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable("authorId") Integer authorId, @PathVariable("authorName") String authorName) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/author/authorId/{authorId}/authorName/{authorName}", HttpMethod.PUT, 
					new HttpEntity<Object>(headers), String.class, authorId, authorName);
		} catch (HttpStatusCodeException e){
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
	               .body(e.getResponseBodyAsString());
		}
	}
	
	//Delete author
	@DeleteMapping(value = "/LMSAdmin/author/authorId/{authorId}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> deleteAuthor(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable("authorId") Integer authorId) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/author/authorId/{authorId}", HttpMethod.DELETE, 
					new HttpEntity<Object>(headers), String.class, authorId);
		} catch (HttpStatusCodeException e){
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
		            .body(e.getResponseBodyAsString());
		}
	}

	//View all authors
	@GetMapping(value = "/LMSAdmin/author", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Iterable<Author>> getAllAuthors(@RequestHeader("Accept") String accept) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		return restTemp.exchange(adminUri + "/author", HttpMethod.GET, new HttpEntity<Object>(headers), 
				new ParameterizedTypeReference<Iterable<Author>>(){});
	}
	
	
	/*
	 * Book Orchestrator
	 */
	
	//Create book
	@PostMapping(value = "/LMSAdmin/book/title/{title}/authId/{authId}/pubId/{pubId}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> insertBook(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable("title") String title, @PathVariable("authId") Integer authId, @PathVariable("pubId") Integer pubId) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/book/title/{title}/authId/{authId}/pubId/{pubId}", HttpMethod.POST, new HttpEntity<Object>(headers), 
					String.class, title, authId, pubId);
		} catch (HttpStatusCodeException e){
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
		            .body(e.getResponseBodyAsString());
		}
	}
	
	//Update book
	@PutMapping(value = "/LMSAdmin/book/bookId/{bookId}/title/{title}/authId/{authId}/pubId/{pubId}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> updateBook(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable("bookId") Integer bookId, @PathVariable("title") String title, 
			@PathVariable("authId") Integer authId, @PathVariable("pubId") Integer pubId) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/book/bookId/{bookId}/title/{title}/authId/{authId}/pubId/{pubId}", HttpMethod.PUT, 
					new HttpEntity<Object>(headers), String.class, bookId, title, authId, pubId);
		} catch (HttpStatusCodeException e){
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
	               .body(e.getResponseBodyAsString());
		}
	}
	
	//Delete book
	@DeleteMapping(value = "/LMSAdmin/book/bookId/{bookId}", 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> deleteBook(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable("bookId") Integer bookId) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/book/bookId/{bookId}", HttpMethod.DELETE, 
					new HttpEntity<Object>(headers), String.class, bookId);
		} catch (HttpStatusCodeException e){
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
		            .body(e.getResponseBodyAsString());
		}
	}
	
	//View all books
	@GetMapping(value = "/LMSAdmin/book", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Iterable<Book>> getAllBooks(@RequestHeader("Accept") String accept) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		return restTemp.exchange(adminUri + "/book", HttpMethod.GET, new HttpEntity<Object>(headers), 
				new ParameterizedTypeReference<Iterable<Book>>(){});
	}
	
	
	/*
	 * Borrower Orchestrator
	 */
	
	//Create borrower
	@PostMapping(value = "/LMSAdmin/borrower/borrName/{name}/borrAddress/{address}/borrPhone/{phone}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> insertBorr(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable("name") String borrName, @PathVariable("address") String borrAddress, @PathVariable("phone") String borrPhone) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		return restTemp.exchange(adminUri + "/borrower/borrName/" + borrName + "/borrAddress/" + borrAddress + "/borrPhone/" + borrPhone, 
				HttpMethod.POST, new HttpEntity<Object>(headers), String.class);
	}
	
	//Update borrower
	@PutMapping(value = "/LMSAdmin/borrower/cardNo/{cardNo}/borrName/{name}/borrAddress/{address}/borrPhone/{phone}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> updateBorr(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable("cardNo") Integer cardNo, @PathVariable("name") String borrName, 
			@PathVariable("address") String borrAddress, @PathVariable("phone") String borrPhone) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/borrower/cardNo/{cardNo}/borrName/{name}/borrAddress/{address}/borrPhone/{phone}", HttpMethod.PUT, 
					new HttpEntity<Object>(headers), String.class, cardNo, borrName, borrAddress, borrPhone);
		} catch (HttpStatusCodeException e){
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
	               .body(e.getResponseBodyAsString());
		}
	}
	
	//Delete borrower
	@DeleteMapping(value = "/LMSAdmin/borrower/cardNo/{cardNo}", 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> deleteBorr(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable("cardNo") Integer cardNo) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/borrower/cardNo/{cardNo}", HttpMethod.DELETE, 
					new HttpEntity<Object>(headers), String.class, cardNo);
		} catch (HttpStatusCodeException e){
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
		            .body(e.getResponseBodyAsString());
		}
	}
	
	//View all borrower
	@GetMapping(value = "/LMSAdmin/borrower", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Iterable<Borrower>> getAllBorrs(@RequestHeader("Accept") String accept) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		return restTemp.exchange(adminUri + "/borrower", HttpMethod.GET, new HttpEntity<Object>(headers), 
				new ParameterizedTypeReference<Iterable<Borrower>>(){});
	}
	
	
	/*
	 * Library Branch Orchestrator
	 */
	
	//Create branch
	@PostMapping(value = "/LMSAdmin/libraryBranch/branchName/{name}/branchAddress/{address}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> insertBranch(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable("name") String branchName, @PathVariable("address") String branchAddress) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		return restTemp.exchange(adminUri + "/libraryBranch/branchName/{name}/branchAddress/{address}", 
				HttpMethod.POST, new HttpEntity<Object>(headers), String.class, branchName, branchAddress);
	}
	
	//Update branch
	@PutMapping(value = "/LMSAdmin/libraryBranch/branchId/{branchId}/branchName/{name}/branchAddress/{address}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> updateBranch(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable("branchId") Integer branchId, @PathVariable("name") String branchName, 
			@PathVariable("address") String branchAddress) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/libraryBranch/branchId/{branchId}/branchName/{name}/branchAddress/{address}", HttpMethod.PUT, 
					new HttpEntity<Object>(headers), String.class, branchId, branchName, branchAddress);
		} catch (HttpStatusCodeException e){
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
	               .body(e.getResponseBodyAsString());
		}
	}
	
	//Delete branch
	@DeleteMapping(value = "/LMSAdmin/libraryBranch/branchId/{branchId}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> deleteBranch(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable("branchId") Integer branchId) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/libraryBranch/branchId/{branchId}", HttpMethod.DELETE, 
					new HttpEntity<Object>(headers), String.class, branchId);
		} catch (HttpStatusCodeException e){
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
		            .body(e.getResponseBodyAsString());
		}
	}
	
	//Dispatch delete branch
	@DeleteMapping(value = "/LMSAdmin/libraryBranch/dispatch/branchId/{branchId}/newBranchId/{newBranId}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> dispatchDeleteBranch(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable("branchId") Integer branchId, @PathVariable("newBranId") Integer newBranId) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/libraryBranch/dispatch/branchId/{branchId}/newBranchId/{newBranId}", HttpMethod.DELETE, 
					new HttpEntity<Object>(headers), String.class, branchId, newBranId);
		} catch (HttpStatusCodeException e){
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
		            .body(e.getResponseBodyAsString());
		}
	}
	
	//View available branches
	@GetMapping(value = "/LMSAdmin/libraryBranch/dispatch/branchId/{branchId}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Iterable<LibraryBranch>> getAvailableBranch(@RequestHeader("Accept") String accept,
			@PathVariable("branchId") Integer branchId) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		return restTemp.exchange(adminUri + "/libraryBranch/dispatch/branchId/{branchId}", HttpMethod.GET, 
				new HttpEntity<Object>(headers), new ParameterizedTypeReference<Iterable<LibraryBranch>>(){}, branchId);
	}
	
	//View all branches
	@GetMapping(value = "/LMSAdmin/libraryBranch", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Iterable<LibraryBranch>> getAvailableBranch(@RequestHeader("Accept") String accept) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		return restTemp.exchange(adminUri + "/libraryBranch", HttpMethod.GET, 
				new HttpEntity<Object>(headers), new ParameterizedTypeReference<Iterable<LibraryBranch>>(){});
	}
	
	
	
	/*
	 * Publisher Orchestrator
	 */
	
	//Create pub
	@PostMapping(value = "/LMSAdmin/publisher/pubName/{publisherName}/pubAddress/{publisherAddress}/pubPhone/{publisherPhone}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> insertPub(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable("publisherName") String pubName, @PathVariable("publisherAddress") String pubAddress, 
			@PathVariable("publisherPhone") String pubPhone) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		return restTemp.exchange(adminUri + "/publisher/pubName/{publisherName}/pubAddress/{publisherAddress}/pubPhone/{publisherPhone}", 
				HttpMethod.POST, new HttpEntity<Object>(headers), String.class, pubName, pubAddress, pubPhone);
	}
	
	//Update pub
	@PutMapping(value = "/LMSAdmin/publisher/pubId/{publisherId}/pubName/{publisherName}/pubAddress/{publisherAddress}/pubPhone/{publisherPhone}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> updatePub(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable("publisherId") Integer pubId, @PathVariable("publisherName") String pubName, 
			@PathVariable("publisherAddress") String pubAddress, @PathVariable("publisherPhone") String pubPhone) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/publisher/pubId/{publisherId}/pubName/{publisherName}/pubAddress/{publisherAddress}/pubPhone/{publisherPhone}", HttpMethod.PUT, 
					new HttpEntity<Object>(headers), String.class, pubId, pubName, pubAddress, pubPhone);
		} catch (HttpStatusCodeException e){
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
	               .body(e.getResponseBodyAsString());
		}
	}
	
	//Delete pub
	@DeleteMapping(value = "/LMSAdmin/publisher/pubId/{publisherId}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> deletePub(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable("publisherId") Integer pubId) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/publisher/pubId/{publisherId}", HttpMethod.DELETE, 
					new HttpEntity<Object>(headers), String.class, pubId);
		} catch (HttpStatusCodeException e){
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
		            .body(e.getResponseBodyAsString());
		}
	}
	
	//View publishers
	@GetMapping(value = "/LMSAdmin/publisher", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Iterable<Publisher>> getAllPubs(@RequestHeader("Accept") String accept) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		return restTemp.exchange(adminUri + "/publisher", HttpMethod.GET, 
				new HttpEntity<Object>(headers), new ParameterizedTypeReference<Iterable<Publisher>>(){});
	}
	
	
	
	/*
	 * Override Orchestrator
	 */
	
	//Override due date
	@PutMapping(value = "/LMSAdmin/overrideDueDate/cardNo/{cardNo}/branchId/{branchId}/bookId/{bookId}/extraDays/{days}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> overDueDate(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@PathVariable("cardNo") Integer cardNo, @PathVariable("branchId") Integer branchId, 
			@PathVariable("bookId") Integer bookId, @PathVariable("days") Integer days) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/overrideDueDate/cardNo/{cardNo}/branchId/{branchId}/bookId/{bookId}/extraDays/{days}", HttpMethod.PUT, 
					new HttpEntity<Object>(headers), String.class, cardNo, branchId, bookId, days);
		} catch (HttpStatusCodeException e){
			return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
	               .body(e.getResponseBodyAsString());
		}
	}
            
}
