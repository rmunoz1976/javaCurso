package com.proyecto.cts.service;

import com.proyecto.cts.entity.EmpresaEntity;
import com.proyecto.cts.repository.EmpresaRepository;
import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.GeneralValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpresaServiceImpl implements EmpresaService {
    GeneralValidation general = new GeneralValidation();
    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public List<EmpresaEntity> list() {
        return (List<EmpresaEntity>)
                empresaRepository.findAll();
    }

    public EmpresaEntity save(EmpresaEntity empresa) throws Exception {

        empresa = aMayusculas(empresa);
        empresa.setFechaCreacion(LocalDateTime.parse(general.fechaActual(2)));
        empresa.setFechaModificacion(empresa.getFechaCreacion());

        if (empresaRepository.findByCodigoContar(empresa.getCodigo()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
        }
        if (empresaRepository.findByValidarDuplicados(empresa.getCodigo(), empresa.getNit(),
                empresa.getNombreCorto(), empresa.getNombreLargo(), empresa.getSigla()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1999.getErrorNumero());
        }

        return empresaRepository.save(empresa);
    }

    @Override
    public EmpresaEntity update(EmpresaEntity empresa, Long id) throws Exception {

        EmpresaEntity empresaDb = null;

        if (empresaRepository.searchById(id) > 0) {
            empresaDb = empresaRepository.findById(id).get();

            empresa.setId(id);
            empresa = aMayusculas(empresa);
            empresa.setEstadoEmpresa(empresaDb.getEstadoEmpresa());
            empresa.setIdUsuarioCreacion(empresaDb.getIdUsuarioCreacion());
            empresa.setFechaCreacion(empresaDb.getFechaCreacion());
            empresa.setIdUsuarioModificacion(empresaDb.getIdUsuarioModificacion());
            empresa.setFechaModificacion(LocalDateTime.parse(general.fechaActual(2)));

            if (!(empresaDb.getCodigo().equals(empresa.getCodigo()))) {
                if (empresaRepository.findByCodigoContar(empresa.getCodigo()) != 0) {
                    throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
                }
            }

            if (empresaRepository.findByValidarDuplicados(empresa.getCodigo(), empresa.getNit(), empresa.getNombreCorto(), empresa.getNombreLargo(), empresa.getSigla()) != 0) {
                throw new Exception(EnumMsgstatus.ERR1999.getErrorNumero());
            }

            return empresaRepository.save(empresa);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
    }

    @Override
    public EmpresaEntity deleteById(Long id) throws Exception {

        if (empresaRepository.searchById(id) > 0) {
            empresaRepository.deleteById(id);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
        return null;
    }

    @Override
    public EmpresaEntity aMayusculas(EmpresaEntity empresa) {

        empresa.setCodigo(empresa.getCodigo().toUpperCase());
        empresa.setNombreCorto(empresa.getNombreCorto().toUpperCase());
        empresa.setNombreLargo(empresa.getNombreLargo().toUpperCase());
        empresa.setSigla(empresa.getSigla().toUpperCase());

        return empresa;
    }

    @Override
    public String findByCodigo(String codigo) {
        return empresaRepository.findByCodigo(codigo);
    }

    @Override
    public Long findByCodigoContar(String codigo) {
        return empresaRepository.findByCodigoContar(codigo);
    }

    @Override
    public Long findByValidarDuplicados(String codigo, Long nit, String nombreCorto, String nombreLargo, String sigla) {
        return empresaRepository.findByValidarDuplicados(codigo, nit, nombreCorto, nombreLargo, sigla);
    }

}
