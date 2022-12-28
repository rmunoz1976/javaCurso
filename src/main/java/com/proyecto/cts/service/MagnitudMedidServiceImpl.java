package com.proyecto.cts.service;

import com.proyecto.cts.entity.MagnitudMedidaEntity;
import com.proyecto.cts.repository.MagnitudMedidRepository;
import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.GeneralValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MagnitudMedidServiceImpl implements MagnitudMedidaService {
    GeneralValidation general = new GeneralValidation();
    @Autowired
    private MagnitudMedidRepository magnitudmedidaRepository;

    @Override
    public List<MagnitudMedidaEntity> list() {
        return (List<MagnitudMedidaEntity>) magnitudmedidaRepository.findAll();
    }

    public MagnitudMedidaEntity save(MagnitudMedidaEntity magnitudmedida) throws Exception {

        magnitudmedida = aMayusculas(magnitudmedida);
        magnitudmedida.setFechaCreacion(LocalDateTime.parse(general.fechaActual(2)));
        magnitudmedida.setFechaModificacion(magnitudmedida.getFechaCreacion());

        if (magnitudmedidaRepository.findByCodigoContar(magnitudmedida.getCodigo()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
        }
        if (magnitudmedidaRepository.findByValidarDuplicados(magnitudmedida.getDescripcion()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
        }
        return magnitudmedidaRepository.save(magnitudmedida);
    }

    @Override
    public MagnitudMedidaEntity update(MagnitudMedidaEntity magnitudmedida, Long id) throws Exception {

        MagnitudMedidaEntity magnitudmedidaDb = null;

        if (magnitudmedidaRepository.searchById(id) > 0) {
            magnitudmedidaDb = magnitudmedidaRepository.findById(id).get();

            magnitudmedida.setId(id);
            magnitudmedida.setIdEstadoRegistro(magnitudmedidaDb.getIdEstadoRegistro());
            magnitudmedida.setIdUsuarioCreacion(magnitudmedidaDb.getIdUsuarioCreacion());
            magnitudmedida.setFechaCreacion(magnitudmedidaDb.getFechaCreacion());
            magnitudmedida.setIdUsuarioModificacion(magnitudmedidaDb.getIdUsuarioModificacion());
            magnitudmedida.setFechaModificacion(LocalDateTime.parse(general.fechaActual(2)));

            magnitudmedida = aMayusculas(magnitudmedida);

            if (!(magnitudmedidaDb.getCodigo().equals(magnitudmedida.getCodigo()))) {
                if (magnitudmedidaRepository.findByCodigoContar(magnitudmedida.getCodigo()) != 0) {
                    throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
                }
            }

            if (magnitudmedidaRepository.findByValidarDuplicados(magnitudmedida.getDescripcion()) != 0) {
                throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
            }
            return magnitudmedidaRepository.save(magnitudmedida);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
    }

    @Override
    public MagnitudMedidaEntity deleteById(Long id) throws Exception {

        if (magnitudmedidaRepository.searchById(id) > 0) {
            magnitudmedidaRepository.deleteById(id);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
        return null;
    }

    public MagnitudMedidaEntity aMayusculas(MagnitudMedidaEntity magnitudmedida) {
        magnitudmedida.setCodigo(magnitudmedida.getCodigo().toUpperCase());
        magnitudmedida.setDescripcion(magnitudmedida.getDescripcion().toUpperCase());

        return magnitudmedida;
    }

    @Override
    public Long findByCodigoContar(String codigo) {
        return magnitudmedidaRepository.findByCodigoContar(codigo);
    }

    @Override
    public Long findByValidarDuplicados(String nombreCompleto) {
        return magnitudmedidaRepository.findByValidarDuplicados(nombreCompleto);
    }
}
