package com.travelagency_v2.travelagency_v2.controllers;


import com.travelagency_v2.travelagency_v2.models.bindingModels.BookingDto;
import com.travelagency_v2.travelagency_v2.models.viewModels.BookingProductViewModel;
import com.travelagency_v2.travelagency_v2.models.viewModels.BookingViewModel;
import com.travelagency_v2.travelagency_v2.services.BookingProductService;
import com.travelagency_v2.travelagency_v2.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    private final BookingProductService bookingProductService;

    @Autowired
    public BookingController(BookingService bookingService, BookingProductService bookingProductService) {
        this.bookingService = bookingService;
        this.bookingProductService = bookingProductService;
    }

    @GetMapping("")
    public String getBookingPage(@ModelAttribute BookingDto bookingDto, @ModelAttribute("bookingProductId") long id, Model model){
        BookingProductViewModel bookingProductViewModel = this.bookingProductService.findById(id);
        model.addAttribute("product",bookingProductViewModel);
        model.addAttribute("view","booking/booking-page");
        return "base-layout";
    }

    @PostMapping("")
    public String bookProduct(@Valid @ModelAttribute BookingDto bookingDto, @AuthenticationPrincipal Principal principal,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("view","booking/booking-page");
            return "base-layout";
        }
        System.out.println(bookingDto.getProduct());
        bookingDto.setUsername(principal.getName());
        BookingViewModel bookingViewModel = this.bookingService.create(bookingDto);
        redirectAttributes.addFlashAttribute("bookingViewModel",bookingViewModel);
        return "redirect:/booking/confirm";
    }

    @GetMapping("/confirm")
    public String getConfirmationPage(@ModelAttribute("bookingViewModel") BookingViewModel bookingViewModel, Model model){
        model.addAttribute("view","booking/booking-confirmation");
        return "base-layout";
    }
}
