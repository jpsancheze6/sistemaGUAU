package JPA;

import JPA.DetalleCompra;
import JPA.Proveedor;
import JPA.Usuario;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2020-07-08T10:28:08", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(ReciboCompra.class)
public class ReciboCompra_ { 

    public static volatile SingularAttribute<ReciboCompra, Date> fecha;
    public static volatile ListAttribute<ReciboCompra, DetalleCompra> detalleCompraList;
    public static volatile SingularAttribute<ReciboCompra, Float> total;
    public static volatile SingularAttribute<ReciboCompra, Integer> idRecibo;
    public static volatile SingularAttribute<ReciboCompra, Proveedor> proveedorid;
    public static volatile SingularAttribute<ReciboCompra, Usuario> usuarioidUsuario;

}