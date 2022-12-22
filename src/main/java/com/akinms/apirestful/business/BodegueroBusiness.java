package com.akinms.apirestful.business;

import com.akinms.apirestful.entity.Bodega;
import com.akinms.apirestful.entity.Bodeguero;
import com.akinms.apirestful.entity.Categoria;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;
import com.akinms.apirestful.respository.BodegaRepository;
import com.akinms.apirestful.respository.BodegueroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BodegueroBusiness implements IBodegueroBusiness{
    @Autowired
    private BodegueroRepository bodegueroRepository;
    @Autowired
    private BodegaRepository bodegaRepository;

    @Override
    public List<Bodeguero> listAll() throws BusinessException {
        try{
            return bodegueroRepository.findAll();
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Bodeguero show(Long id) throws BusinessException, NotFoundException {
        Optional<Bodeguero> op;
        try{
            op = bodegueroRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro el bodeguero con el id "+id);
        }
        return op.get();
    }

    @Override
    public Bodeguero save(Bodeguero bodeguero) throws BusinessException {
        try{
            Optional<Bodega> op;
            if(bodeguero.getBodega()!=null){
                op = bodegaRepository.findById(bodeguero.getBodega().getIdbodega());
                if(!op.isPresent()){
                    throw new NotFoundException("No se encontro la bodega");
                }
                bodeguero.setBodega(op.get());
                return bodegueroRepository.save(bodeguero);
            }else{
                return bodegueroRepository.save(bodeguero);
            }

        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Bodeguero update(Long id, Bodeguero bodeguero) throws BusinessException, NotFoundException {
        Optional<Bodeguero> op;
        Optional<Bodega> op2;
        try{
            op = bodegueroRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro el bodeguero con el id "+id);
        }
        else{
            System.out.println("Id de la bodega:    "+bodeguero.getBodega().getIdbodega());
            if(bodeguero.getBodega()!=null){
                op2 = bodegaRepository.findById(bodeguero.getBodega().getIdbodega());
                if(!op2.isPresent()){
                    throw new NotFoundException("No se encontro la bodega con el id "+id);
                }
                else{
                    try {
                        op.map(bodegueroUpdate -> {
                            bodegueroUpdate.setNombres(bodeguero.getNombres());
                            bodegueroUpdate.setApellidos(bodeguero.getApellidos());
                            bodegueroUpdate.setTelefono(bodeguero.getTelefono());
                            bodegueroUpdate.setBodega(op2.get());
                            bodeguero.setBodega(op2.get());
                            return bodegueroRepository.save(bodegueroUpdate);
                        });
                    }
                    catch (Exception e){
                        System.out.println("error");
                        throw new BusinessException(e.getMessage());
                    }
                }
            }
        }
        System.out.println("Actualizando");
        bodeguero.setIdbodeguero(id);
        return bodegueroRepository.save(bodeguero);
    }

    @Override
    public void remove(Long id) throws BusinessException, NotFoundException {
        Optional<Bodeguero> op;
        try{
            op = bodegueroRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro el bodeguero con el id "+id);
        }
        else{
            try {
                bodegueroRepository.deleteById(id);
            }
            catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }
}
