package JPA;

import JPA.DetalleCompra;
import JPA.DetalleFactura;
import JPA.Proveedor;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-14T21:18:59")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile ListAttribute<Producto, DetalleCompra> detalleCompraList;
    public static volatile SingularAttribute<Producto, Float> existencias;
    public static volatile SingularAttribute<Producto, String> marca;
    public static volatile SingularAttribute<Producto, Boolean> disponibilidad;
    public static volatile SingularAttribute<Producto, Float> precio;
    public static volatile SingularAttribute<Producto, String> unidadreferencia;
    public static volatile SingularAttribute<Producto, Proveedor> proveedorid;
    public static volatile SingularAttribute<Producto, String> tipoanimal;
    public static volatile SingularAttribute<Producto, Float> peso;
    public static volatile SingularAttribute<Producto, Integer> idProducto;
    public static volatile ListAttribute<Producto, DetalleFactura> detalleFacturaList;
    public static volatile SingularAttribute<Producto, String> nombre;

}