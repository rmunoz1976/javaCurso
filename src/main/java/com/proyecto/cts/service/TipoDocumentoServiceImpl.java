package com.proyecto.cts.service;

import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.GeneralValidation;
import com.proyecto.cts.entity.TipoDocumentoEntity;
import com.proyecto.cts.repository.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {
    GeneralValidation general = new GeneralValidation();
    @Autowired
    private TipoDocumentoRepository tipodocumentoRepository;

    @Override
    public List<TipoDocumentoEntity> list() {
        return (List<TipoDocumentoEntity>) tipodocumentoRepository.findAll();
    }

    public TipoDocumentoEntity save(TipoDocumentoEntity tipodocumento) throws Exception {

        tipodocumento = aMayusculas(tipodocumento);
        tipodocumento.setFechaCreacion(LocalDateTime.parse(general.fechaActual(2)));
        tipodocumento.setFechaModificacion(tipodocumento.getFechaCreacion());

        if (tipodocumentoRepository.findByCodigoContar(tipodocumento.getCodigo()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
        }
        if (tipodocumentoRepository.findByValidarDuplicados(tipodocumento.getDescripcion()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
        }
        return tipodocumentoRepository.save(tipodocumento);
    }

    @Override
    public TipoDocumentoEntity update(TipoDocumentoEntity tipodocumento, Long id) throws Exception {

        TipoDocumentoEntity tipodocumentoDb = null;

        if (tipodocumentoRepository.searchById(id) > 0) {
            tipodocumentoDb = tipodocumentoRepository.findById(id).get();

            tipodocumento.setId(id);
            tipodocumento.setIdEstadoRegistro(tipodocumentoDb.getIdEstadoRegistro());
            tipodocumento.setIdUsuarioCreacion(tipodocumentoDb.getIdUsuarioCreacion());
            tipodocumento.setFechaCreacion(tipodocumentoDb.getFechaCreacion());
            tipodocumento.setIdUsuarioModificacion(tipodocumentoDb.getIdUsuarioModificacion());
            tipodocumento.setFechaModificacion(LocalDateTime.parse(general.fechaActual(2)));

            tipodocumento = aMayusculas(tipodocumento);

            if (!(tipodocumentoDb.getCodigo().equals(tipodocumento.getCodigo()))) {
                if (tipodocumentoRepository.findByCodigoContar(tipodocumento.getCodigo()) != 0) {
                    throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
                }
            }

            if (tipodocumentoRepository.findByValidarDuplicados(tipodocumento.getDescripcion()) != 0) {
                throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
            }

            return tipodocumentoRepository.save(tipodocumento);

        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
    }

    @Override
    public TipoDocumentoEntity deleteById(Long id) throws Exception {

        if (tipodocumentoRepository.searchById(id) > 0) {
            tipodocumentoRepository.deleteById(id);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
        return null;
    }

    public TipoDocumentoEntity aMayusculas(TipoDocumentoEntity tipodocumento) {
        tipodocumento.setCodigo(tipodocumento.getCodigo().toUpperCase());
        tipodocumento.setDescripcion(tipodocumento.getDescripcion().toUpperCase());

        return tipodocumento;
    }

    @Override
    public Long findByCodigoContar(String codigo) {
        return tipodocumentoRepository.findByCodigoContar(codigo);
    }

    @Override
    public Long findByValidarDuplicados(String nombreCompleto) {
        return tipodocumentoRepository.findByValidarDuplicados(nombreCompleto);
    }

    @Override
    public Long searchById(Long id) {
        return tipodocumentoRepository.searchById(id);
    }
}
