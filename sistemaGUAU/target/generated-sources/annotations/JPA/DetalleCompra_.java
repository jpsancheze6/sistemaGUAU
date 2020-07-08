package JPA;

import JPA.Producto;
import JPA.ReciboCompra;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2020-07-08T10:28:08", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(DetalleCompra.class)
public class DetalleCompra_ { 

    public static volatile SingularAttribute<DetalleCompra, ReciboCompra> reciboid;
    public static volatile SingularAttribute<DetalleCompra, Float> subtotal;
    public static volatile SingularAttribute<DetalleCompra, Producto> productoid;
    public static volatile SingularAttribute<DetalleCompra, Integer> id;
    public static volatile SingularAttribute<DetalleCompra, Integer> cantidad;

}