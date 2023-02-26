package com.demo.project.services;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.project.dao.AddressDao;
import com.demo.project.dao.BookDao;
import com.demo.project.dao.BookDao2;
import com.demo.project.dao.ColorDao;
import com.demo.project.dao.CompanyDao;
import com.demo.project.dao.LaptopDao;
import com.demo.project.dao.UserDao;
import com.demo.project.models.Book;
import com.demo.project.models.Company;
import com.demo.project.models.one.to.one.uni.dir.Address;
import com.demo.project.models.one.to.one.uni.dir.User;
import com.demo.project.one.to.many.bi.dir.models.Color;
import com.demo.project.one.to.many.bi.dir.models.Laptop;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private BookDao2 bookDao2;

	private int totalObjects = 10000;

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;

	@Override
	@Transactional
	public Book saveData(Book book) {
		Book book2 = null;
		for (int i = 0; i <= 50000; i++) {
			book2 = new Book();
			book2.setName("BookName" + i);
			book2.setAuthor("AuthorName " + i);
			bookDao.save(book2);
		}
		return book2;
	}

	@Override
	@Transactional
	public List<Book> updateData() {

		List<Book> books = IntStream.range(0, totalObjects)
				.mapToObj(value -> Book.builder().name("Update Book " + value).author("Update Author" + value).build())
				.collect(Collectors.toList());
		
		for (int i = 120052; i < 139990; i = i + batchSize) {
			if (i + batchSize > 139990) {
				List<Book> books1 = bookDao.findAllById(i);
				bookDao.saveAll(books1);
				break;
			}
			List<Book> books1 = books.subList(i, i + batchSize);
			bookDao.saveAll(books1);
		}
		return books;
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		List<Book>deleteBookList= new ArrayList<Book>();
		for (int i = 120052; i < 139990; i = i + batchSize) {
			if (i + batchSize >= 139990) {
				List<Book> books = bookDao.findAllById(i);
				deleteBookList.addAll(books);
				break;
			}
			bookDao.deleteAll(deleteBookList);
		}
//		List<Book> deleteBook = new ArrayList<>();
//		for (long i = 80052; i <= 120048; i++) {
//			Book book = bookDao.findById(i).orElseThrow(() -> new RuntimeException(" Id not Found"));
//			deleteBook.add(book);
//		}
//		bookDao.deleteAll(deleteBook);

	}

	@Override
	public List<Book> saveBatchData() {
		List<Book> books = IntStream.range(0, totalObjects)
				.mapToObj(value -> Book.builder().name("Book " + value).author("Author" + value).build())
				.collect(Collectors.toList());
		for (int i = 0; i < totalObjects; i = i + batchSize) {
			if (i + batchSize > totalObjects) {
				List<Book> books1 = books.subList(i, totalObjects - 1);
				bookDao.saveAll(books1);
				break;
			}
			List<Book> books1 = books.subList(i, i + batchSize);
			bookDao.saveAll(books1);
		}
		return books;
	}

	@Override
	public Book saveCompanyOneData(Book book) {
		return bookDao.save(book);
	}

	@Override
	public List<Book> getLists() {
		return bookDao.findAll();
	}

	@Override
	@Transactional
	public Map<String, Object> getDataById(Long id) {
		Map<String, Object> mappedList = new HashMap<>();
		Book book = bookDao.findById(id).orElseThrow(() -> new RuntimeException(" Id not Found"));
		mappedList.put(book.getName(), book);
		return mappedList;
	}

}
