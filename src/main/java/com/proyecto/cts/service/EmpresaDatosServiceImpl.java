package com.proyecto.cts.service;

import com.proyecto.cts.entity.EmpresaDatosEntity;
import com.proyecto.cts.repository.EmpresaDatosRepository;
import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.GeneralValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpresaDatosServiceImpl implements EmpresaDatosService {
    GeneralValidation general = new GeneralValidation();
    @Autowired
    private EmpresaDatosRepository empresadatoRepository;

    @Override
    public List<EmpresaDatosEntity> list() {
        return (List<EmpresaDatosEntity>) empresadatoRepository.findAll();
    }

    public EmpresaDatosEntity save(EmpresaDatosEntity empresadatos) throws Exception {

        empresadatos = aMayusculas(empresadatos);
        empresadatos.setFechaCreacion(LocalDateTime.parse(general.fechaActual(2)));
        empresadatos.setFechaModificacion(empresadatos.getFechaCreacion());

        if (empresadatoRepository.findByValidarDuplicados(empresadatos.getNombrePersonaJuridica()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1006.getErrorNumero());
        }
        return empresadatoRepository.save(empresadatos);
    }

    @Override
    public EmpresaDatosEntity update(EmpresaDatosEntity empresadatos, Long id) throws Exception {

        EmpresaDatosEntity empresadatosDb = null;

        if (empresadatoRepository.searchById(id) > 0) {
            empresadatosDb = empresadatoRepository.findById(id).get();

            empresadatos.setId(id);
            empresadatos.setIdUsuarioCreacion(empresadatosDb.getIdUsuarioCreacion());
            empresadatos.setFechaCreacion(empresadatosDb.getFechaCreacion());
            empresadatos.setIdUsuarioModificacion(empresadatosDb.getIdUsuarioModificacion());
            empresadatos.setFechaModificacion(LocalDateTime.parse(general.fechaActual(2)));

            empresadatos = aMayusculas(empresadatos);

            if (empresadatoRepository.findByValidarDuplicados(empresadatos.getNombrePersonaJuridica()) != 0) {
                throw new Exception(EnumMsgstatus.ERR1006.getErrorNumero());
            }
            return empresadatoRepository.save(empresadatos);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
    }

    @Override
    public EmpresaDatosEntity deleteById(Long id) throws Exception {

        if (empresadatoRepository.searchById(id) > 0) {
            empresadatoRepository.deleteById(id);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
        return null;
    }

    public EmpresaDatosEntity aMayusculas(EmpresaDatosEntity empresadatos) {
        empresadatos.setNombrePersonaJuridica(empresadatos.getNombrePersonaJuridica().toUpperCase());
        return empresadatos;
    }

    @Override
    public Long findByCodigoContar(String codigo) {
        return empresadatoRepository.findByCodigoContar(codigo);
    }

    @Override
    public Long findByValidarDuplicados(String nombrePersonaJuridica) {
        return empresadatoRepository.findByValidarDuplicados(nombrePersonaJuridica);
    }
}
