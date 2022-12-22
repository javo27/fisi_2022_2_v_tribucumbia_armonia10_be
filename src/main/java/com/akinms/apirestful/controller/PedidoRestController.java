package com.akinms.apirestful.controller;

import com.akinms.apirestful.business.IPedidoBusiness;
import com.akinms.apirestful.entity.DetallePedido;
import com.akinms.apirestful.entity.Fechas;
import com.akinms.apirestful.entity.Pedido;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;
import com.akinms.apirestful.responseentity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/atencioncliente/v1/pedidos")
public class PedidoRestController {

    @Autowired
    private IPedidoBusiness pedidoBusiness;

    @GetMapping("/consultar/cliente/{id}")
    public ResponseEntity<RespuestaPedido> listPedidosCliente(@PathVariable Long id){
        RespuestaPedido respuestaPedido = new RespuestaPedido();
        try{
            List<Pedido> pedidosCliente = pedidoBusiness.getPedidosCliente(id);
            List<Pedidos> pedidosClienteResponse = new ArrayList<>();
            //List<DetallePedidos> detallesResponse = new ArrayList<>();
            for(Pedido p : pedidosCliente){
                Pedidos ped = new Pedidos();
                List<DetallePedidos> detallesResponse = new ArrayList<>();
                ped.setIdpedido(p.getIdpedido());
                for(DetallePedido dt : p.getDetallesPedido()){
                    System.out.println("CANTIDAD DE PRODUCTOS DETALLE: "+p.getDetallesPedido().size());
                    DetallePedidos det = new DetallePedidos();
                    det.setPedido("00");
                    det.setCantidad(dt.getCantidad());
                    det.setProducto(dt.getProducto());
                    detallesResponse.add(det);
                }
                ped.setDetallesPedido(detallesResponse);
                ped.setEstado(p.getEstado());
                ped.setFecha(p.getFecha().toString().substring(0,10));
                ped.setMetodo_pago(p.getMetodo_pago());
                ped.setMonto_total(p.getMonto_total());
                ped.setTipo_entrega(p.getTipo_entrega());
                BodegaUbicacion bu = new BodegaUbicacion();
                bu.setNombre(p.getBodega().getNombre());
                bu.setIdbodega(p.getBodega().getIdbodega());
                bu.setLongitud(p.getBodega().getUbicacion().getLongitud());
                bu.setLatitud(p.getBodega().getUbicacion().getLatitud());
                bu.setDireccion(p.getBodega().getUbicacion().getNombre());
                ped.setBodega(bu);
                pedidosClienteResponse.add(ped);
            }
            if(pedidosClienteResponse.size()>0)
                respuestaPedido.setMensaje("Cantidad de pedidos: "+pedidosClienteResponse.size());
            else
                respuestaPedido.setMensaje("No Tiene pedidos registrados");
            respuestaPedido.setPedidos(pedidosClienteResponse);
            return new ResponseEntity<>(respuestaPedido,HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/consultar/bodega/{id}")
    public ResponseEntity<RespuestaPedido> listPedidosBodega(@PathVariable Long id){
        RespuestaPedido respuestaPedido = new RespuestaPedido();
        try{
            List<Pedido> pedidosBodega = pedidoBusiness.getPedidosBodega(id);
            if(pedidosBodega.size()>0)
                respuestaPedido.setMensaje("Cantidad de pedidos: "+pedidosBodega.size());
            else
                respuestaPedido.setMensaje("No Tiene pedidos registrados");
            respuestaPedido.setPedidos(pedidosBodega);
            return new ResponseEntity<>(respuestaPedido,HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/consultar/ventas/bodega/{id}/fechas")
    public ResponseEntity<RespuestaVentas> getVentasSemanales(@PathVariable Long id, @RequestParam String fecha_inicio, @RequestParam String fecha_fin){
        RespuestaVentas respuestaVentas = new RespuestaVentas();
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //Ventas ventasSemanales = pedidoBusiness.getVentasSemanes(sdf.parse(fecha_inicio),sdf.parse(fecha_fin),id);
            List<Pedido> ventasSemanales = pedidoBusiness.getVentasSemanes(fecha_inicio,fecha_fin,id);
            List<Ventas> ventas = new ArrayList<>();
            for(Pedido p : ventasSemanales){
                Ventas v = new Ventas();
                v.setMonto(p.getMonto_total());
                String feccha = p.getFecha().toString();
                v.setFecha(feccha.substring(0,10));
                ventas.add(v);
            }
            respuestaVentas.setMensaje("Ventas semanales en el rango: ["+fecha_inicio+" - "+fecha_fin+"]");
            respuestaVentas.setVentas(ventas);
            return new ResponseEntity<>(respuestaVentas,HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    @GetMapping("/")
    public ResponseEntity<CollectionModel<EntityModel<Pedido>>> listAll() {
    //public ResponseEntity<List<Categoria>> listAll(){
        try{
            List<EntityModel<Pedido>> pedidos = pedidoBusiness.listAll().stream()
                    .map(assemblerPedido::toModel)
                    .collect(Collectors.toList());
            CollectionModel<EntityModel<Pedido>> collection = CollectionModel.of(pedidos,
                    linkTo(methodOn(PedidoRestController.class).listAll()).withSelfRel());
            return new ResponseEntity<>(collection, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pedido>> show(@PathVariable Long id){
    //public ResponseEntity<Categoria> showCategory(@PathVariable Long id){
        try{
            Pedido pedido = pedidoBusiness.show(id);
            return new ResponseEntity<>(assemblerPedido.toModel(pedido),
                    HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Pedido pedido){
        try{
            EntityModel<Pedido> entityModel = assemblerPedido.toModel(pedidoBusiness.save(pedido));
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        }catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Pedido>> update(@PathVariable Long id, @RequestBody Pedido pedido){
        try {
            EntityModel<Pedido> entityModel = assemblerPedido.toModel(pedidoBusiness.update(id,pedido));
            //return new ResponseEntity<>(entityModel,HttpStatus.OK);
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            pedidoBusiness.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
}
