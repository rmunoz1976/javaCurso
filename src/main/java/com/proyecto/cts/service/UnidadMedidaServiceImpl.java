package com.proyecto.cts.service;

import com.proyecto.cts.entity.UnidadMedidaEntity;
import com.proyecto.cts.repository.UnidadMedidaRepository;
import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.GeneralValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UnidadMedidaServiceImpl implements UnidadMedidaService {
    GeneralValidation general = new GeneralValidation();
    @Autowired
    private UnidadMedidaRepository unidadmedidaRepository;

    @Override
    public List<UnidadMedidaEntity> list() {
        return (List<UnidadMedidaEntity>) unidadmedidaRepository.findAll();
    }

    public UnidadMedidaEntity save(UnidadMedidaEntity unidadmedida) throws Exception {

        unidadmedida = aMayusculas(unidadmedida);
        unidadmedida.setFechaCreacion(LocalDateTime.parse(general.fechaActual(2)));
        unidadmedida.setFechaModificacion(unidadmedida.getFechaCreacion());

        if (unidadmedidaRepository.findByCodigoContar(unidadmedida.getCodigo()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
        }
        if (unidadmedidaRepository.findByValidarDuplicados(unidadmedida.getDescripcion()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
        }
        return unidadmedidaRepository.save(unidadmedida);
    }

    @Override
    public UnidadMedidaEntity update(UnidadMedidaEntity unidadmedida, Long id) throws Exception {

        UnidadMedidaEntity unidadmedidaDb = null;

        if (unidadmedidaRepository.searchById(id) > 0) {
            unidadmedidaDb = unidadmedidaRepository.findById(id).get();

            unidadmedida.setId(id);
            unidadmedida.setIdEstadoRegistro(unidadmedidaDb.getIdEstadoRegistro());
            unidadmedida.setIdUsuarioCreacion(unidadmedidaDb.getIdUsuarioCreacion());
            unidadmedida.setFechaCreacion(unidadmedidaDb.getFechaCreacion());
            unidadmedida.setIdUsuarioModificacion(unidadmedidaDb.getIdUsuarioModificacion());
            unidadmedida.setFechaModificacion(LocalDateTime.parse(general.fechaActual(2)));

            unidadmedida = aMayusculas(unidadmedida);

            if (!(unidadmedidaDb.getCodigo().equals(unidadmedida.getCodigo()))) {
                if (unidadmedidaRepository.findByCodigoContar(unidadmedida.getCodigo()) != 0) {
                    throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
                }
            }

            if (unidadmedidaRepository.findByValidarDuplicados(unidadmedida.getDescripcion()) != 0) {
                throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
            }
            return unidadmedidaRepository.save(unidadmedida);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
    }

    @Override
    public UnidadMedidaEntity deleteById(Long id) throws Exception {

        if (unidadmedidaRepository.searchById(id) > 0) {
            unidadmedidaRepository.deleteById(id);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
        return null;
    }

    public UnidadMedidaEntity aMayusculas(UnidadMedidaEntity unidadmedida) {
        unidadmedida.setCodigo(unidadmedida.getCodigo().toUpperCase());
        unidadmedida.setDescripcion(unidadmedida.getDescripcion().toUpperCase());

        return unidadmedida;
    }

    @Override
    public Long findByCodigoContar(String codigo) {
        return unidadmedidaRepository.findByCodigoContar(codigo);
    }

    @Override
    public Long findByValidarDuplicados(String nombreCompleto) {
        return unidadmedidaRepository.findByValidarDuplicados(nombreCompleto);
    }
}
