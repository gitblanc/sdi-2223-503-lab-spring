package com.uniovi.notaneitor.controllers;

import com.uniovi.notaneitor.entities.Professor;
import com.uniovi.notaneitor.entities.User;
import com.uniovi.notaneitor.services.ProfessorService;
import com.uniovi.notaneitor.validators.AddProfessorsFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AddProfessorsFormValidator addProfessorsFormValidator;

    @RequestMapping("/professor/list")
    public String getList(Model model) {

        model.addAttribute("professorsList", professorService.getProfessors());
        return "professor/list";
    }

    @RequestMapping(value = "/professor/add", method = RequestMethod.POST)
    public String setProfessor(@Validated Professor professor, BindingResult result) {
        addProfessorsFormValidator.validate(professor, result);
        if(result.hasErrors()){
            return "professor/add";
        }

        professorService.addProfessor(professor);
        return "redirect:/professor/list";
    }

    @RequestMapping(value = "/professor/add", method = RequestMethod.GET)
    public String getProfessor(Model model) {
        model.addAttribute("professor", new Professor());
        return "professor/add";
    }

    @RequestMapping("/professor/details/{dni}")
    public String getDetail(Model model, @PathVariable String dni) {
        model.addAttribute("professor", professorService.getProfessor(dni));
        return "professor/details";
    }

    @RequestMapping("/professor/delete/{dni}")
    public String deleteProfessor(@PathVariable String dni) {
        professorService.deleteProfessor(dni);
        return "redirect:/professor/list";
    }
}