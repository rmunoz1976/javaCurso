package com.proyecto.cts.service;

import com.proyecto.cts.entity.RolEntity;
import com.proyecto.cts.repository.RolRepository;
import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.GeneralValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {
    GeneralValidation general = new GeneralValidation();
    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<RolEntity> list() {
        return (List<RolEntity>) rolRepository.findAll();
    }

    public RolEntity save(RolEntity rol) throws Exception {

        rol = aMayusculas(rol);

        if (rolRepository.findByCodigoContar(rol.getCodigo()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
        }
        if (rolRepository.findByValidarDuplicados(rol.getDescripcion()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
        }
        return rolRepository.save(rol);
    }

    @Override
    public RolEntity update(RolEntity rol, Long id) throws Exception {

        RolEntity rolDb = null;

        if (rolRepository.searchById(id) > 0) {
            rolDb = rolRepository.findById(id).get();

            rol.setId(id);
            rol = aMayusculas(rol);

            if (!(rolDb.getCodigo().equals(rol.getCodigo()))) {
                if (rolRepository.findByCodigoContar(rol.getCodigo()) != 0) {
                    throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
                }
            }

            if (rolRepository.findByValidarDuplicados(rol.getDescripcion()) != 0) {
                throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
            }

            return rolRepository.save(rol);

        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
    }

    @Override
    public RolEntity deleteById(Long id) throws Exception {

        if (rolRepository.searchById(id) > 0) {
            rolRepository.deleteById(id);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
        return null;
    }

    public RolEntity aMayusculas(RolEntity rol) {
        rol.setCodigo(rol.getCodigo().toUpperCase());
        rol.setDescripcion(rol.getDescripcion().toUpperCase());

        return rol;
    }

    @Override
    public Long findByCodigoContar(String codigo) {
        return rolRepository.findByCodigoContar(codigo);
    }

    @Override
    public Long findByValidarDuplicados(String nombreCompleto) {
        return rolRepository.findByValidarDuplicados(nombreCompleto);
    }

    @Override
    public Long searchById(Long id) {
        return rolRepository.searchById(id);
    }    
}
