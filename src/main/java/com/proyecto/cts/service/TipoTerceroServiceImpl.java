package com.proyecto.cts.service;

import com.proyecto.cts.entity.TipoTerceroEntity;
import com.proyecto.cts.repository.TipoTerceroRepository;
import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.GeneralValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TipoTerceroServiceImpl implements TipoTerceroService {
    GeneralValidation general = new GeneralValidation();
    @Autowired
    private TipoTerceroRepository tipoterceroRepository;

    @Override
    public List<TipoTerceroEntity> list() {
        return (List<TipoTerceroEntity>) tipoterceroRepository.findAll();
    }

    public TipoTerceroEntity save(TipoTerceroEntity tipotercero) throws Exception {

        tipotercero = aMayusculas(tipotercero);
        tipotercero.setFechaCreacion(LocalDateTime.parse(general.fechaActual(2)));
        tipotercero.setFechaModificacion(tipotercero.getFechaCreacion());

        if (tipoterceroRepository.findByCodigoContar(tipotercero.getCodigo()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
        }
        if (tipoterceroRepository.findByValidarDuplicados(tipotercero.getDescripcion()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
        }
        return tipoterceroRepository.save(tipotercero);
    }

    @Override
    public TipoTerceroEntity update(TipoTerceroEntity tipotercero, Long id) throws Exception {

        TipoTerceroEntity tipoterceroDb = null;

        if (tipoterceroRepository.searchById(id) > 0) {
            tipoterceroDb = tipoterceroRepository.findById(id).get();

            tipotercero.setId(id);
            tipotercero.setIdEstadoRegistro(tipoterceroDb.getIdEstadoRegistro());
            tipotercero.setIdUsuarioCreacion(tipoterceroDb.getIdUsuarioCreacion());
            tipotercero.setFechaCreacion(tipoterceroDb.getFechaCreacion());
            tipotercero.setIdUsuarioModificacion(tipoterceroDb.getIdUsuarioModificacion());
            tipotercero.setFechaModificacion(LocalDateTime.parse(general.fechaActual(2)));

            tipotercero = aMayusculas(tipotercero);

            if (!(tipoterceroDb.getCodigo().equals(tipotercero.getCodigo()))) {
                if (tipoterceroRepository.findByCodigoContar(tipotercero.getCodigo()) != 0) {
                    throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
                }
            }

            if (tipoterceroRepository.findByValidarDuplicados(tipotercero.getDescripcion()) != 0) {
                throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
            }
            return tipoterceroRepository.save(tipotercero);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
    }

    @Override
    public TipoTerceroEntity deleteById(Long id) throws Exception {

        if (tipoterceroRepository.searchById(id) > 0) {
            tipoterceroRepository.deleteById(id);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
        return null;
    }

    public TipoTerceroEntity aMayusculas(TipoTerceroEntity tipotercero) {
        tipotercero.setCodigo(tipotercero.getCodigo().toUpperCase());
        tipotercero.setDescripcion(tipotercero.getDescripcion().toUpperCase());

        return tipotercero;
    }

    @Override
    public Long findByCodigoContar(String codigo) {
        return tipoterceroRepository.findByCodigoContar(codigo);
    }

    @Override
    public Long findByValidarDuplicados(String nombreCompleto) {
        return tipoterceroRepository.findByValidarDuplicados(nombreCompleto);
    }
}
