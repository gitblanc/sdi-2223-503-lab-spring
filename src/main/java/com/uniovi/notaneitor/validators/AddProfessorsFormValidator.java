package com.uniovi.notaneitor.validators;

import com.uniovi.notaneitor.entities.Professor;
import com.uniovi.notaneitor.entities.User;
import com.uniovi.notaneitor.services.MarksService;
import com.uniovi.notaneitor.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AddProfessorsFormValidator implements Validator {
    @Autowired
    private ProfessorService professorsService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Professor.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Professor professor = (Professor) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
        if (professor.getDni().toString().length() != 9 || !Character.isLetter(professor.getDni().substring(professor.getDni().toString().length() - 1).charAt(0))) {
            errors.rejectValue("dni", "Error.add.dni.professor");
        }
        if(professorsService.existsProfessor(professor.getDni())){
            errors.rejectValue("dni", "Error.add.dni.unique.professor");
        }
    }
}