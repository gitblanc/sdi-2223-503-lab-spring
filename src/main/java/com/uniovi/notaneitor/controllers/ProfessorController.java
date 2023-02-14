package com.uniovi.notaneitor.controllers;

import com.uniovi.notaneitor.entities.Professor;
import com.uniovi.notaneitor.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @RequestMapping("/professor/list")
    public String getList(){
        return professorService.getProfessors().toString();
    }

    @RequestMapping(value="/professor/add", method = RequestMethod.POST)
    public String setProfessor(@ModelAttribute Professor professor){
        professorService.addProfessor(professor);
        return "Ok";
    }

    @RequestMapping("/professor/details/{dni}")
    public String getDetail(@PathVariable String dni){
        return professorService.getProfessor(dni).toString();
    }

    @RequestMapping("/professor/delete/{dni}")
    public String deleteProfessor(@PathVariable String dni){
        professorService.deleteProfessor(dni);
        return "Ok";
    }
}
