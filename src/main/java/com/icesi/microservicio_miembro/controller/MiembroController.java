package com.icesi.microservicio_miembro.controller;

import com.icesi.microservicio_miembro.model.Miembro;
import com.icesi.microservicio_miembro.service.MiembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/miembros")
public class MiembroController {

    @Autowired
    private MiembroService miembroService;

    @Operation(summary = "Registrar un nuevo miembro", description = "Este endpoint permite registrar un nuevo miembro en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Miembro registrado exitosamente"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido: el usuario no tiene el rol adecuado")
    })
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MEMBER')")
    public Miembro registrarMiembro(@RequestBody Miembro miembro) {
        return miembroService.registrarMiembro(miembro);
    }

    @Operation(summary = "Obtener todos los miembros", description = "Este endpoint devuelve una lista de todos los miembros registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de miembros obtenida exitosamente"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido: el usuario no tiene el rol adecuado")
    })
    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')")
    public List<Miembro> obtenerTodosMiembros() {
        return miembroService.obtenerTodosMiembros();
    }

    @Operation(summary = "Obtener un miembro por ID", description = "Este endpoint devuelve los detalles de un miembro específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Miembro encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Miembro no encontrado")
    })
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')")
    public Optional<Miembro> obtenerMiembroPorId(@PathVariable Long id) {
        return miembroService.obtenerMiembroPorId(id);
    }

    @Operation(summary = "Actualizar un miembro", description = "Este endpoint permite actualizar la información de un miembro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Miembro actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Miembro no encontrado")
    })
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Miembro actualizarMiembro(@PathVariable Long id, @RequestBody Miembro miembro) {
        return miembroService.actualizarMiembro(id, miembro);
    }

    @Operation(summary = "Eliminar un miembro", description = "Este endpoint permite eliminar un miembro del sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Miembro eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Miembro no encontrado")
    })
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void eliminarMiembro(@PathVariable Long id) {
        miembroService.eliminarMiembro(id);
    }
}
