package com.proyecto.cts.controller;

import com.proyecto.cts.entity.SubgrupoProductoEntity;
import com.proyecto.cts.service.SubgrupoProductoService;
import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.EnumResult;
import com.proyecto.cts.zgeneral.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cts/subgrupoproducto")
public class SubgrupoProductoController {

    @Autowired
    private SubgrupoProductoService subgrupoproductoService;

    // Listar
    @GetMapping("/List")
    public List<SubgrupoProductoEntity> list() {
        return subgrupoproductoService.list();
    }

    //Insertar
    @PostMapping("/Save")
    public ResponseEntity<GeneralResponse> save(@Validated @RequestBody SubgrupoProductoEntity subgrupoproductoEntity) throws Exception {
        GeneralResponse response = new GeneralResponse();
        response.setEstado(EnumResult.mensajeError.getSavedError());
        try {
            SubgrupoProductoEntity savedSubgrupoProducto = subgrupoproductoService.save(subgrupoproductoEntity);
            if (Objects.nonNull(savedSubgrupoProducto)) {
                response.setEstado(EnumResult.mensajeError.getSaved());
                response.setMensaje1(EnumMsgstatus.ERR0001.getErrorDescripcion());
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                response.setMensaje1(EnumMsgstatus.ERR0002.getErrorDescripcion());
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
            }

        } catch (Exception error) {
            switch (error.getMessage()) {
                case "ERR1001" -> response.setMensaje1(EnumMsgstatus.ERR1001.getErrorDescripcion());
                case "ERR1002" -> response.setMensaje1(EnumMsgstatus.ERR1002.getErrorDescripcion());
                default -> {
                    response.setMensaje1(String.valueOf(error.getCause().getCause()));
                }
            }

            System.out.println(response.getMensaje1());
            System.out.println(response.getMensaje2());
            response = response.mensaje(response);
            System.out.println(response.getMensaje1());
            System.out.println(response.getMensaje2());

            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
        }
    }

    // Actualizar
    @PutMapping("/Update/{id}")
    public ResponseEntity<GeneralResponse> update(@Validated @RequestBody SubgrupoProductoEntity subgrupoproductosEntity,
                                                  @PathVariable("id") Long id) {
        GeneralResponse response = new GeneralResponse();
        response.setEstado(EnumResult.mensajeError.getUpdatedError());
        try {
            SubgrupoProductoEntity updatedSubgrupoProducto = subgrupoproductoService.update(subgrupoproductosEntity, id);
            if (Objects.nonNull(updatedSubgrupoProducto)) {
                response.setEstado(EnumResult.mensajeError.getUpdated());
                response.setMensaje1(EnumMsgstatus.ERR0001.getErrorDescripcion());
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                response.setMensaje1(EnumMsgstatus.ERR0002.getErrorDescripcion());
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
            }
        } catch (Exception error) {
            switch (error.getMessage()) {
                case "ERR1001" -> response.setMensaje1(EnumMsgstatus.ERR1001.getErrorDescripcion());
                case "ERR1002" -> response.setMensaje1(EnumMsgstatus.ERR1002.getErrorDescripcion());
                case "ERR1998" -> response.setMensaje1(EnumMsgstatus.ERR1998.getErrorDescripcion());
                default -> {
                    response.setMensaje1(String.valueOf(error.getCause().getCause()));
                    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
                }
            }

            System.out.println(response.getMensaje1());
            System.out.println(response.getMensaje2());
            response = response.mensaje(response);
            System.out.println(response.getMensaje1());
            System.out.println(response.getMensaje2());

            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
        }
    }

    // Borrar
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<GeneralResponse> deleteById(@PathVariable("id") Long id) throws Exception {
        GeneralResponse response = new GeneralResponse();
        response.setEstado(EnumResult.mensajeError.getDeleteError());
        try {
            subgrupoproductoService.deleteById(id);
            response.setEstado(EnumResult.mensajeError.getDeleted());
            response.setMensaje1(EnumMsgstatus.ERR1701.getErrorDescripcion());
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
        } catch (Exception error) {
            if ("ERR1998".equals(error.getMessage())) {
                response.setMensaje1(EnumMsgstatus.ERR1998.getErrorDescripcion());
            } else {
                response.setMensaje1(String.valueOf(error.getCause().getCause()));
            }

            System.out.println(response.getMensaje1());
            System.out.println(response.getMensaje2());
            response = response.mensaje(response);
            System.out.println(response.getMensaje1());
            System.out.println(response.getMensaje2());

            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
        }
    }
}
