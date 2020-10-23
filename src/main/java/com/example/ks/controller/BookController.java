package com.example.ks.controller;

import com.example.ks.model.Book;
import com.example.ks.service.book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping("")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("books", bookService.findAll());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormUpdate(@PathVariable Long id){
        Optional<Book> book = bookService.findById(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @PostMapping("edit")
    public ModelAndView updateBook(@ModelAttribute("book") Book book){
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelte(@PathVariable Long id){
        Optional<Book> book = bookService.findById(id);
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteBook(@ModelAttribute("book") Book book){
        bookService.remove(book.getId());
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("books", bookService.findAll());
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView showFormCreate(){
        Book book = new Book();
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @PostMapping("create")
    public ModelAndView Create(@ModelAttribute("book") Book book){
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("books", bookService.findAll());
        return modelAndView;
    }
}
