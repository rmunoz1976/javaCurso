package com.proyecto.cts.service;

import com.proyecto.cts.entity.MonedaEntity;
import com.proyecto.cts.repository.MonedaRepository;
import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.GeneralValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MonedaServiceImpl implements MonedaService {
    GeneralValidation general = new GeneralValidation();
    @Autowired
    private MonedaRepository monedaRepository;

    @Override
    public List<MonedaEntity> list() {
        return (List<MonedaEntity>) monedaRepository.findAll();
    }

    public MonedaEntity save(MonedaEntity moneda) throws Exception {

        moneda = aMayusculas(moneda);
        moneda.setFechaCreacion(LocalDateTime.parse(general.fechaActual(2)));
        moneda.setFechaModificacion(moneda.getFechaCreacion());

        if (monedaRepository.findByCodigoContar(moneda.getCodigo()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
        }
        if (monedaRepository.findByValidarDuplicados(moneda.getDescripcion()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
        }
        return monedaRepository.save(moneda);
    }

    @Override
    public MonedaEntity update(MonedaEntity moneda, Long id) throws Exception {

        MonedaEntity monedaDb = null;

        if (monedaRepository.searchById(id) > 0) {
            monedaDb = monedaRepository.findById(id).get();

            moneda.setId(id);
            moneda.setIdEstadoRegistro(monedaDb.getIdEstadoRegistro());
            moneda.setIdUsuarioCreacion(monedaDb.getIdUsuarioCreacion());
            moneda.setFechaCreacion(monedaDb.getFechaCreacion());
            moneda.setIdUsuarioModificacion(monedaDb.getIdUsuarioModificacion());
            moneda.setFechaModificacion(LocalDateTime.parse(general.fechaActual(2)));

            moneda = aMayusculas(moneda);

            if (!(monedaDb.getCodigo().equals(moneda.getCodigo()))) {
                if (monedaRepository.findByCodigoContar(moneda.getCodigo()) != 0) {
                    throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
                }
            }

            if (monedaRepository.findByValidarDuplicados(moneda.getDescripcion()) != 0) {
                throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
            }
            return monedaRepository.save(moneda);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
    }

    @Override
    public MonedaEntity deleteById(Long id) throws Exception {

        if (monedaRepository.searchById(id) > 0) {
            monedaRepository.deleteById(id);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
        return null;
    }

    public MonedaEntity aMayusculas(MonedaEntity moneda) {
        moneda.setCodigo(moneda.getCodigo().toUpperCase());
        moneda.setDescripcion(moneda.getDescripcion().toUpperCase());

        return moneda;
    }

    @Override
    public Long findByCodigoContar(String codigo) {
        return monedaRepository.findByCodigoContar(codigo);
    }

    @Override
    public Long findByValidarDuplicados(String nombreCompleto) {
        return monedaRepository.findByValidarDuplicados(nombreCompleto);
    }
}
