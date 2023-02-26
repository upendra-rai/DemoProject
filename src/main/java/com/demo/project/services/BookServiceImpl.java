package com.demo.project.services;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

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
	public Book updateData(Long id, Book book) {
		Book book2 = null;
		for (int i = 0; i <= 50000; i++) {

		}
		return book2;
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		List<Book> deleteBook = new ArrayList<>();
		for (long i = 0; i <= 50000; i++) {
			Book book = bookDao.findById(i).orElseThrow(() -> new RuntimeException(" Id not Found"));
			deleteBook.add(book);
		}
		bookDao.deleteAll(deleteBook);

	}

	@Override
	public Book saveBatchData() {
//		for (int i = 0; i < totalObjects; i = i + batchSize) {
//		    if( i+ batchSize > totalObjects){
//		        List<Book> books1 = books.subList(i, totalObjects - 1);
//		        repository.saveAll(books1);
//		        break;
//		    }
//		    List<Book> books1 = books.subList(i, i + batchSize);
//		    repository.saveAll(books1);
		return null;
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
