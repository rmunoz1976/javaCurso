package com.proyecto.cts.service;

import com.proyecto.cts.entity.MunicipioEntity;
import com.proyecto.cts.repository.MunicipioRepository;
import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.GeneralValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MunicipioServiceImpl implements MunicipioService {
    GeneralValidation general = new GeneralValidation();
    @Autowired
    private MunicipioRepository municipioRepository;

    @Override
    public List<MunicipioEntity> list() {
        return (List<MunicipioEntity>) municipioRepository.findAll();
    }

    public MunicipioEntity save(MunicipioEntity municipio) throws Exception {

        municipio = aMayusculas(municipio);
        municipio.setFechaCreacion(LocalDateTime.parse(general.fechaActual(2)));
        municipio.setFechaModificacion(municipio.getFechaCreacion());

        if (municipioRepository.findByCodigoContar(municipio.getCodigo()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
        }
        if (municipioRepository.findByValidarDuplicados(municipio.getDescripcion()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
        }
        return municipioRepository.save(municipio);
    }

    @Override
    public MunicipioEntity update(MunicipioEntity municipio, Long id) throws Exception {

        MunicipioEntity municipioDb = null;

        if (municipioRepository.searchById(id) > 0) {
            municipioDb = municipioRepository.findById(id).get();

            municipio.setId(id);
            municipio.setIdEstadoRegistro(municipioDb.getIdEstadoRegistro());
            municipio.setIdUsuarioCreacion(municipioDb.getIdUsuarioCreacion());
            municipio.setFechaCreacion(municipioDb.getFechaCreacion());
            municipio.setIdUsuarioModificacion(municipioDb.getIdUsuarioModificacion());
            municipio.setFechaModificacion(LocalDateTime.parse(general.fechaActual(2)));

            municipio = aMayusculas(municipio);

            if (!(municipioDb.getCodigo().equals(municipio.getCodigo()))) {
                if (municipioRepository.findByCodigoContar(municipio.getCodigo()) != 0) {
                    throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
                }
            }

            if (municipioRepository.findByValidarDuplicados(municipio.getDescripcion()) != 0) {
                throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
            }
            return municipioRepository.save(municipio);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
    }

    @Override
    public MunicipioEntity deleteById(Long id) throws Exception {

        if (municipioRepository.searchById(id) > 0) {
            municipioRepository.deleteById(id);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
        return null;
    }

    public MunicipioEntity aMayusculas(MunicipioEntity municipio) {
        municipio.setCodigo(municipio.getCodigo().toUpperCase());
        municipio.setDescripcion(municipio.getDescripcion().toUpperCase());

        return municipio;
    }

    @Override
    public Long findByCodigoContar(String codigo) {
        return municipioRepository.findByCodigoContar(codigo);
    }

    @Override
    public Long findByValidarDuplicados(String nombreCompleto) {
        return municipioRepository.findByValidarDuplicados(nombreCompleto);
    }
}
