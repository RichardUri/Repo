package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//Tells the spring framework to detect this as a spring manage component
@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    /*public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
*/
    //Can only have on functioning constructor in the BootStrap class
    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Doman Driven Design", "123123");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        //Saves them to H2 database
        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "345678987654");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        //Saves them to H2 database
        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());

        //Creation and test of publisher data

        Publisher steveHandJobs = new Publisher("PubName","16519 SW 32nd ST", "Miami", "Florida", "33027");
        publisherRepository.save(steveHandJobs);

        System.out.println("Amount of publishers: " + publisherRepository.count());

        ddd.setPublisher(steveHandJobs);
        steveHandJobs.getBooks().add(ddd);

        noEJB.setPublisher(steveHandJobs);
        steveHandJobs.getBooks().add(noEJB);

        System.out.println("Num books: " + bookRepository.count());
        System.out.println("Publisher num books: " + steveHandJobs.getBooks().size());


    }
}
