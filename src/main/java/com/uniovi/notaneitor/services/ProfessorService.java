package com.uniovi.notaneitor.services;

import com.uniovi.notaneitor.entities.Professor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProfessorService {
    private List<Professor> professorsList = new LinkedList<>();

    @PostConstruct
    public void init() {
        professorsList.add(new Professor("41012833S", "Eduardo", "Blanco Bielsa", "Informática"));
        professorsList.add(new Professor("76512833S", "Rubén", "Balsa García", "Ciencias"));
    }

    public List<Professor> getProfessors() {
        return professorsList;
    }

    public Professor getProfessor(String dni) {
        return professorsList.stream()
                .filter(professor -> professor.getDni().equals(dni)).findFirst().get();
    }

    public void addProfessor(Professor p) {
        if (p.getDni() == null) {
            p.setDni(professorsList.get(professorsList.size() - 1).getDni() + 1);
        }

        professorsList.add(p);
    }

    public void deleteProfessor(String dni) {
        professorsList.removeIf(professor -> professor.getDni().equals(dni));
    }
}
