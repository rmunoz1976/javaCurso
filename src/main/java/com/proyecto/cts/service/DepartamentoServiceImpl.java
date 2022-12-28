package com.proyecto.cts.service;

import com.proyecto.cts.entity.DepartamentoEntity;
import com.proyecto.cts.repository.DepartamentoRepository;
import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.GeneralValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {
    GeneralValidation general = new GeneralValidation();
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public List<DepartamentoEntity> list() {
        return (List<DepartamentoEntity>) departamentoRepository.findAll();
    }

    public DepartamentoEntity save(DepartamentoEntity departamento) throws Exception {

        departamento = aMayusculas(departamento);
        departamento.setFechaCreacion(LocalDateTime.parse(general.fechaActual(2)));
        departamento.setFechaModificacion(departamento.getFechaCreacion());

        if (departamentoRepository.findByCodigoContar(departamento.getCodigo()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
        }
        if (departamentoRepository.findByValidarDuplicados(departamento.getDescripcion()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
        }
        return departamentoRepository.save(departamento);
    }

    @Override
    public DepartamentoEntity update(DepartamentoEntity departamento, Long id) throws Exception {

        DepartamentoEntity departamentoDb = null;

        if (departamentoRepository.searchById(id) > 0) {
            departamentoDb = departamentoRepository.findById(id).get();

            departamento.setId(id);
            departamento.setIdEstadoRegistro(departamentoDb.getIdEstadoRegistro());
            departamento.setIdUsuarioCreacion(departamentoDb.getIdUsuarioCreacion());
            departamento.setFechaCreacion(departamentoDb.getFechaCreacion());
            departamento.setIdUsuarioModificacion(departamentoDb.getIdUsuarioModificacion());
            departamento.setFechaModificacion(LocalDateTime.parse(general.fechaActual(2)));

            departamento = aMayusculas(departamento);

            if (!(departamentoDb.getCodigo().equals(departamento.getCodigo()))) {
                if (departamentoRepository.findByCodigoContar(departamento.getCodigo()) != 0) {
                    throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
                }
            }

            if (departamentoRepository.findByValidarDuplicados(departamento.getDescripcion()) != 0) {
                throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
            }
            return departamentoRepository.save(departamento);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
    }

    @Override
    public DepartamentoEntity deleteById(Long id) throws Exception {

        if (departamentoRepository.searchById(id) > 0) {
            departamentoRepository.deleteById(id);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
        return null;
    }

    public DepartamentoEntity aMayusculas(DepartamentoEntity departamento) {
        departamento.setCodigo(departamento.getCodigo().toUpperCase());
        departamento.setDescripcion(departamento.getDescripcion().toUpperCase());

        return departamento;
    }

    @Override
    public Long findByCodigoContar(String codigo) {
        return departamentoRepository.findByCodigoContar(codigo);
    }

    @Override
    public Long findByValidarDuplicados(String nombreCompleto) {
        return departamentoRepository.findByValidarDuplicados(nombreCompleto);
    }
}
