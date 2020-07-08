package JPA;

import JPA.Factura;
import JPA.Producto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2020-07-08T10:28:08", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(DetalleFactura.class)
public class DetalleFactura_ { 

    public static volatile SingularAttribute<DetalleFactura, Factura> facturaid;
    public static volatile SingularAttribute<DetalleFactura, Float> subtotal;
    public static volatile SingularAttribute<DetalleFactura, Producto> productoid;
    public static volatile SingularAttribute<DetalleFactura, Integer> id;
    public static volatile SingularAttribute<DetalleFactura, Float> cantidad;

}