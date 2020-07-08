package JPA;

import JPA.Cliente;
import JPA.DetalleFactura;
import JPA.Usuario;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2020-07-08T10:28:08", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, Date> fecha;
    public static volatile SingularAttribute<Factura, Float> total;
    public static volatile SingularAttribute<Factura, Cliente> clienteid;
    public static volatile SingularAttribute<Factura, Float> descuento;
    public static volatile SingularAttribute<Factura, Usuario> usuarioidUsuario;
    public static volatile SingularAttribute<Factura, Integer> idFactura;
    public static volatile SingularAttribute<Factura, String> concepto;
    public static volatile ListAttribute<Factura, DetalleFactura> detalleFacturaList;

}