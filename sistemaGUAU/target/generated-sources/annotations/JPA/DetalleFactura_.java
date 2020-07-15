package JPA;

import JPA.Factura;
import JPA.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-14T21:18:59")
@StaticMetamodel(DetalleFactura.class)
public class DetalleFactura_ { 

    public static volatile SingularAttribute<DetalleFactura, Factura> facturaid;
    public static volatile SingularAttribute<DetalleFactura, Float> subtotal;
    public static volatile SingularAttribute<DetalleFactura, Producto> productoid;
    public static volatile SingularAttribute<DetalleFactura, Integer> id;
    public static volatile SingularAttribute<DetalleFactura, Float> cantidad;

}