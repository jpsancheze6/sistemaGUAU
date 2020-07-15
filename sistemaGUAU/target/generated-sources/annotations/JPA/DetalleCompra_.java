package JPA;

import JPA.Producto;
import JPA.ReciboCompra;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-14T21:18:59")
@StaticMetamodel(DetalleCompra.class)
public class DetalleCompra_ { 

    public static volatile SingularAttribute<DetalleCompra, ReciboCompra> reciboid;
    public static volatile SingularAttribute<DetalleCompra, Float> subtotal;
    public static volatile SingularAttribute<DetalleCompra, Producto> productoid;
    public static volatile SingularAttribute<DetalleCompra, Integer> id;
    public static volatile SingularAttribute<DetalleCompra, Integer> cantidad;

}