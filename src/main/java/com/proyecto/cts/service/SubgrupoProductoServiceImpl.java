package com.proyecto.cts.service;

import com.proyecto.cts.entity.SubgrupoProductoEntity;
import com.proyecto.cts.repository.SubgrupoProductoRepository;
import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.GeneralValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubgrupoProductoServiceImpl implements SubgrupoProductoService {
    GeneralValidation general = new GeneralValidation();
    @Autowired
    private SubgrupoProductoRepository subgrupoproductoRepository;

    @Override
    public List<SubgrupoProductoEntity> list() {
        return (List<SubgrupoProductoEntity>) subgrupoproductoRepository.findAll();
    }

    public SubgrupoProductoEntity save(SubgrupoProductoEntity subgrupoproducto) throws Exception {

        subgrupoproducto = aMayusculas(subgrupoproducto);
        subgrupoproducto.setFechaCreacion(LocalDateTime.parse(general.fechaActual(2)));
        subgrupoproducto.setFechaModificacion(subgrupoproducto.getFechaCreacion());

        if (subgrupoproductoRepository.findByCodigoContar(subgrupoproducto.getCodigo()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
        }
        if (subgrupoproductoRepository.findByValidarDuplicados(subgrupoproducto.getDescripcion()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
        }
        return subgrupoproductoRepository.save(subgrupoproducto);
    }

    @Override
    public SubgrupoProductoEntity update(SubgrupoProductoEntity subgrupoproducto, Long id) throws Exception {

        SubgrupoProductoEntity subgrupoproductoDb = null;

        if (subgrupoproductoRepository.searchById(id) > 0) {
            subgrupoproductoDb = subgrupoproductoRepository.findById(id).get();

            subgrupoproducto.setId(id);
            subgrupoproducto.setIdEstadoRegistro(subgrupoproductoDb.getIdEstadoRegistro());
            subgrupoproducto.setIdUsuarioCreacion(subgrupoproductoDb.getIdUsuarioCreacion());
            subgrupoproducto.setFechaCreacion(subgrupoproductoDb.getFechaCreacion());
            subgrupoproducto.setIdUsuarioModificacion(subgrupoproductoDb.getIdUsuarioModificacion());
            subgrupoproducto.setFechaModificacion(LocalDateTime.parse(general.fechaActual(2)));

            subgrupoproducto = aMayusculas(subgrupoproducto);

            if (!(subgrupoproductoDb.getCodigo().equals(subgrupoproducto.getCodigo()))) {
                if (subgrupoproductoRepository.findByCodigoContar(subgrupoproducto.getCodigo()) != 0) {
                    throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
                }
            }

            if (subgrupoproductoRepository.findByValidarDuplicados(subgrupoproducto.getDescripcion()) != 0) {
                throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
            }
            return subgrupoproductoRepository.save(subgrupoproducto);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
    }

    @Override
    public SubgrupoProductoEntity deleteById(Long id) throws Exception {

        if (subgrupoproductoRepository.searchById(id) > 0) {
            subgrupoproductoRepository.deleteById(id);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
        return null;
    }

    public SubgrupoProductoEntity aMayusculas(SubgrupoProductoEntity subgrupoproducto) {
        subgrupoproducto.setCodigo(subgrupoproducto.getCodigo().toUpperCase());
        subgrupoproducto.setDescripcion(subgrupoproducto.getDescripcion().toUpperCase());

        return subgrupoproducto;
    }

    @Override
    public Long findByCodigoContar(String codigo) {
        return subgrupoproductoRepository.findByCodigoContar(codigo);
    }

    @Override
    public Long findByValidarDuplicados(String nombreCompleto) {
        return subgrupoproductoRepository.findByValidarDuplicados(nombreCompleto);
    }
}
