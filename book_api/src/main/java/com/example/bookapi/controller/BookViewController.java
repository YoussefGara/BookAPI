    package com.example.bookapi.controller;

    import com.example.bookapi.model.Book;
    import com.example.bookapi.service.BookService;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.*;
    import jakarta.validation.Valid;
    import org.springframework.web.servlet.mvc.support.RedirectAttributes;

    @Controller
    @RequestMapping("/view/books")
    public class BookViewController {

        private final BookService bookService;

        public BookViewController(BookService bookService) {
            this.bookService = bookService;
        }

        // Show all books (HTML)
        @GetMapping
        public String getAllBooks(Model model) {
            model.addAttribute("books", bookService.getAllBooks());
            return "books"; // Refers to src/main/resources/templates/books.html
        }

        // Show book details (HTML)
        @GetMapping("/{id}")
        public String getBookById(@PathVariable Long id, Model model) {
            bookService.getBookById(id).ifPresent(book -> model.addAttribute("book", book));
            return "book-details";
        }

        // Show create-book form (HTML)
        @GetMapping("/new")
        public String showCreateForm(Model model) {
            model.addAttribute("book", new Book());
            return "create-book";
        }

        // Handle form submission
        @PostMapping
        public String createBook(@Valid @ModelAttribute Book book, BindingResult result, Model model) {
            if (result.hasErrors()) {
                return "create-book"; // Re-render form with errors
            }
            bookService.createBook(book);
            return "redirect:/view/books";
        }

        // In BookViewController.java
        @GetMapping("/search")
        public String searchBooks(@RequestParam(required = false) String keyword, Model model) {
            if (keyword != null && !keyword.trim().isEmpty()) {
                model.addAttribute("books", bookService.searchByTitleOrAuthor(keyword));
            } else {
                model.addAttribute("books", bookService.getAllBooks());
            }
            return "books"; // Reuse the books.html template
        }



        // Handle delete
        @GetMapping("/delete/{id}")
        public String deleteBook(@PathVariable Long id) {
            bookService.deleteBook(id);
            return "redirect:/view/books";
        }


        @GetMapping("/edit/{id}")
        public String showEditForm(@PathVariable Long id, Model model) {
            Book book = bookService.getBookById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
            model.addAttribute("book", book);
            return "edit-book";
        }

        @PostMapping("/edit/{id}")
        public String updateBook(@PathVariable Long id, @Valid Book book,
                                 BindingResult result, Model model) {
            if (result.hasErrors()) {
                book.setId(id);
                return "edit-book";
            }

            bookService.saveBook(book);
            return "redirect:/view/books";
        }

    }