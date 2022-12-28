package com.proyecto.cts.controller;

import com.proyecto.cts.entity.UsuarioEntity;
import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.EnumResult;
import com.proyecto.cts.zgeneral.GeneralResponse;
import com.proyecto.cts.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cts/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    String mensajeError1 = "";
    String mensajeError2 = "";
    GeneralResponse generalResponse;

    // Listar
    @GetMapping("/List")
    public List<UsuarioEntity> list() {
        return usuarioService.list();
    }

    //Insertar
    @PostMapping("/Save")
    public ResponseEntity<GeneralResponse> save(@Validated @RequestBody UsuarioEntity usuarioEntity) throws Exception {
        generalResponse = new GeneralResponse();
        try {
            UsuarioEntity savedUsuario = usuarioService.save(usuarioEntity);
            if (Objects.nonNull(savedUsuario)) {
                generalResponse.setEstado(EnumResult.mensajeError.getSaved());
                generalResponse.setMensaje1(EnumMsgstatus.ERR0001.getErrorDescripcion());
                generalResponse.setMensaje2("");
                return ResponseEntity.status(HttpStatus.CREATED).body(generalResponse);
            } else {
                generalResponse.setEstado(EnumResult.mensajeError.getSavedError());
                generalResponse.setMensaje1(EnumMsgstatus.ERR0002.getErrorDescripcion());
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(generalResponse);
            }
        } catch (Exception error) {
            generalResponse.setEstado(EnumResult.mensajeError.getError());
            switch (error.getMessage()) {
                case "ERR1003" -> mensajeError1 = EnumMsgstatus.ERR1003.getErrorDescripcion();
                case "ERR1999" -> mensajeError1 = EnumMsgstatus.ERR1999.getErrorDescripcion();
                default -> {
                    mensajeError1 = error.getMessage();
                    mensajeError2 = String.valueOf(error.getCause().getCause());
                }
            }
            generalResponse.setMensaje1(mensajeError1);
            generalResponse.setMensaje2(mensajeError2);
            System.out.println(mensajeError1);
            System.out.println(mensajeError2);

            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(generalResponse);
        }
    }

    // Actualizar
    @PutMapping("/Update/{id}")
    public ResponseEntity<GeneralResponse> update(@Validated @RequestBody UsuarioEntity usuarioEntity,
                                                  @PathVariable("id") Long id) {
        generalResponse = new GeneralResponse();
        try {
            UsuarioEntity updatedUsuario = usuarioService.update(usuarioEntity, id);

            if (Objects.nonNull(updatedUsuario)) {
                generalResponse.setEstado(EnumResult.mensajeError.getUpdated());
                generalResponse.setMensaje1(EnumMsgstatus.ERR0001.getErrorDescripcion());
                generalResponse.setMensaje2("");
                return ResponseEntity.status(HttpStatus.CREATED).body(generalResponse);
            } else {
                generalResponse.setEstado(EnumResult.mensajeError.getUpdatedError());
                generalResponse.setMensaje1(EnumMsgstatus.ERR0002.getErrorDescripcion());
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(generalResponse);
            }

        } catch (Exception error) {

            generalResponse.setEstado(EnumResult.mensajeError.getError());
            switch (error.getMessage()) {
                case "ERR1003" -> mensajeError1 = EnumMsgstatus.ERR1003.getErrorDescripcion();
                case "ERR1998" -> mensajeError1 = EnumMsgstatus.ERR1998.getErrorDescripcion();
                case "ERR1999" -> mensajeError1 = EnumMsgstatus.ERR1999.getErrorDescripcion();
                default -> {
                    mensajeError1 = error.getMessage();
                    mensajeError2 = String.valueOf(error.getCause().getCause());
                }
            }

            generalResponse.setMensaje1(mensajeError1);
            generalResponse.setMensaje2(mensajeError2);
            System.out.println(mensajeError1);
            System.out.println(mensajeError2);

            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(generalResponse);
        }
    }

    // Borrar
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<GeneralResponse> deleteById(@PathVariable("id") Long id) throws Exception {
        generalResponse = new GeneralResponse();
        try {
            UsuarioEntity deletedUsuario = usuarioService.deleteById(id);
            generalResponse.setEstado(EnumResult.mensajeError.getDeleted());
            generalResponse.setMensaje1(EnumMsgstatus.ERR1701.getErrorDescripcion());
            generalResponse.setMensaje2("");
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(generalResponse);
        } catch (Exception error) {

            generalResponse.setEstado(EnumResult.mensajeError.getError());
            if ("ERR1998".equals(error.getMessage())) {
                mensajeError1 = EnumMsgstatus.ERR1998.getErrorDescripcion();
            } else {
                mensajeError1 = error.getMessage();
                mensajeError2 = String.valueOf(error.getCause().getCause());
            }

            generalResponse.setMensaje1(mensajeError1);
            generalResponse.setMensaje2(mensajeError2);
            System.out.println(mensajeError1);
            System.out.println(mensajeError2);

            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(generalResponse);
        }
    }
}
