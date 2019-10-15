package com.lms.LMSAdmin.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSAdmin.pojo.Author;
import com.lms.LMSAdmin.pojo.Book;
import com.lms.LMSAdmin.pojo.Borrower;
import com.lms.LMSAdmin.pojo.LibraryBranch;
import com.lms.LMSAdmin.pojo.Publisher;
import com.lms.LMSAdmin.service.AuthorService;
import com.lms.LMSAdmin.service.BookService;
import com.lms.LMSAdmin.service.BorrowerService;
import com.lms.LMSAdmin.service.LibraryBranchService;
import com.lms.LMSAdmin.service.OverrideService;
import com.lms.LMSAdmin.service.PublisherService;

@RestController
@RequestMapping(value = "/LMSAdmin*", 
	consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
	produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})

public class AdminController {
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	BorrowerService borrService;
	
	@Autowired
	LibraryBranchService branService;
	
	@Autowired
	OverrideService overService;
	
	@Autowired
	PublisherService pubService;
	
	
	/*
	 * Author Controller
	 */
	
	//Create a record
	@RequestMapping(value = "/author/authorName/{authorName}", method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> insertAuthor(@PathVariable String authorName) {
		
		authorService.insertAuthor(authorName);
		return new ResponseEntity<String>("Author record created.", HttpStatus.CREATED);
	}
	
	//Update a record
	@RequestMapping(value = "/author/authorId/{authorId}/authorName/{authorName}", method = {RequestMethod.PUT, RequestMethod.GET})
	public ResponseEntity<String> updateAuthor(@PathVariable("authorId") int authorId, @PathVariable("authorName") String authorName) {
		
		boolean checkId = authorService.ifExists(authorId);
		
		if(checkId == true) {
			authorService.updateAuthor(authorId, authorName);
			return new ResponseEntity<String>("Author record updated.", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Delete a record
	@RequestMapping(value = "/author/authorId/{authorId}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public ResponseEntity<String> deleteAuthor(@PathVariable int authorId) {
		
		boolean checkId = authorService.ifExists(authorId);
		
		if(checkId == true) {
			authorService.deleteAuthor(authorId);
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Get all records
	@RequestMapping(value = "/author", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<Author> getAllAuthors() {
		return authorService.getAllAuthors();
	}
	
	
	/*
	 * Book Controller
	 */
	
	//Create a record
	@RequestMapping(value = "/book/title/{title}/authId/{authId}/pubId/{pubId}", method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> insertBook(@PathVariable("title") String title, @PathVariable("authId") int authId, 
			@PathVariable("pubId") int pubId) {
		
		boolean checkId = authorService.ifExists(authId);
				
		if(checkId == true) {
			checkId = pubService.ifExists(pubId);
			
			if(checkId == true) {
				bookService.insertBook(title, authId, pubId);
				return new ResponseEntity<String>("Book record created.", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<String>("Invalid publisher ID.", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<String>("Invalid author ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Update a record
	@RequestMapping(value = "/book/bookId/{bookId}/title/{title}/authId/{authId}/pubId/{pubId}", 
			method = {RequestMethod.PUT, RequestMethod.GET})

	public ResponseEntity<String> updateBook(@PathVariable("bookId") int bookId, @PathVariable("title") String title, 
			@PathVariable("authId") int authId, @PathVariable("pubId") int pubId) {
		
		boolean checkId = bookService.ifExists(bookId);
		
		if(checkId == true) {
			checkId = authorService.ifExists(authId);
			
			if(checkId == true) {
				checkId = pubService.ifExists(pubId);
				
				if(checkId == true) {
					System.out.println(bookId + " " + authId + " " + pubId);
					bookService.updateBook(bookId, title, authId, pubId);
					return new ResponseEntity<String>("Book record updated.", HttpStatus.OK);
				}else {
					return new ResponseEntity<String>("Invalid publisher ID.", HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<String>("Invalid author ID.", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<String>("Invalid book ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Delete a record
	@RequestMapping(value = "/book/bookId/{bookId}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public ResponseEntity<String> deleteBook(@PathVariable("bookId") int bookId) {
		
		boolean checkId = bookService.ifExists(bookId);
		
		if(checkId == true) {
			bookService.deleteBook(bookId);
			return new ResponseEntity<String>("Book record deleted.", HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>("Invalid book ID.", HttpStatus.NOT_FOUND);
		}	
	}
	
	//Get all records
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	
	/*
	 * Borrower Controller
	 */
	
	//Create a record
	@RequestMapping(value = "/borrower/borrName/{name}/borrAddress/{address}/borrPhone/{phone}", 
			method = {RequestMethod.POST, RequestMethod.GET})
	
	public ResponseEntity<String> insertBorr(@PathVariable("name") String borrName, @PathVariable("address") String borrAddress, 
			@PathVariable("phone") String borrPhone) {
		
		borrService.insertBorr(borrName, borrAddress, borrPhone);
		return new ResponseEntity<String>("Borrower record created.", HttpStatus.CREATED);
	}
	
	//Update a record
	@RequestMapping(value = "/borrower/cardNo/{cardNo}/borrName/{name}/borrAddress/{address}/borrPhone/{phone}", 
			method = {RequestMethod.PUT, RequestMethod.GET})
	
	public ResponseEntity<String> updateBorr(@PathVariable("cardNo") int cardNo, @PathVariable("name") String borrName, 
			@PathVariable("address") String borrAddress, @PathVariable("phone") String borrPhone) {
		
		boolean checkId = borrService.ifExists(cardNo);
		
		if(checkId == true) {
			borrService.updateBorr(cardNo, borrName, borrAddress, borrPhone);
			return new ResponseEntity<String>("Borrower record updated.", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Delete a record
	@RequestMapping(value = "/borrower/cardNo/{cardNo}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public ResponseEntity<String> deleteBorr(@PathVariable("cardNo") int cardNo) {
				
		boolean checkId = borrService.ifExists(cardNo);
		
		if(checkId == true) {
			borrService.deleteBorr(cardNo);
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Get all records
	@RequestMapping(value = "/borrower", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<Borrower> getAllBorrs() {
		return borrService.getAllBorrs();
	}
	
	
	/*
	 * Library Branch Controller
	 */
	
	//Create a record
	@RequestMapping(value = "/libraryBranch/branchName/{name}/branchAddress/{address}", 
			method = {RequestMethod.POST, RequestMethod.GET})
	
	public ResponseEntity<String> insertBranch(@PathVariable("name") String branchName, @PathVariable("address") String branchAddress) {
		
		branService.insertBranch(branchName, branchAddress);
		return new ResponseEntity<String>("Library Branch record created.", HttpStatus.CREATED);
	}
	
	//Update a record
	@RequestMapping(value = "/libraryBranch/branchId/{branchId}/branchName/{name}/branchAddress/{address}", 
			method = {RequestMethod.PUT, RequestMethod.GET})
	
	public ResponseEntity<String> updateBranch(@PathVariable("branchId") int branchId, @PathVariable("name") String branchName, 
			@PathVariable("address") String branchAddress) {
		
		boolean checkId = branService.ifExists(branchId);
		
		if(checkId == true) {
			branService.updateBranch(branchId, branchName, branchAddress);
			return new ResponseEntity<String>("Library Branch record updated.", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Get all available branches to dispatch books to
	@RequestMapping(value = "/libraryBranch/dispatchDelete/branchId/{branchId}/newBranchId", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<LibraryBranch> getAvailableBranch(@PathVariable("branchId") int branchId) {
		return branService.getAvailableBranch(branchId);
	}
	
	//Dispatch books then delete branch
	@RequestMapping(value = "/dispatch/branchId/{branchId}/newBranchId/{newBranId}", 
			method = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET})
	
	public ResponseEntity<String> dispatchDeleteBranch(@PathVariable("branchId") int branchId, @PathVariable("newBranId") int newBranId) {
		
		boolean checkId = branService.ifExists(branchId);
		
		if(checkId == true) {
			branService.dispatchBooks(branchId, newBranId);
			branService.deleteBranch(branchId);
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Delete branch
	@RequestMapping(value = "/libraryBranch/branchId/{branchId}", 
			method = {RequestMethod.DELETE, RequestMethod.GET})
	
	public ResponseEntity<String> deleteBranch(@PathVariable("branchId") int branchId) {
		boolean checkId = branService.ifExists(branchId);
		
		if(checkId == true) {
			branService.deleteBranch(branchId);
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Get all records
	@RequestMapping(value = "/libraryBranch", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<LibraryBranch> getAllBranches() {
		return branService.getAllBranches();
	}
	
	
	/*
	 * Publisher Controller
	 */
	
	//Create a record
	@RequestMapping(value = "/publisher/pubName/{publisherName}/pubAddress/{publisherAddress}/pubPhone/{publisherPhone}", 
			method = {RequestMethod.POST, RequestMethod.GET})
	
	public ResponseEntity<String> insertPub(@PathVariable("publisherName") String pubName, 
			@PathVariable("publisherAddress") String pubAddress, @PathVariable("publisherPhone") String pubPhone) {
		
		pubService.insertPub(pubName, pubAddress, pubPhone);
		return new ResponseEntity<String>("Publisher record created.", HttpStatus.CREATED);
	}
	
	//Update a record
	@RequestMapping(value = "/publisher/pubId/{publisherId}/pubName/{publisherName}/pubAddress/{publisherAddress}/pubPhone/{publisherPhone}", 
			method = {RequestMethod.PUT, RequestMethod.GET})
	
	public ResponseEntity<String> updatePub(@PathVariable("publisherId") int pubId, @PathVariable("publisherName") String pubName, 
			@PathVariable("publisherAddress") String pubAddress, @PathVariable("publisherPhone") String pubPhone) {
		
		boolean checkId = pubService.ifExists(pubId);
		
		if(checkId == true) {
			pubService.updatePub(pubId, pubName, pubAddress, pubPhone);
			return new ResponseEntity<String>("Publisher record updated.", HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Delete a record
	@RequestMapping(value = "/publisher/pubId/{publisherId}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public ResponseEntity<String> deletePub(@PathVariable("publisherId") int pubId) {
		
		boolean checkId = pubService.ifExists(pubId);
		
		if(checkId == true) {
			pubService.deletePub(pubId);
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Get all records
	@RequestMapping(value = "/publisher", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<Publisher> getAllPubs() {
		return pubService.getAllPubs();
	}
	
	
	/*
	 * Override Due Date
	 */
	
	//Override due date
	@RequestMapping(value = "/overrideDueDate/cardNo/{cardNo}/branchId/{branchId}/bookId/{bookId}/extraDays/{days}", 
			method = {RequestMethod.PUT, RequestMethod.GET})

	public ResponseEntity<String> overDueDate(@PathVariable("cardNo") int cardNo, @PathVariable("branchId") int branchId, 
			@PathVariable("bookId") int bookId, @PathVariable("days") int days) {
		
		boolean checkIds = overService.ifExists(cardNo, bookId, branchId);
		
		if(checkIds == true) {
			//Get the current due date
			Date currDueDate = overService.getDueDate(cardNo, bookId, branchId);
			overService.overDueDate(cardNo, bookId, branchId, currDueDate, days);
			
			return new ResponseEntity<String>("Override successful.", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.OK);
		}
	}
}
