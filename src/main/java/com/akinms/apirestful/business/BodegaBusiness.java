package com.akinms.apirestful.business;

import com.akinms.apirestful.entity.Bodega;
import com.akinms.apirestful.entity.Cliente;
import com.akinms.apirestful.entity.Ubicacion;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;
import com.akinms.apirestful.respository.BodegaRepository;
import com.akinms.apirestful.respository.ClienteRepository;
import com.akinms.apirestful.respository.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BodegaBusiness implements IBodegaBusiness{
    @Autowired
    private BodegaRepository bodegaRepository;
    @Autowired
    private UbicacionRepository ubicacionRepository;
    @Autowired
    private IUbicacionBusiness ubicacionBusiness;

    @Override
    public List<Bodega> listAll() throws BusinessException {
        try{
            return bodegaRepository.findAll();
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    @Override
    public Bodega show(Long id) throws BusinessException, NotFoundException {
        Optional<Bodega> op;
        try{
            op = bodegaRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro la bodega con el id "+id);
        }
        return op.get();
    }

    @Override
    public Bodega save(Bodega bodega) throws BusinessException{
        try{
            if(bodega.getUbicacion()!=null){
                Ubicacion u = ubicacionBusiness.save(bodega.getUbicacion());
                bodega.setUbicacion(u);
                return bodegaRepository.save(bodega);
            }else{
                throw new BusinessException("Debe ingresar una ubicacion");
            }

        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Bodega update(Long id, Bodega bodega) throws BusinessException, NotFoundException{
        Optional<Bodega> op;
        Optional<Ubicacion> ou;
        try{
            op = bodegaRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro la bodega con el id "+id);
        }
        else{
            try {
                if(bodega.getUbicacion()!=null){
                    Ubicacion u;
                    ou = ubicacionRepository.findById(bodega.getUbicacion().getIdubicacion());
                    if(!ou.isPresent()){
                        throw new NotFoundException("No se encontro la ubicacion que se quiere actualizar");
                    }
                    else{
                        u = ubicacionBusiness.update(bodega.getUbicacion().getIdubicacion(),bodega.getUbicacion());
                    }
                    bodega.setUbicacion(u);
                    op.map(bodegaUpdate -> {
                        bodegaUpdate.setNombre(bodega.getNombre());
                        bodegaUpdate.setUbicacion(bodega.getUbicacion());
                        return bodegaRepository.save(bodegaUpdate);
                    });
                }
            }
            catch (Exception e){
                System.out.println("error");
                throw new BusinessException(e.getMessage());
            }
        }
        bodega.setIdbodega(id);
        return bodegaRepository.save(bodega);
    }

    @Override
    public void remove(Long id) throws BusinessException, NotFoundException{
        Optional<Bodega> op;
        try{
            op = bodegaRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro la bodega con el id "+id);
        }
        else{
            try {
                bodegaRepository.deleteById(id);
            }
            catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }

}
