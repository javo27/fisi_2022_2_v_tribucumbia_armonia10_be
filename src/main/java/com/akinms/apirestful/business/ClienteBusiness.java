package com.akinms.apirestful.business;

import com.akinms.apirestful.entity.Categoria;
import com.akinms.apirestful.entity.Cliente;
import com.akinms.apirestful.entity.Producto;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;
import com.akinms.apirestful.respository.ClienteRepository;
import com.akinms.apirestful.respository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClienteBusiness implements IClienteBusiness{
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listAll() throws BusinessException {
        try{
            return clienteRepository.findAll();
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    @Override
    public Cliente show(Long id) throws BusinessException, NotFoundException {
        Optional<Cliente> op;
        try{
            op = clienteRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro el cliente con el id "+id);
        }
        return op.get();
    }

    @Override
    public Cliente save(Cliente cliente) throws BusinessException{
        try{
            return clienteRepository.save(cliente);

        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Cliente update(Long id, Cliente cliente) throws BusinessException, NotFoundException{
        Optional<Cliente> op;
        try{
            op = clienteRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro el cliente con el id "+id);
        }
        else{
            try {
                op.map(clienteUpdate -> {
                    clienteUpdate.setNombres(cliente.getNombres());
                    clienteUpdate.setApellidos(cliente.getApellidos());
                    clienteUpdate.setDireccion(cliente.getDireccion());
                    clienteUpdate.setTelefono(cliente.getTelefono());
                    //clienteUpdate.setIdcliente(cliente.getIdcliente());
                    return clienteRepository.save(clienteUpdate);
                });
            }
            catch (Exception e){
                System.out.println("error");
                throw new BusinessException(e.getMessage());
            }
        }
        cliente.setIdcliente(id);
        System.out.println("ultima");
        return clienteRepository.save(cliente);
    }

    @Override
    public void remove(Long id) throws BusinessException, NotFoundException{
        Optional<Cliente> op;
        try{
            op = clienteRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro el cliente con el id "+id);
        }
        else{
            try {
                clienteRepository.deleteById(id);
            }
            catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }
}
