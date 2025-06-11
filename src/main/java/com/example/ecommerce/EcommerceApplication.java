package com.example.ecommerce;

import com.example.ecommerce.controllers.DetalleOrdenCompraController;
import com.example.ecommerce.entities.*;
import com.example.ecommerce.entities.enums.Rol;
import com.example.ecommerce.entities.enums.Sexo;
import com.example.ecommerce.entities.enums.TipoProducto;
import com.example.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class EcommerceApplication {
   @Autowired
   private ProvinciaRepository provinciaRepository;
   @Autowired
   private LocalidadRepository localidadRepository;
   @Autowired
   private DireccionRepository direccionRepository;
   @Autowired
   private UsuarioRepository usuarioRepository;
   @Autowired
   private CategoriaRepository categoriaRepository;
   @Autowired
   private ProductoRepository productoRepository;
   @Autowired
   private DetalleProductoRepository detalleProductoRepository;
   @Autowired
   private DescuentoRepository descuentoRepository;
   @Autowired
   private TalleRepository talleRepository;
   @Autowired
   private OrdenCompraRepository ordenCompraRepository;
   @Autowired
   private DetalleOrdenCompraController detalleOrdenCompraController;
   @Autowired
   private ImagenRepository imagenRepository;
   @Autowired
   private StockRepository stockRepository;

   @Bean
   @Transactional
   CommandLineRunner init(ProvinciaRepository provinciaRepository,
                          LocalidadRepository localidadRepository,
                          DireccionRepository direccionRepository,
                          UsuarioRepository usuarioRepository,
                          ProductoRepository productoRepository,
                          DetalleProductoRepository detalleProductoRepository,
                          ImagenRepository imagenRepository,
                          CategoriaRepository categoriaRepository,
                          DescuentoRepository descuentoRepository,
                          TalleRepository talleRepository,
                          StockRepository stockRepository,
                          OrdenCompraRepository ordenCompraRepository,
                          DetalleOrdenCompraController detalleOrdenCompraController,
                          PasswordEncoder passwordEncoder) {
      return args -> {
         // ------------- Provincias --------------- //
         Provincia mendoza = Provincia.builder().nombre("mendoza").build();
         Provincia cordoba = Provincia.builder().nombre("cordoba").build();
         Provincia santaFe = Provincia.builder().nombre("santa fe").build();
         Provincia salta = Provincia.builder().nombre("salta").build();

         provinciaRepository.save(mendoza);
         provinciaRepository.save(cordoba);
         provinciaRepository.save(santaFe);
         provinciaRepository.save(salta);

         // -------------- Localidades ---------------- //
         Localidad lujan = Localidad.builder().nombre("lujan de cuyo").provincia(mendoza).build();
         Localidad godoyCruz = Localidad.builder().nombre("godoy Cruz").provincia(mendoza).build();
         Localidad carlos_paz = Localidad.builder().nombre("carlos paz").provincia(cordoba).build();
         Localidad rioCuarto = Localidad.builder().nombre("rio cuarto").provincia(cordoba).build();
         Localidad rosario = Localidad.builder().nombre("rosario").provincia(santaFe).build();
         Localidad rafaela = Localidad.builder().nombre("rafaela").provincia(santaFe).build();

         localidadRepository.save(lujan);
         localidadRepository.save(carlos_paz);
         localidadRepository.save(godoyCruz);
         localidadRepository.save(rioCuarto);
         localidadRepository.save(rosario);
         localidadRepository.save(rafaela);

         // --------------- Direcciones ----------------- //
         Direccion direccion_mendoza = Direccion.builder().domicilio("san martín 1500")
               .localidad(lujan).build();
         Direccion direccionGodoyCruz = Direccion.builder().domicilio("balcarce 230").localidad(godoyCruz)
               .build();
         Direccion direccionVillaCarlosPaz = Direccion.builder().domicilio("general paz 100").localidad(carlos_paz)
               .build();
         Direccion direccionRioCuarto = Direccion.builder().domicilio("colon 750").localidad(rioCuarto)
               .build();
         Direccion direccionRosario = Direccion.builder().domicilio("avenida pellegrini 1200").localidad(rosario)
               .build();
         Direccion direccionRafaela = Direccion.builder().domicilio("bulevar lehmann 800").localidad(rafaela)
               .build();

         direccionRepository.save(direccion_mendoza);
         direccionRepository.save(direccionVillaCarlosPaz);
         direccionRepository.save(direccionGodoyCruz);
         direccionRepository.save(direccionRioCuarto);
         direccionRepository.save(direccionRosario);
         direccionRepository.save(direccionRafaela);

         // --------------- Usuarios ------------------- //
         Usuario usuario1 = Usuario.builder()
               .nombre("Roberto").apellido("Carlos").dni(467890123).rol(Rol.USUARIO)
               .username("correo@gmail.com").password(passwordEncoder.encode("sdsdsdsd"))
               .direccion(direccion_mendoza).build();
         Usuario usuario2 = Usuario.builder()
               .nombre("Rodrigo").apellido("Mora").dni(34789123).rol(Rol.USUARIO)
               .username("correo2@gmail.com").password(passwordEncoder.encode("contraseña"))
               .direccion(direccionVillaCarlosPaz).build();
         Usuario admin1 = Usuario.builder()
               .nombre("Ernesto").apellido("Sabato").dni(24789123).rol(Rol.ADMIN)
               .username("eladmin123@gmail.com").password(passwordEncoder.encode("adminquebuentipo"))
               .direccion(direccion_mendoza).build();

         usuarioRepository.save(usuario1);
         usuarioRepository.save(usuario2);
         usuarioRepository.save(admin1);

         // -------------- Categoria ----------------- //
         Categoria running = Categoria.builder().nombre("Running").build();
         Categoria urbano = Categoria.builder().nombre("Urbano").build();
         Categoria training = Categoria.builder().nombre("Training").build();
         Categoria futbol = Categoria.builder().nombre("Futbol").build();

         categoriaRepository.save(running);
         categoriaRepository.save(urbano);
         categoriaRepository.save(training);
         categoriaRepository.save(futbol);

         // ----------------- Productos ------------------ //
         Producto producto1 = Producto.builder()
               .nombre("Zapatillas Speedcat OG").tipoProducto(TipoProducto.CALZADO)
               .sexo(Sexo.HOMBRE).categorias(List.of(urbano)).build();
         Producto product1 = Producto.builder().nombre("ZAPATILLAS ADIDAS ULTRABOOST").tipoProducto(TipoProducto.CALZADO)
               .sexo(Sexo.HOMBRE).categorias(List.of(urbano, running)).build();
         Producto producto2 = Producto.builder()
               .nombre("Zapatillas PUMA x HELLO KITTY Suede XL").tipoProducto(TipoProducto.CALZADO)
               .sexo(Sexo.MUJER).categorias(List.of(urbano, running)).build();
         Producto product2 = Producto.builder().nombre("CAMISETA NIKE ESSENTIALS").tipoProducto(TipoProducto.ROPA)
               .sexo(Sexo.MUJER).categorias(List.of(training, urbano)).build();
         Producto producto3 = Producto.builder()
               .nombre("Buzo WARDROBE Essentials").tipoProducto(TipoProducto.ROPA)
               .sexo(Sexo.HOMBRE).categorias(List.of(training)).build();
         Producto pumaRemeraHombre = Producto.builder().nombre("PUMA ESSENTIALS LOGO TEE").tipoProducto(TipoProducto.ROPA)
               .sexo(Sexo.HOMBRE).categorias(List.of(training, urbano)).build();
         Producto producto4 = Producto.builder()
               .nombre("Campera puffer oversize").tipoProducto(TipoProducto.ROPA)
               .sexo(Sexo.MUJER).categorias(List.of(urbano)).build();
         Producto pumaCalzasMujer = Producto.builder()
               .nombre("PUMA EVERSCULPT HIGH WAIST LEGGINGS").tipoProducto(TipoProducto.ROPA)
               .sexo(Sexo.MUJER).categorias(List.of(urbano, running)).build();
         Producto producto5 = Producto.builder()
               .nombre("Botines de fútbol FUTURE 8 ULTIMATE MxSG").tipoProducto(TipoProducto.CALZADO)
               .sexo(Sexo.HOMBRE).categorias(List.of(futbol)).build();
         Producto pumaCamperaHombre = Producto.builder()
               .nombre("PUMA TRAIN FAVORITE WOVEN JACKET").tipoProducto(TipoProducto.ROPA)
               .sexo(Sexo.MUJER).categorias(List.of(training, urbano)).build();
         Producto pumaRemeraMujer2 = Producto.builder()
               .nombre("PUMA TRAINING XT").tipoProducto(TipoProducto.CALZADO)
               .sexo(Sexo.MUJER).categorias(List.of(futbol, urbano)).build();

         productoRepository.save(producto1);
         productoRepository.save(producto2);
         productoRepository.save(producto3);
         productoRepository.save(producto4);
         productoRepository.save(producto5);
         productoRepository.save(product1);
         productoRepository.save(product2);
         productoRepository.save(pumaRemeraHombre);
         productoRepository.save(pumaRemeraMujer2);
         productoRepository.save(pumaCalzasMujer);
         productoRepository.save(pumaCamperaHombre);

         // -------------- Descuentos ---------------- //
         Descuento descuento5 = Descuento.builder().nombre("5%")
               .fechaInicio(LocalDate.of(2025, 6, 15))
               .fechaFin(LocalDate.of(2025, 6, 20))
               .porcentaje(5).build();
         Descuento descuento10 = Descuento.builder().nombre("10%")
               .fechaInicio(LocalDate.of(2025, 6, 1))
               .fechaFin(LocalDate.of(2025, 6, 20))
               .porcentaje(10).build();
         Descuento descuento15 = Descuento.builder().nombre("15%")
               .fechaInicio(LocalDate.of(2025, 6, 1))
               .fechaFin(LocalDate.of(2025, 6, 29))
               .porcentaje(15).build();
         Descuento descuento20 = Descuento.builder().nombre("20%")
               .fechaInicio(LocalDate.of(2025, 6, 1))
               .fechaFin(LocalDate.of(2025, 6, 28))
               .porcentaje(20).build();
         Descuento descuento25 = Descuento.builder().nombre("25%")
               .fechaInicio(LocalDate.of(2025, 6, 1))
               .fechaFin(LocalDate.of(2025, 6, 30))
               .porcentaje(25).build();
         Descuento descuento30 = Descuento.builder().nombre("30%")
               .fechaInicio(LocalDate.of(2025, 6, 1))
               .fechaFin(LocalDate.of(2025, 6, 26))
               .porcentaje(30).build();
         Descuento descuento0 = Descuento.builder().nombre("Sin descuento")
               .fechaInicio(LocalDate.of(2024, 5, 1))
               .fechaFin(LocalDate.of(2024, 5, 25))
               .porcentaje(0).build();

         descuentoRepository.save(descuento0);
         descuentoRepository.save(descuento10);
         descuentoRepository.save(descuento5);
         descuentoRepository.save(descuento15);
         descuentoRepository.save(descuento20);
         descuentoRepository.save(descuento25);
         descuentoRepository.save(descuento30);

         // ------------------ Imagenes ------------------ //
         Imagen imagen1 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746976155/speedcat_qvh3gy.jpg")
               .alt("Zapatilla Speedcat OG color negro").build();
         Imagen imagen2 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746976155/speedcat-2_qombn0.jpg")
               .alt("Zapatilla Speedcat OG color negro").build();
         Imagen imagen3 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746977987/hello_kitty_zepctk.jpg")
               .alt("Zapatillas PUMA x HELLO KITTY color negro").build();
         Imagen imagen4 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746977986/hello_kitty-2_ezmxie.jpg")
               .alt("Zapatillas PUMA x HELLO KITTY color negro").build();
         Imagen imagen5 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746979624/wardrobe-1_itzkxm.jpg")
               .alt("Buzo WARDROBE Essentials color negro").build();
         Imagen imagen6 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746979624/wardrobe_sntpop.jpg")
               .alt("Buzo WARDROBE Essentials color negro").build();
         Imagen imagen7 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746980439/puffer_ddt91y.jpg")
               .alt("Campera puffer oversize color beige").build();
         Imagen imagen8 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746980439/puffer-1_li1jwt.jpg")
               .alt("Campera puffer oversize color beige").build();
         Imagen imagen9 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746980888/future_8_ultimate-1_eylmr1.jpg")
               .alt("Botines de fútbol FUTURE 8 ULTIMATE").build();
         Imagen imagen10 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1746980887/future_8_ultimate_k8wrhd.jpg").build();
         Imagen imagen11 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599729/zapatilla_hombre_a1_z9i1vp.png")
               .alt("ZAPATILLAS ADIDAS ULTRABOOST color negro").build();
         Imagen imagen12 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599730/zapatilla_hombre_a2_hztl5w.png")
               .alt("ZAPATILLAS ADIDAS ULTRABOOST color negro").build();
         Imagen imagen13 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599730/zapatilla_hombre_a3_xz5vab.png")
               .alt("ZAPATILLAS ADIDAS ULTRABOOST color verde").build();
         Imagen imagen14 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599730/zapatilla_hombre_a4_yl149a.png")
               .alt("ZAPATILLAS ADIDAS ULTRABOOST color verde").build();
         Imagen imagen15 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599733/ropa_mujer_b3_koiqej.png")
               .alt("CAMISETA NIKE ESSENTIALS color negro").build();
         Imagen imagen16 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599733/ropa_mujer_b4_khipqc.png")
               .alt("CAMISETA NIKE ESSENTIALS color negro").build();
         Imagen imagen17 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599732/ropa_mujer_b1_puw3sd.png")
               .alt("CAMISETA NIKE ESSENTIALS color rosa").build();
         Imagen imagen18 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599733/ropa_mujer_b2_sp7lsl.png")
               .alt("CAMISETA NIKE ESSENTIALS color rosa").build();
         Imagen imagen19 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599734/ropa-hombre-b1_tvipbt.png")
               .alt("PUMA ESSENTIALS LOGO TEE color negro").build();
         Imagen imagen20 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599729/ropa-hombre-b2_xaled9.png")
               .alt("PUMA ESSENTIALS LOGO TEE color negro").build();
         Imagen imagen21 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599731/ropa_hombre_b3_fq1yjj.png")
               .alt("PUMA ESSENTIALS LOGO TEE color azul").build();
         Imagen imagen22 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599731/ropa_hombre_b4_jpbbkm.png")
               .alt("PUMA ESSENTIALS LOGO TEE color azul").build();
         Imagen imagen23 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599732/ropa_mujer_a1_ct0gaw.png")
               .alt("PUMA EVERSCULPT HIGH WAIST LEGGINGS color negro").build();
         Imagen imagen24 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599732/ropa_mujer_a2_cye71p.png")
               .alt("PUMA EVERSCULPT HIGH WAIST LEGGINGS color negro").build();
         Imagen imagen25 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599732/ropa_mujer_a3_hbrnz7.png")
               .alt("PUMA EVERSCULPT HIGH WAIST LEGGINGS color marron").build();
         Imagen imagen26 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599732/ropa_mujer_a4_fkqanx.png")
               .alt("PUMA EVERSCULPT HIGH WAIST LEGGINGS color marron").build();
         Imagen imagen27 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599733/ropa_mujer_c1_tprhcf.png")
               .alt("PUMA TRAIN FAVORITE WOVEN JACKET color azul").build();
         Imagen imagen28 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599733/ropa_mujer_c2_xm3csu.png")
               .alt("PUMA TRAIN FAVORITE WOVEN JACKET color azul").build();
         Imagen imagen29 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599733/ropa_mujer_c3_ohuhgv.png")
               .alt("PUMA TRAIN FAVORITE WOVEN JACKET color verde").build();
         Imagen imagen30 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599733/ropa_mujer_c4_lb4nlc.png")
               .alt("PUMA TRAIN FAVORITE WOVEN JACKET color verde").build();
         Imagen imagen31 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599731/zapatilla_mujer_b1_nh37hr.png")
               .alt("PUMA TRAINING XT color blanco").build();
         Imagen imagen32 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599730/zapatilla_Blanca_Mujer_A1_apemrb.png")
               .alt("PUMA TRAINING XT color blanco").build();
         Imagen imagen33 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599729/zapatilla_Blanca_Mujer_A4_vooxal.png")
               .alt("PUMA TRAINING XT color morado").build();
         Imagen imagen34 = Imagen.builder()
               .url("https://res.cloudinary.com/dk93fowya/image/upload/v1749599729/zapatilla_Blanca_Mujer_A3_qvlqkn.png")
               .alt("PUMA TRAINING XT color morado").build();

         imagenRepository.save(imagen1);
         imagenRepository.save(imagen2);
         imagenRepository.save(imagen3);
         imagenRepository.save(imagen4);
         imagenRepository.save(imagen5);
         imagenRepository.save(imagen6);
         imagenRepository.save(imagen7);
         imagenRepository.save(imagen8);
         imagenRepository.save(imagen9);
         imagenRepository.save(imagen10);
         imagenRepository.save(imagen11);
         imagenRepository.save(imagen12);
         imagenRepository.save(imagen13);
         imagenRepository.save(imagen14);
         imagenRepository.save(imagen15);
         imagenRepository.save(imagen16);
         imagenRepository.save(imagen17);
         imagenRepository.save(imagen18);
         imagenRepository.save(imagen19);
         imagenRepository.save(imagen20);
         imagenRepository.save(imagen21);
         imagenRepository.save(imagen22);
         imagenRepository.save(imagen23);
         imagenRepository.save(imagen24);
         imagenRepository.save(imagen25);
         imagenRepository.save(imagen26);
         imagenRepository.save(imagen27);
         imagenRepository.save(imagen28);
         imagenRepository.save(imagen29);
         imagenRepository.save(imagen30);
         imagenRepository.save(imagen31);
         imagenRepository.save(imagen32);
         imagenRepository.save(imagen33);
         imagenRepository.save(imagen34);

         // ---------------- Talles --------------- //
         Talle s = Talle.builder().name("S").build();
         Talle m = Talle.builder().name("M").build();
         Talle l = Talle.builder().name("L").build();
         Talle xl = Talle.builder().name("XL").build();
         Talle xxl = Talle.builder().name("XXL").build();
         Talle tallen1 = Talle.builder().name("36").build();
         Talle tallen2 = Talle.builder().name("37").build();
         Talle tallen3 = Talle.builder().name("38").build();
         Talle talle1 = Talle.builder().name("39").build();
         Talle talle2 = Talle.builder().name("40").build();
         Talle talle3 = Talle.builder().name("41").build();
         Talle talle4 = Talle.builder().name("42").build();

         talleRepository.save(l);
         talleRepository.save(xl);
         talleRepository.save(xxl);
         talleRepository.save(talle1);
         talleRepository.save(talle2);
         talleRepository.save(talle3);
         talleRepository.save(talle4);
         talleRepository.save(tallen1);
         talleRepository.save(tallen2);
         talleRepository.save(tallen3);
         talleRepository.save(s);
         talleRepository.save(m);

         // ----------------------- Detalle Producto --------------- //
         DetalleProducto speedcat_negro = DetalleProducto.builder()
               .color("Negro").activo(true).precioCompra(100000).precioVenta(120000)
               .producto(producto1).descuento(descuento10)
               .imagenes(List.of(imagen1, imagen2)).build();
         DetalleProducto hello_kitty = DetalleProducto.builder()
               .color("Negro").activo(true).precioCompra(130000).precioVenta(150000)
               .producto(producto2).descuento(descuento10)
               .imagenes(List.of(imagen3, imagen4)).build();
         DetalleProducto buzo_wardrobe = DetalleProducto.builder()
               .color("Negro").activo(true).precioCompra(140000).precioVenta(160000)
               .producto(producto3).descuento(descuento5)
               .imagenes(List.of(imagen5, imagen6)).build();
         DetalleProducto campera_puffer = DetalleProducto.builder()
               .color("Beige").activo(true).precioCompra(170000).precioVenta(180000)
               .producto(producto4).descuento(descuento10)
               .imagenes(List.of(imagen7, imagen8)).build();
         DetalleProducto botines_future = DetalleProducto.builder()
               .color("Amarillo").activo(true).precioCompra(190000).precioVenta(220000)
               .producto(producto5).descuento(descuento10)
               .imagenes(List.of(imagen9, imagen10)).build();

         DetalleProducto ultraboost_negro = DetalleProducto.builder()
               .color("Negro").activo(true).precioCompra(200000).precioVenta(220000)
               .producto(product1).descuento(descuento20)
               .imagenes(List.of(imagen11, imagen12)).build();
         DetalleProducto ultraboost_verde = DetalleProducto.builder()
               .color("Verde").activo(true).precioCompra(210000).precioVenta(230000)
               .producto(product1).descuento(descuento20)
               .imagenes(List.of(imagen13, imagen14)).build();
         DetalleProducto ropa_mujer_negro = DetalleProducto.builder()
               .color("Negro").activo(true).precioCompra(150000).precioVenta(170000)
               .producto(product2).descuento(descuento15)
               .imagenes(List.of(imagen15, imagen16)).build();
         DetalleProducto ropa_mujer_rosa = DetalleProducto.builder()
               .color("Rosa").activo(true).precioCompra(160000).precioVenta(180000)
               .producto(product2).descuento(descuento15)
               .imagenes(List.of(imagen17, imagen18)).build();
         DetalleProducto ropa_hombre_negro = DetalleProducto.builder()
               .color("Negro").activo(true).precioCompra(170000).precioVenta(190000)
               .producto(pumaRemeraHombre).descuento(descuento15)
               .imagenes(List.of(imagen19, imagen20)).build();
         DetalleProducto ropa_hombre_azul = DetalleProducto.builder()
               .color("Azul").activo(true).precioCompra(180000).precioVenta(200000)
               .producto(pumaRemeraHombre).descuento(descuento25)
               .imagenes(List.of(imagen21, imagen22)).build();
         DetalleProducto legging_mujer_negro = DetalleProducto.builder()
               .color("Negro").activo(true).precioCompra(190000).precioVenta(210000)
               .producto(pumaCalzasMujer).descuento(descuento15)
               .imagenes(List.of(imagen23, imagen24)).build();
         DetalleProducto leggings_mujer_rosa = DetalleProducto.builder()
               .color("Marron").activo(true).precioCompra(200000).precioVenta(220000)
               .producto(pumaCalzasMujer).descuento(descuento15)
               .imagenes(List.of(imagen25, imagen26)).build();
         DetalleProducto jacket_mujer_azul = DetalleProducto.builder()
               .color("Azul").activo(true).precioCompra(210000).precioVenta(230000)
               .producto(pumaCamperaHombre).descuento(descuento10)
               .imagenes(List.of(imagen27, imagen28)).build();
         DetalleProducto jacket_mujer_negro = DetalleProducto.builder()
               .color("Verde").activo(true).precioCompra(220000).precioVenta(240000)
               .producto(pumaCamperaHombre).descuento(descuento30)
               .imagenes(List.of(imagen29, imagen30)).build();
         DetalleProducto training_mujer_blanco = DetalleProducto.builder()
               .color("Blanco").activo(true).precioCompra(230000).precioVenta(250000)
               .producto(pumaRemeraMujer2).descuento(descuento20)
               .imagenes(List.of(imagen31, imagen32)).build();
         DetalleProducto training_mujer_rosa = DetalleProducto.builder()
               .color("Morado").activo(true).precioCompra(240000).precioVenta(260000)
               .producto(pumaRemeraMujer2).descuento(descuento5)
               .imagenes(List.of(imagen33, imagen34)).build();

         // ------------------------ Stock ------------------------- //
         Stock speedcat_negro_1 = Stock.builder()
               .stock(10).talle(talle1).build();
         Stock speedcat_negro_2 = Stock.builder()
               .stock(10).talle(talle2).build();
         Stock hello_kitty_1 = Stock.builder()
               .stock(10).talle(talle2).build();
         Stock hello_kitty_2 = Stock.builder()
               .stock(10).talle(talle3).build();
         Stock buzo_wardrobe_1 = Stock.builder()
               .stock(10).talle(l).build();
         Stock buzo_wardrobe_2 = Stock.builder()
               .stock(10).talle(xl).build();
         Stock campera_puffer_1 = Stock.builder()
               .stock(10).talle(xl).build();
         Stock campera_puffer_2 = Stock.builder()
               .stock(10).talle(xxl).build();
         Stock botines_future_1 = Stock.builder()
               .stock(10).talle(talle1).build();
         Stock botines_future_2 = Stock.builder()
               .stock(10).talle(talle2).build();
         Stock ultra_negro = Stock.builder()
               .stock(10).talle(tallen3).build();
         Stock ultra_verde = Stock.builder()
               .stock(10).talle(tallen1).build();
         Stock ropa_negro = Stock.builder()
               .stock(10).talle(s).build();
         Stock ropa_rosa = Stock.builder()
               .stock(10).talle(m).build();
         Stock ropa_azul = Stock.builder()
               .stock(10).talle(s).build();
         Stock ropa_negro_2 = Stock.builder()
               .stock(10).talle(xl).build();
         Stock leggings_rosa = Stock.builder()
               .stock(10).talle(xl).build();
         Stock leggings_negro = Stock.builder()
               .stock(10).talle(l).build();
         Stock jacket_negro = Stock.builder()
               .stock(10).talle(xxl).build();
         Stock jacket_azul = Stock.builder()
               .stock(10).talle(xxl).build();
         Stock training_blanco = Stock.builder()
               .stock(10).talle(tallen2).build();
         Stock training_rosa = Stock.builder()
               .stock(10).talle(tallen3).build();

         speedcat_negro.setStocks(List.of(speedcat_negro_1, speedcat_negro_2));
         hello_kitty.setStocks(List.of(hello_kitty_1, hello_kitty_2));
         buzo_wardrobe.setStocks(List.of(buzo_wardrobe_1, buzo_wardrobe_2));
         campera_puffer.setStocks(List.of(campera_puffer_1, campera_puffer_2));
         botines_future.setStocks(List.of(botines_future_1, botines_future_2));
         ultraboost_negro.setStocks(List.of(ultra_negro));
         ultraboost_verde.setStocks(List.of(ultra_verde));
         ropa_mujer_negro.setStocks(List.of(ropa_negro));
         ropa_mujer_rosa.setStocks(List.of(ropa_rosa));
         ropa_hombre_azul.setStocks(List.of(ropa_azul));
         ropa_hombre_negro.setStocks(List.of(ropa_negro_2));
         leggings_mujer_rosa.setStocks(List.of(leggings_rosa));
         legging_mujer_negro.setStocks(List.of(leggings_negro));
         jacket_mujer_negro.setStocks(List.of(jacket_negro));
         jacket_mujer_azul.setStocks(List.of(jacket_azul));
         training_mujer_blanco.setStocks(List.of(training_blanco));
         training_mujer_rosa.setStocks(List.of(training_rosa));

         detalleProductoRepository.save(speedcat_negro);
         detalleProductoRepository.save(hello_kitty);
         detalleProductoRepository.save(buzo_wardrobe);
         detalleProductoRepository.save(campera_puffer);
         detalleProductoRepository.save(botines_future);
         detalleProductoRepository.save(ultraboost_negro);
         detalleProductoRepository.save(ultraboost_verde);
         detalleProductoRepository.save(ropa_mujer_negro);
         detalleProductoRepository.save(ropa_mujer_rosa);
         detalleProductoRepository.save(ropa_hombre_azul);
         detalleProductoRepository.save(ropa_hombre_negro);
         detalleProductoRepository.save(leggings_mujer_rosa);
         detalleProductoRepository.save(legging_mujer_negro);
         detalleProductoRepository.save(jacket_mujer_negro);
         detalleProductoRepository.save(jacket_mujer_azul);
         detalleProductoRepository.save(training_mujer_blanco);
         detalleProductoRepository.save(training_mujer_rosa);
      };
   }

   public static void main(String[] args) {
      SpringApplication.run(EcommerceApplication.class, args);
   }
}
