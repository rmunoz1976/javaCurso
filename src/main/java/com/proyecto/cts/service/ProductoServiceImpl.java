package com.proyecto.cts.service;

import com.proyecto.cts.entity.ProductoEntity;
import com.proyecto.cts.repository.ProductoRepository;
import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.GeneralValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    GeneralValidation general = new GeneralValidation();
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoEntity> list() {
        return (List<ProductoEntity>) productoRepository.findAll();
    }

    public ProductoEntity save(ProductoEntity producto) throws Exception {

        producto = aMayusculas(producto);
        producto.setFechaCreacion(LocalDateTime.parse(general.fechaActual(2)));
        producto.setFechaModificacion(producto.getFechaCreacion());

        if (productoRepository.findByCodigoContar(producto.getCodigo()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
        }
        if (productoRepository.findByValidarDuplicadosCorto(producto.getNombreCorto()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1004.getErrorNumero());
        }
        if (productoRepository.findByValidarDuplicadosLargo(producto.getNombreLargo()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1005.getErrorNumero());
        }
        return productoRepository.save(producto);
    }

    @Override
    public ProductoEntity update(ProductoEntity producto, Long id) throws Exception {

        ProductoEntity productoDb = null;

        if (productoRepository.searchById(id) > 0) {
            productoDb = productoRepository.findById(id).get();

            producto.setId(id);
            producto.setIdEstadoRegistro(productoDb.getIdEstadoRegistro());
            producto.setIdUsuarioCreacion(productoDb.getIdUsuarioCreacion());
            producto.setFechaCreacion(productoDb.getFechaCreacion());
            producto.setIdUsuarioModificacion(productoDb.getIdUsuarioModificacion());
            producto.setFechaModificacion(LocalDateTime.parse(general.fechaActual(2)));

            producto = aMayusculas(producto);

            if (!(productoDb.getCodigo().equals(producto.getCodigo()))) {
                if (productoRepository.findByCodigoContar(producto.getCodigo()) != 0) {
                    throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
                }
            }
            if (productoRepository.findByValidarDuplicadosCorto(producto.getNombreCorto()) != 0) {
                throw new Exception(EnumMsgstatus.ERR1004.getErrorNumero());
            }
            if (productoRepository.findByValidarDuplicadosLargo(producto.getNombreLargo()) != 0) {
                throw new Exception(EnumMsgstatus.ERR1005.getErrorNumero());
            }
            return productoRepository.save(producto);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
    }

    @Override
    public ProductoEntity deleteById(Long id) throws Exception {

        if (productoRepository.searchById(id) > 0) {
            productoRepository.deleteById(id);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
        return null;
    }

    public ProductoEntity aMayusculas(ProductoEntity producto) {
        producto.setCodigo(producto.getCodigo().toUpperCase());
        producto.setNombreCorto(producto.getNombreCorto().toUpperCase());
        producto.setNombreLargo(producto.getNombreLargo().toUpperCase());
        return producto;
    }

    @Override
    public Long findByCodigoContar(String codigo) {
        return productoRepository.findByCodigoContar(codigo);
    }

    @Override
    public Long findByValidarDuplicadosCorto(String nombreCorto) {
        return productoRepository.findByValidarDuplicadosLargo(nombreCorto);
    }

    @Override
    public Long findByValidarDuplicadosLargo(String nombreLargo) {
        return productoRepository.findByValidarDuplicadosLargo(nombreLargo);
    }
}
