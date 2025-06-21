package com.stanreybackend.stanreyapi.controller;

   import java.util.List;

   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.http.ResponseEntity;
   import org.springframework.web.bind.annotation.DeleteMapping;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.PathVariable;
   import org.springframework.web.bind.annotation.PostMapping;
   import org.springframework.web.bind.annotation.PutMapping;
   import org.springframework.web.bind.annotation.RequestBody;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.RestController;

   import com.stanreybackend.stanreyapi.DTO.CarritoDTO;
   import com.stanreybackend.stanreyapi.DTO.CarritoProductoDTO;
   import com.stanreybackend.stanreyapi.entity.Carrito;
   import com.stanreybackend.stanreyapi.entity.CarritoProducto;
   import com.stanreybackend.stanreyapi.service.CarritoProductoService;
   import com.stanreybackend.stanreyapi.service.CarritoService;

   @RestController
   @RequestMapping("/stanrey")
   public class CarritoController {

       @Autowired
       private CarritoService carritoService;

       @Autowired
       private CarritoProductoService carritoProductoService;

       @PostMapping("/carrito/save")
       public ResponseEntity<String> saveCarrito(@RequestBody CarritoDTO carritoDTO) {
           String carritoId = carritoService.addCarrito(carritoDTO);
           return ResponseEntity.ok(carritoId);
       }

       @GetMapping("/carrito/usuario/{id_usuario}")
       public ResponseEntity<Carrito> getCarritoByUsuarioId(@PathVariable Long id_usuario) {
           Carrito carrito = carritoService.findByUsuarioId(id_usuario);
           if (carrito != null) {
               return ResponseEntity.ok(carrito);
           }
           return ResponseEntity.notFound().build();
       }

       @PostMapping("/carrito/agregar-producto")
       public ResponseEntity<String> addCarritoProducto(@RequestBody CarritoProductoDTO carritoProductoDTO) {
           String carritoProductoId = carritoProductoService.addCarritoProducto(carritoProductoDTO);
           return ResponseEntity.ok(carritoProductoId);
       }

       @PutMapping("/carrito/producto/{id_carrito_producto}/cantidad/{cantidad}")
       public ResponseEntity<String> updateCarritoProductoCantidad(
           @PathVariable Long id_carrito_producto,
           @PathVariable Integer cantidad
       ) {
           String carritoProductoId = carritoProductoService.updateCarritoProductoCantidad(id_carrito_producto, cantidad);
           return ResponseEntity.ok(carritoProductoId);
       }

       @GetMapping("/carrito/productos/{id_carrito}")
       public ResponseEntity<List<CarritoProducto>> getProductosByCarritoId(@PathVariable Long id_carrito) {
           List<CarritoProducto> productos = carritoProductoService.findByCarritoId(id_carrito);
           return ResponseEntity.ok(productos);
       }

       @DeleteMapping("/carrito/eliminar-producto/{id_carrito_producto}")
       public ResponseEntity<String> deleteCarritoProducto(@PathVariable Long id_carrito_producto) {
           String deletedId = carritoProductoService.deleteByIdCarritoProducto(id_carrito_producto);
           if (deletedId != null) {
               return ResponseEntity.ok(deletedId);
           }
           return ResponseEntity.notFound().build();
       }
   }