package com.travelagency_v2.travelagency_v2.controllers;


import com.travelagency_v2.travelagency_v2.entities.enums.Facility;
import com.travelagency_v2.travelagency_v2.entities.enums.HotelCategory;
import com.travelagency_v2.travelagency_v2.models.bindingModels.HotelDto;
import com.travelagency_v2.travelagency_v2.models.viewModels.CityViewModel;
import com.travelagency_v2.travelagency_v2.models.viewModels.HotelDetailViewModel;
import com.travelagency_v2.travelagency_v2.models.viewModels.HotelViewModel;
import com.travelagency_v2.travelagency_v2.services.CityService;
import com.travelagency_v2.travelagency_v2.services.HotelService;
import com.travelagency_v2.travelagency_v2.utilities.ReviewScore;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class HotelController {

    private final HotelService hotelService;

    private final CityService cityService;

    private final ModelMapper modelMapper;

    @Autowired
    public HotelController( HotelService hotelService, CityService cityService, ModelMapper modelMapper) {
        this.hotelService = hotelService;
        this.cityService = cityService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("cities")
    public List<CityViewModel> getCities(){
        List<CityViewModel> cities = this.cityService.findAll();
        return cities;
    }

    @ModelAttribute("categories")
    public HotelCategory[] getCategories(){
        return HotelCategory.values();
    }

    @ModelAttribute("facilities")
    public Facility[] getFacilities(){
        return Facility.values();
    }


    @GetMapping("/hotels")
    public String getHotelPage(@RequestParam(required = false) Map<String, String> params, Model model,
                               @PageableDefault(size = 8) Pageable pageable){
        List<HotelViewModel> hotelViewModels = this.hotelService.findAll();
        for (HotelViewModel hotelViewModel : hotelViewModels) {
            hotelViewModel.setScore(ReviewScore.set(hotelViewModel));
        }
        Page<HotelViewModel> hotelViewModelPage = new PageImpl<>(hotelViewModels,pageable,hotelViewModels.size());
        model.addAttribute("hotelMenu",true);
        model.addAttribute("hotels", hotelViewModelPage);
        model.addAttribute("view","hotels/hotels-page");
        return "base-layout";
    }


    @GetMapping("/hotels/{id}")
    public String getDetailHotel(@PathVariable long id, Model model){
        HotelDetailViewModel hotel = this.hotelService.findById(id);
        hotel.setScore(ReviewScore.set(hotel));
        model.addAttribute("hotel",hotel);
        model.addAttribute("view","hotels/hotel-details");
        return "base-layout";
    }

    @PostMapping("/hotels/{id}")
    public String goToBookingPage(@PathVariable long id, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("bookingProductId",id);
        return "redirect:/booking";
    }

    @GetMapping("/admin/hotels/add")
    public String getAddHotelPage(@ModelAttribute HotelDto hotelDto, Model model){
        model.addAttribute("view","hotels/add-hotel");
        model.addAttribute("action","Add");
        return "base-layout";
    }

    @PostMapping("/admin/hotels/add")
    public String addHotel(@Valid @ModelAttribute HotelDto hotelDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("view","hotels/add-hotel");
            model.addAttribute("action","Add");
            return "base-layout";
        }
        this.hotelService.create(hotelDto);
        return "redirect:/hotels";
    }

    @GetMapping("/admin/hotels/edit/{id}")
    public String getEditHotelPage(@PathVariable long id, @ModelAttribute HotelDto hotelDto, Model model){
        HotelDetailViewModel hotelDetailViewModel = this.hotelService.findById(id);
        this.modelMapper.map(hotelDetailViewModel,hotelDto);
        model.addAttribute("view","hotels/add-hotel");
        model.addAttribute("action","Edit");
        return "base-layout";
    }

    @PostMapping("/admin/hotels/edit/{id}")
    public String editHotel(@Valid @ModelAttribute HotelDto hotelDto, BindingResult bindingResult,
                            Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("view","hotels/add-hotel");
            model.addAttribute("action","Edit");
            return "base-layout";
        }
        this.hotelService.create(hotelDto);
        return "redirect:/hotels/" + hotelDto.getId();
    }

    @GetMapping("/admin/hotels/delete/{id}")
    public String getDeleteHotelPage(@PathVariable long id){
        this.hotelService.deleteById(id);
        return "redirect:/hotels";
    }


}
