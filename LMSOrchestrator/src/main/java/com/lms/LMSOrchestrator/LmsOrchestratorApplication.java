package com.lms.LMSOrchestrator;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.lms.LMSAdmin.pojo.Author;
import com.lms.LMSAdmin.pojo.Book;
import com.lms.LMSAdmin.pojo.BookLoans;
import com.lms.LMSAdmin.pojo.Borrower;
import com.lms.LMSAdmin.pojo.LibraryBranch;
import com.lms.LMSAdmin.pojo.Override;
import com.lms.LMSAdmin.pojo.Publisher;


@SpringBootApplication
@RestController
@RequestMapping(value = "/LMSOrchestrator")
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
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
	@PostMapping("/LMSAdmin/authors/authorName/{authorName}")
	public ResponseEntity<?> insertAuthor(@RequestHeader("Accept") String accept, 
			@RequestHeader("Content-Type") String contentType, @RequestBody Author author) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/authors/authorName/{authorName}", HttpMethod.POST, 
					new HttpEntity<Author>(author, headers), Author.class, author.getAuthorName());
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Author>(e.getStatusCode());
		}
	}
	
	//Update author
	@PutMapping("/LMSAdmin/authors/authorId/{authorId}/authorName/{authorName}")
	public ResponseEntity<?> updateAuthor(@RequestHeader("Accept") String accept, 
			@RequestHeader("Content-Type") String contentType, @RequestBody Author author) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/authors/authorId/{authorId}/authorName/{authorName}", HttpMethod.PUT, 
					new HttpEntity<Author>(author, headers), Author.class, author.getAuthorId(), author.getAuthorName());
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Author>(e.getStatusCode());
		}
	}
	
	//Delete author
	@DeleteMapping("/LMSAdmin/authors/authorId/{authorId}")
	public ResponseEntity<?> deleteAuthor(@RequestHeader("Accept") String accept, 
			@RequestHeader("Content-Type") String contentType, @RequestBody Author author) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/authors/authorId/{authorId}", HttpMethod.DELETE, 
					new HttpEntity<Author>(author, headers), Author.class, author.getAuthorId());
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Author>(e.getStatusCode());
		}
	}

	//View all authors
	@GetMapping("/LMSAdmin/authors")
	public ResponseEntity<Iterable<Author>> getAllAuthors(@RequestHeader("Accept") String accept) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		try {
		return restTemp.exchange(adminUri + "/authors", HttpMethod.GET, new HttpEntity<Object>(headers), 
				new ParameterizedTypeReference<Iterable<Author>>(){});
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Iterable<Author>>(e.getStatusCode());
		}
	}
	
	
	/*
	 * Book Orchestrator
	 */
	
	//Create book
	@PostMapping("/LMSAdmin/books/title/{title}/authId/{authId}/pubId/{pubId}")
	public ResponseEntity<?> insertBook(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody Book book) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/books/title/{title}/authId/{authId}/pubId/{pubId}", HttpMethod.POST, 
					new HttpEntity<Book>(book, headers), Book.class, book.getTitle(), book.getAuthor().getAuthorId(), 
					book.getPublisher().getPublisherId());
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Book>(e.getStatusCode());
		}
	}
	
	//Update book
	@PutMapping("/LMSAdmin/books/bookId/{bookId}/title/{title}/authId/{authId}/pubId/{pubId}")
	public ResponseEntity<?> updateBook(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody Book book) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/books/bookId/{bookId}/title/{title}/authId/{authId}/pubId/{pubId}", HttpMethod.PUT, 
					new HttpEntity<Book>(book, headers), Book.class, book.getBookId(), book.getTitle(), 
					book.getAuthor().getAuthorId(), book.getPublisher().getPublisherId());
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Book>(e.getStatusCode());
		}
	}
	
	//Delete book
	@DeleteMapping("/LMSAdmin/books/bookId/{bookId}")
	public ResponseEntity<?> deleteBook(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody Book book) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/books/bookId/{bookId}", HttpMethod.DELETE, 
					new HttpEntity<Object>(book, headers), Book.class, book.getBookId());
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Book>(e.getStatusCode());
		}
	}
	
	//View all books
	@GetMapping("/LMSAdmin/books")
	public ResponseEntity<Iterable<Book>> getAllBooks(@RequestHeader("Accept") String accept) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/books", HttpMethod.GET, new HttpEntity<Object>(headers), 
				new ParameterizedTypeReference<Iterable<Book>>(){});
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Iterable<Book>>(e.getStatusCode());
		}
	}
	
	
	/*
	 * Borrower Orchestrator
	 */
	
	//Create borrower
	@PostMapping("/LMSAdmin/borrowers/borrName/{name}/borrAddress/{address}/borrPhone/{phone}")
	public ResponseEntity<?> insertBorr(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody Borrower borrower) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/borrowers/borrName/{name}/borrAddress/{address}/borrPhone/{phone}", 
				HttpMethod.POST, new HttpEntity<Borrower>(borrower, headers), Borrower.class, borrower.getName(), 
				borrower.getAddress(), borrower.getPhone());
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Borrower>(e.getStatusCode());
		}
	}
	
	//Update borrower
	@PutMapping("/LMSAdmin/borrowers/cardNo/{cardNo}/borrName/{name}/borrAddress/{address}/borrPhone/{phone}")
	public ResponseEntity<?> updateBorr(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody Borrower borrower) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/borrowers/cardNo/{cardNo}/borrName/{name}/borrAddress/{address}/borrPhone/{phone}", HttpMethod.PUT, 
					new HttpEntity<Borrower>(borrower, headers), Borrower.class, borrower.getCardNo(), borrower.getName(), 
					borrower.getAddress(), borrower.getPhone());
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Borrower>(e.getStatusCode());
		}
	}
	
	//Delete borrower
	@DeleteMapping("/LMSAdmin/borrowers/cardNo/{cardNo}")
	public ResponseEntity<?> deleteBorr(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody Borrower borrower) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/borrowers/cardNo/{cardNo}", HttpMethod.DELETE, 
					new HttpEntity<Borrower>(borrower, headers), Borrower.class, borrower.getCardNo());
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Borrower>(e.getStatusCode());
		}
	}
	
	//View all borrower
	@GetMapping("/LMSAdmin/borrowers")
	public ResponseEntity<Iterable<Borrower>> getAllBorrs(@RequestHeader("Accept") String accept) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/borrowers", HttpMethod.GET, new HttpEntity<Object>(headers), 
				new ParameterizedTypeReference<Iterable<Borrower>>(){});
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Iterable<Borrower>>(e.getStatusCode());
		}
	}
	
	
	/*
	 * Library Branch Orchestrator
	 */
	
	//Create branch
	@PostMapping("/LMSAdmin/branches/branchName/{name}/branchAddress/{address}")
	public ResponseEntity<?> insertBranch(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody LibraryBranch branch) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/branches/branchName/{name}/branchAddress/{address}", 
				HttpMethod.POST, new HttpEntity<LibraryBranch>(branch, headers), LibraryBranch.class, branch.getBranchName(), 
				branch.getBranchAddress());
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<LibraryBranch>(e.getStatusCode());
		}
	}
	
	//Update branch
	@PutMapping("/LMSAdmin/branches/branchId/{branchId}/branchName/{name}/branchAddress/{address}")
	public ResponseEntity<?> updateBranch(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody LibraryBranch branch) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/branches/branchId/{branchId}/branchName/{name}/branchAddress/{address}", HttpMethod.PUT, 
					new HttpEntity<LibraryBranch>(branch, headers), LibraryBranch.class, branch.getBranchId(), 
					branch.getBranchName(), branch.getBranchAddress());
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<LibraryBranch>(e.getStatusCode());
		}
	}
	
	//Delete branch
	@DeleteMapping("/LMSAdmin/branches/branchId/{branchId}")
	public ResponseEntity<?> deleteBranch(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody LibraryBranch branch) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/branches/branchId/{branchId}", HttpMethod.DELETE, 
					new HttpEntity<LibraryBranch>(branch, headers), LibraryBranch.class, branch.getBranchId());
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<LibraryBranch>(e.getStatusCode());
		}
	}
	
	//View all branches
	@GetMapping("/LMSAdmin/branches")
	public ResponseEntity<Iterable<LibraryBranch>> getAvailableBranch(@RequestHeader("Accept") String accept) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/branches", HttpMethod.GET, 
				new HttpEntity<Object>(headers), new ParameterizedTypeReference<Iterable<LibraryBranch>>(){});
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Iterable<LibraryBranch>>(e.getStatusCode());
		}
	}
	
	
	
	/*
	 * Publisher Orchestrator
	 */
	
	//Create pub
	@PostMapping("/LMSAdmin/publishers/pubName/{publisherName}/pubAddress/{publisherAddress}/pubPhone/{publisherPhone}")
	public ResponseEntity<?> insertPub(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody Publisher pub) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/publishers/pubName/{publisherName}/pubAddress/{publisherAddress}/pubPhone/{publisherPhone}", 
				HttpMethod.POST, new HttpEntity<Publisher>(pub, headers), Publisher.class, pub.getPublisherName(), 
				pub.getPublisherAddress(), pub.getPublisherPhone());
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Publisher>(e.getStatusCode());
		}
	}
	
	//Update pub
	@PutMapping("/LMSAdmin/publishers/pubId/{publisherId}/pubName/{publisherName}/pubAddress/{publisherAddress}/pubPhone/{publisherPhone}")
	public ResponseEntity<?> updatePub(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody Publisher pub) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/publishers/pubId/{publisherId}/pubName/{publisherName}/pubAddress/{publisherAddress}/pubPhone/{publisherPhone}", HttpMethod.PUT, 
					new HttpEntity<Publisher>(pub, headers), Publisher.class, pub.getPublisherId(), pub.getPublisherName(), 
					pub.getPublisherAddress(), pub.getPublisherName());
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Publisher>(e.getStatusCode());
		}
	}
	
	//Delete pub
	@DeleteMapping(value = "/LMSAdmin/publishers/pubId/{publisherId}")
	public ResponseEntity<?> deletePub(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody Publisher pub) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/publishers/pubId/{publisherId}", HttpMethod.DELETE, 
					new HttpEntity<Publisher>(pub, headers), Publisher.class, pub.getPublisherId());
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Publisher>(e.getStatusCode());
		}
	}
	
	//View publishers
	@GetMapping("/LMSAdmin/publishers")
	public ResponseEntity<Iterable<Publisher>> getAllPubs(@RequestHeader("Accept") String accept) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		try {
		return restTemp.exchange(adminUri + "/publishers", HttpMethod.GET, 
				new HttpEntity<Object>(headers), new ParameterizedTypeReference<Iterable<Publisher>>(){});
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Iterable<Publisher>>(e.getStatusCode());
		}
	}
	
	
	
	/*
	 * Override Orchestrator
	 */
	
	//Override due date
	@PutMapping("/LMSAdmin/duedate/cardNo/{cardNo}/branchId/{branchId}/bookId/{bookId}/extraDays/{days}")
	public ResponseEntity<?> overDueDate(@RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType,
			@RequestBody Override override) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		headers.add("Accept", accept);
		
		try {
			return restTemp.exchange(adminUri + "/duedate/cardNo/{cardNo}/branchId/{branchId}/bookId/{bookId}/extraDays/{days}", HttpMethod.PUT, 
					new HttpEntity<Override>(override, headers), Override.class, override.getCardNo(), override.getBranchId(), 
					override.getBookId(), override.getDays());
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Override>(e.getStatusCode());
		}
	}
	
	//View book loans
	@GetMapping("/LMSAdmin/loans")
	public ResponseEntity<Iterable<BookLoans>> getBookLoans(@RequestHeader("Accept") String accept) {
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept", accept);
		
		try {
		return restTemp.exchange(adminUri + "/loans", HttpMethod.GET, 
				new HttpEntity<Object>(headers), new ParameterizedTypeReference<Iterable<BookLoans>>(){});
		} catch(HttpStatusCodeException e) {
			return new ResponseEntity<Iterable<BookLoans>>(e.getStatusCode());
		}
	}
            
}
