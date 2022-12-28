package com.proyecto.cts.service;

import com.proyecto.cts.entity.CompaniaEntity;
import com.proyecto.cts.zgeneral.EnumMsgstatus;
import com.proyecto.cts.zgeneral.GeneralValidation;
import com.proyecto.cts.repository.CompaniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompaniaServiceImpl implements CompaniaService {
    GeneralValidation general = new GeneralValidation();
    @Autowired
    private CompaniaRepository companiaRepository;

    @Override
    public List<CompaniaEntity> list() {
        return (List<CompaniaEntity>)
                companiaRepository.findAll();
    }

    public CompaniaEntity save(CompaniaEntity compania) throws Exception {

        compania = aMayusculas(compania);
        compania.setFecha(LocalDate.parse(general.fechaActual(1)));

        if (companiaRepository.findByCodigoContar(compania.getCodigo()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
        }
        if (companiaRepository.findByValidarDuplicados(compania.getCodigo(), compania.getNit(),
                compania.getNombreCorto(), compania.getNombreLargo(), compania.getSigla()) != 0) {
            throw new Exception(EnumMsgstatus.ERR1999.getErrorNumero());
        }

        return companiaRepository.save(compania);
    }

    @Override
    public CompaniaEntity update(CompaniaEntity compania, Long id) throws Exception {

        CompaniaEntity companiaDb = null;

        if (companiaRepository.searchById(id) > 0) {
            companiaDb = companiaRepository.findById(id).get();

            compania.setId(id);
            compania = aMayusculas(compania);
            compania.setFecha(LocalDate.parse(general.fechaActual(1)));

            if (!(companiaDb.getCodigo().equals(compania.getCodigo()))) {
                if (companiaRepository.findByCodigoContar(compania.getCodigo()) != 0) {
                    throw new Exception(EnumMsgstatus.ERR1001.getErrorNumero());
                }
            }

            if (companiaRepository.findByValidarDuplicados(compania.getCodigo(), compania.getNit(), compania.getNombreCorto(), compania.getNombreLargo(), compania.getSigla()) != 0) {
                throw new Exception(EnumMsgstatus.ERR1999.getErrorNumero());
            }

            return companiaRepository.save(compania);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
    }

    @Override
    public CompaniaEntity deleteById(Long id) throws Exception {

        if (companiaRepository.searchById(id) > 0) {
            companiaRepository.deleteById(id);
        } else {
            throw new Exception(EnumMsgstatus.ERR1998.getErrorNumero());
        }
        return null;
    }

    @Override
    public CompaniaEntity aMayusculas(CompaniaEntity compania) {

        compania.setCodigo(compania.getCodigo().toUpperCase());
        compania.setNombreCorto(compania.getNombreCorto().toUpperCase());
        compania.setNombreLargo(compania.getNombreLargo().toUpperCase());
        compania.setSigla(compania.getSigla().toUpperCase());

        return compania;
    }

    @Override
    public String findByCodigo(String codigo) {
        return companiaRepository.findByCodigo(codigo);
    }

    @Override
    public Long findByCodigoContar(String codigo) {
        return companiaRepository.findByCodigoContar(codigo);
    }

    @Override
    public Long findByValidarDuplicados(String codigo, Long nit, String nombreCorto, String nombreLargo, String sigla) {
        return companiaRepository.findByValidarDuplicados(codigo, nit, nombreCorto, nombreLargo, sigla);
    }

}
