package JPA;

import JPA.DetalleCompra;
import JPA.DetalleFactura;
import JPA.Proveedor;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2020-07-08T10:28:08", comments="EclipseLink-2.7.7.v20200504-rNA")
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