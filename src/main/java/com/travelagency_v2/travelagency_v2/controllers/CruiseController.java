package com.travelagency_v2.travelagency_v2.controllers;


import com.travelagency_v2.travelagency_v2.models.viewModels.CruiseViewModel;
import com.travelagency_v2.travelagency_v2.services.CityService;
import com.travelagency_v2.travelagency_v2.services.CruiseService;
import com.travelagency_v2.travelagency_v2.entities.enums.CruiseCategory;
import com.travelagency_v2.travelagency_v2.models.bindingModels.BookingDto;
import com.travelagency_v2.travelagency_v2.models.bindingModels.CruiseDto;
import com.travelagency_v2.travelagency_v2.models.viewModels.CityViewModel;
import com.travelagency_v2.travelagency_v2.models.viewModels.CruiseDetailViewModel;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CruiseController {

    private final CruiseService cruiseService;
    private final ModelMapper modelMapper;
    private final CityService cityService;

    @Autowired
    public CruiseController(CruiseService cruiseService, ModelMapper modelMapper, CityService cityService) {
        this.cruiseService = cruiseService;
        this.modelMapper = modelMapper;
        this.cityService = cityService;
    }

    @ModelAttribute("cityNames")
    public List<String> getCities(){
        List<CityViewModel> cities = this.cityService.findAll();
        List<String> cityNames = new ArrayList<>();
        for (CityViewModel city : cities) {
            cityNames.add(city.getName());
        }
        return cityNames;
    }

    @ModelAttribute("categories")
    public CruiseCategory[] getCategories(){
        return CruiseCategory.values();
    }

    @GetMapping("/cruises")
    public String getCruisePage(Model model, @PageableDefault(size = 8) Pageable pageable){
        List<CruiseViewModel> cruiseViewModels = this.cruiseService.findAll();
        for (CruiseViewModel cruiseViewModel : cruiseViewModels) {
            cruiseViewModel.setScore(ReviewScore.set(cruiseViewModel));
        }
        Page<CruiseViewModel> cruiseViewModelPage = new PageImpl<>(cruiseViewModels,pageable,cruiseViewModels.size());
        model.addAttribute("cruiseMenu",true);
        model.addAttribute("cruises",cruiseViewModelPage);
        model.addAttribute("view","cruises/cruises-page");
        return "base-layout";
    }

    @GetMapping("/cruises/{id}")
    public String getDetailCruisePage(@PathVariable long id, @ModelAttribute BookingDto bookingDto, Model model){
        CruiseDetailViewModel cruise = this.cruiseService.findById(id);
        cruise.setScore(ReviewScore.set(cruise));
        model.addAttribute("cruise",cruise);
        model.addAttribute("view","cruises/cruise-detail");
        return "base-layout";
    }

    @PostMapping("/cruises/{id}")
    public String goToBookingPage(@PathVariable long id, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("bookingProductId",id);
        return "redirect:/booking";
    }

    @GetMapping("/admin/cruises/add")
    public String getAddCruisePage(@ModelAttribute CruiseDto cruiseDto, Model model){
        model.addAttribute("view","cruises/add-cruise");
        model.addAttribute("action","Add");
        return "base-layout";
    }

    @PostMapping("/admin/cruises/add")
    public String addCruise(@Valid @ModelAttribute CruiseDto cruiseDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("view","cruises/add-cruise");
            model.addAttribute("action","Add");
            return "base-layout";
        }
        this.cruiseService.create(cruiseDto);
        return "redirect:/cruises";
    }

    @GetMapping("/admin/cruises/edit/{id}")
    public String getEditCruisePage(@PathVariable long id, @ModelAttribute CruiseDto cruiseDto, Model model){
        CruiseDetailViewModel cruiseDetailViewModel = this.cruiseService.findById(id);
        this.modelMapper.map(cruiseDetailViewModel,cruiseDto);
        model.addAttribute("view","cruises/add-cruise");
        model.addAttribute("action","Edit");
        return "base-layout";
    }

    @PostMapping("/admin/cruises/edit/{id}")
    public String editCruise(@Valid @ModelAttribute CruiseDto cruiseDto, BindingResult bindingResult,
                             Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("view","cruises/add-cruise");
            model.addAttribute("action","Edit");
            return "base-layout";
        }

        this.cruiseService.create(cruiseDto);
        return "redirect:/cruises/" + cruiseDto.getId();
    }

    @GetMapping("/admin/cruises/delete/{id}")
    public String getDeleteHotelPage(@PathVariable long id){
        this.cruiseService.deleteById(id);
        return "redirect:/cruises";
    }
}
