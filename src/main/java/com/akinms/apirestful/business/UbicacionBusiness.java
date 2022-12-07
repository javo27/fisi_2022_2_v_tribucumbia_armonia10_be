package com.akinms.apirestful.business;

import com.akinms.apirestful.entity.Bodega;
import com.akinms.apirestful.entity.Ubicacion;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;
import com.akinms.apirestful.respository.BodegaRepository;
import com.akinms.apirestful.respository.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UbicacionBusiness implements IUbicacionBusiness{
    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Override
    public List<Ubicacion> listAll() throws BusinessException {
        try{
            return ubicacionRepository.findAll();
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    @Override
    public Ubicacion show(Long id) throws BusinessException, NotFoundException {
        Optional<Ubicacion> op;
        try{
            op = ubicacionRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro la bodega con el id "+id);
        }
        return op.get();
    }

    @Override
    public Ubicacion save(Ubicacion ubicacion) throws BusinessException{
        try{
            return ubicacionRepository.save(ubicacion);

        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Ubicacion update(Long id, Ubicacion ubicacion) throws BusinessException, NotFoundException{
        Optional<Ubicacion> op;
        try{
            op = ubicacionRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro la bodega con el id "+id);
        }
        else{
            try {
                op.map(ubicacionUpdate -> {
                    ubicacionUpdate.setNombre(ubicacion.getNombre());
                    ubicacionUpdate.setLatitud(ubicacion.getLatitud());
                    ubicacionUpdate.setLongitud(ubicacion.getLongitud());
                    return ubicacionRepository.save(ubicacionUpdate);
                });
            }
            catch (Exception e){
                System.out.println("error");
                throw new BusinessException(e.getMessage());
            }
        }
        ubicacion.setIdubicacion(id);
        return ubicacionRepository.save(ubicacion);
    }

    @Override
    public void remove(Long id) throws BusinessException, NotFoundException{
        Optional<Ubicacion> op;
        try{
            op = ubicacionRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro la bodega con el id "+id);
        }
        else{
            try {
                ubicacionRepository.deleteById(id);
            }
            catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }
}
