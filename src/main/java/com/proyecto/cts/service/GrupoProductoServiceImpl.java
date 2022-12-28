package com.proyecto.cts.service;

import com.proyecto.cts.entity.GrupoProductoEntity;
import com.proyecto.cts.repository.GrupoProductoRepository;
import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.GeneralValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GrupoProductoServiceImpl implements GrupoProductoService {
    GeneralValidation general = new GeneralValidation();
    @Autowired
    private GrupoProductoRepository grupoproductoRepository;

    @Override
    public List<GrupoProductoEntity> list() {
        return (List<GrupoProductoEntity>) grupoproductoRepository.findAll();
    }

    public GrupoProductoEntity save(GrupoProductoEntity grupoproducto) throws Exception {

        grupoproducto = aMayusculas(grupoproducto);
        grupoproducto.setFechaCreacion(LocalDateTime.parse(general.fechaActual(2)));
        grupoproducto.setFechaModificacion(grupoproducto.getFechaCreacion());

        if (grupoproductoRepository.findByCodigoContar(grupoproducto.getCodigo()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
        }
        if (grupoproductoRepository.findByValidarDuplicados(grupoproducto.getDescripcion()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
        }
        return grupoproductoRepository.save(grupoproducto);
    }

    @Override
    public GrupoProductoEntity update(GrupoProductoEntity grupoproducto, Long id) throws Exception {

        GrupoProductoEntity grupoproductoDb = null;

        if (grupoproductoRepository.searchById(id) > 0) {
            grupoproductoDb = grupoproductoRepository.findById(id).get();

            grupoproducto.setId(id);
            grupoproducto.setIdEstadoRegistro(grupoproductoDb.getIdEstadoRegistro());
            grupoproducto.setIdUsuarioCreacion(grupoproductoDb.getIdUsuarioCreacion());
            grupoproducto.setFechaCreacion(grupoproductoDb.getFechaCreacion());
            grupoproducto.setIdUsuarioModificacion(grupoproductoDb.getIdUsuarioModificacion());
            grupoproducto.setFechaModificacion(LocalDateTime.parse(general.fechaActual(2)));

            grupoproducto = aMayusculas(grupoproducto);

            if (!(grupoproductoDb.getCodigo().equals(grupoproducto.getCodigo()))) {
                if (grupoproductoRepository.findByCodigoContar(grupoproducto.getCodigo()) != 0) {
                    throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
                }
            }

            if (grupoproductoRepository.findByValidarDuplicados(grupoproducto.getDescripcion()) != 0) {
                throw new Exception(EnumMsgstatus.ERR1002.getErrorNumero());
            }
            return grupoproductoRepository.save(grupoproducto);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
    }

    @Override
    public GrupoProductoEntity deleteById(Long id) throws Exception {

        if (grupoproductoRepository.searchById(id) > 0) {
            grupoproductoRepository.deleteById(id);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
        return null;
    }

    public GrupoProductoEntity aMayusculas(GrupoProductoEntity grupoproducto) {
        grupoproducto.setCodigo(grupoproducto.getCodigo().toUpperCase());
        grupoproducto.setDescripcion(grupoproducto.getDescripcion().toUpperCase());

        return grupoproducto;
    }

    @Override
    public Long findByCodigoContar(String codigo) {
        return grupoproductoRepository.findByCodigoContar(codigo);
    }

    @Override
    public Long findByValidarDuplicados(String nombreCompleto) {
        return grupoproductoRepository.findByValidarDuplicados(nombreCompleto);
    }
}
