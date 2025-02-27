package com.icesi.microservicio_miembro.service;


import com.icesi.microservicio_miembro.model.Miembro;
import com.icesi.microservicio_miembro.repository.MiembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiembroService {
    @Autowired
    private MiembroRepository miembroRepository;

    public Miembro registrarMiembro(Miembro miembro) {
        return miembroRepository.save(miembro);
    }

    public List<Miembro> obtenerTodosMiembros() {
        return miembroRepository.findAll();
    }
}
