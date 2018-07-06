//package com.example.hwanpenndemo1.dao.impl;
//
////import forezp.dao.BookRepository;
////import forezp.entity.Book;
//import com.example.hwanpenndemo1.dao.BookRepository;
//import com.example.hwanpenndemo1.entity.Book;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Component;
//
///**
// * Created by fangzhipeng on 2017/4/19.
// */
//@Component
//public class SimpleBookRepository implements BookRepository {
//
//    @Override
//    @Cacheable("books777")
//    public Book getByIsbn(String isbn) {
//        simulateSlowService();
//        return new Book(isbn+"77", "Some new book");
//    }
//
//    // Don't do this at home
//    private void simulateSlowService() {
//        try {
//            long time = 3000L;
//            Thread.sleep(time);
//        } catch (InterruptedException e) {
//            throw new IllegalStateException(e);
//        }
//    }
//
//}