package lab.bookings.controllers;

import jakarta.validation.Valid;
import lab.bookings.models.Apartment;
import lab.bookings.repositories.ApartmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/apartments")
public class ApartmentController {
    private ApartmentRepository repository;

    public ApartmentController(ApartmentRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("apartments", repository.findAll());
        model.addAttribute(new Apartment());
        return "apartments";
    }

    @PostMapping()
    public String create(@Valid Apartment apartment, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("apartments", repository.findAll());
            return "apartments";
        } else {
            repository.save(apartment);
            return "redirect:/apartments";
        }
    }
}
