package com.proyecto.cts.service;

import com.proyecto.cts.entity.UsuarioEntity;
import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.GeneralValidation;
import com.proyecto.cts.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    GeneralValidation general = new GeneralValidation();
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioEntity> list() {
        return (List<UsuarioEntity>) usuarioRepository.findAll();
    }

    public UsuarioEntity save(UsuarioEntity usuario) throws Exception {

        usuario = aMayusculas(usuario);
        usuario.setFechaCreacion(LocalDateTime.parse(general.fechaActual(2)));
        usuario.setFechaModificacion(usuario.getFechaCreacion());

        if (usuarioRepository.findByLoginContar(usuario.getUsername()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1003.getErrorNumero());
        }
        if (usuarioRepository.findByValidarDuplicados( usuario.getNombreCompleto(), usuario.getUsername(), usuario.getPassword(), usuario.getEmail()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1999.getErrorNumero());
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioEntity update(UsuarioEntity usuario, Long id) throws Exception {

        UsuarioEntity usuarioDb = null;

        if (usuarioRepository.searchById(id) > 0) {
            usuarioDb = usuarioRepository.findById(id).get();

            usuario.setId(id);
            usuario.setIdEstadoRegistro(usuarioDb.getIdEstadoRegistro());
            usuario.setIdUsuarioCreacion(usuarioDb.getIdUsuarioCreacion());
            usuario.setFechaCreacion(usuarioDb.getFechaCreacion());
            usuario.setIdUsuarioModificacion(usuarioDb.getIdUsuarioModificacion());
            usuario.setFechaModificacion(LocalDateTime.parse(general.fechaActual(2)));

            usuario = aMayusculas(usuario);

            if (!(usuarioDb.getUsername().equals(usuario.getUsername()))) {
                if (usuarioRepository.findByLoginContar(usuario.getUsername()) != 0) {
                    throw new Exception(EnumMsgstatus.ERR1003.getErrorNumero());
                }
            }

            if (usuarioRepository.findByValidarDuplicados( usuario.getNombreCompleto(), usuario.getUsername(), usuario.getPassword(), usuario.getEmail()) != 0) {
                throw new Exception(EnumMsgstatus.ERR1999.getErrorNumero());
            }

            return usuarioRepository.save(usuario);

        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
    }

    @Override
    public UsuarioEntity deleteById(Long id) throws Exception {

        if (usuarioRepository.searchById(id) > 0) {
            usuarioRepository.deleteById(id);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
        return null;
    }

    public UsuarioEntity aMayusculas(UsuarioEntity usuario) {
        usuario.setPrimerNombre(usuario.getPrimerNombre().toUpperCase());
        usuario.setSegundoNombre(usuario.getSegundoNombre().toUpperCase());
        usuario.setPrimerApellido(usuario.getPrimerApellido().toUpperCase());
        usuario.setSegundoApellido(usuario.getSegundoApellido().toUpperCase());
        usuario.setNombreCompleto(usuario.getPrimerNombre() + " " + usuario.getSegundoNombre() + " " +
                usuario.getPrimerApellido() + " " + usuario.getSegundoApellido());
        //usuario.setUsername(usuario.getUsername().toUpperCase());

        return usuario;
    }

    @Override
    public Long findByLoginContar(String Username) {
        return usuarioRepository.findByLoginContar(Username);
    }

    @Override
    public Long findByValidarDuplicados(String nombreCompleto,String userName, String password, String email) {
        return usuarioRepository.findByValidarDuplicados( nombreCompleto, userName, password, email);
    }

    @Override
    public Long searchById(Long id) {
        return usuarioRepository.searchById(id);
    }


}
