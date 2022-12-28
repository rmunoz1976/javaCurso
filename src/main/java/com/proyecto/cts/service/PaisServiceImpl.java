package com.proyecto.cts.service;

import com.proyecto.cts.entity.PaisEntity;
import com.proyecto.cts.repository.PaisRepository;
import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.GeneralValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaisServiceImpl implements PaisService {
    GeneralValidation general = new GeneralValidation();
    @Autowired
    private PaisRepository paisRepository;

    @Override
    public List<PaisEntity> list() {
        return (List<PaisEntity>) paisRepository.findAll();
    }

    public PaisEntity save(PaisEntity pais) throws Exception {

        pais = aMayusculas(pais);
        pais.setFechaCreacion(LocalDateTime.parse(general.fechaActual(2)));
        pais.setFechaModificacion(pais.getFechaCreacion());

        if (paisRepository.findByCodigoContar(pais.getCodigo()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
        }
        if (paisRepository.findByValidarDuplicados(pais.getDescripcion()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
        }
        return paisRepository.save(pais);
    }

    @Override
    public PaisEntity update(PaisEntity pais, Long id) throws Exception {

        PaisEntity paisDb = null;

        if (paisRepository.searchById(id) > 0) {
            paisDb = paisRepository.findById(id).get();

            pais.setId(id);
            pais.setIdEstadoRegistro(paisDb.getIdEstadoRegistro());
            pais.setIdUsuarioCreacion(paisDb.getIdUsuarioCreacion());
            pais.setFechaCreacion(paisDb.getFechaCreacion());
            pais.setIdUsuarioModificacion(paisDb.getIdUsuarioModificacion());
            pais.setFechaModificacion(LocalDateTime.parse(general.fechaActual(2)));

            pais = aMayusculas(pais);

            if (!(paisDb.getCodigo().equals(pais.getCodigo()))) {
                if (paisRepository.findByCodigoContar(pais.getCodigo()) != 0) {
                    throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
                }
            }

            if (paisRepository.findByValidarDuplicados(pais.getDescripcion()) != 0) {
                throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
            }
            return paisRepository.save(pais);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
    }

    @Override
    public PaisEntity deleteById(Long id) throws Exception {

        if (paisRepository.searchById(id) > 0) {
            paisRepository.deleteById(id);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
        return null;
    }

    public PaisEntity aMayusculas(PaisEntity pais) {
        pais.setCodigo(pais.getCodigo().toUpperCase());
        pais.setDescripcion(pais.getDescripcion().toUpperCase());

        return pais;
    }

    @Override
    public Long findByCodigoContar(String codigo) {
        return paisRepository.findByCodigoContar(codigo);
    }

    @Override
    public Long findByValidarDuplicados(String nombreCompleto) {
        return paisRepository.findByValidarDuplicados(nombreCompleto);
    }
}
