package com.icesi.microservicio_miembro.controller;

import com.icesi.microservicio_miembro.model.Miembro;
import com.icesi.microservicio_miembro.service.MiembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/miembros")
public class MiembroController {

    @Autowired
    private MiembroService miembroService;

    @PostMapping
    public Miembro registrarMiembro(@RequestBody Miembro miembro) {
        return miembroService.registrarMiembro(miembro);
    }

    @GetMapping
    public List<Miembro> obtenerTodosMiembros() {
        return miembroService.obtenerTodosMiembros();
    }

}
