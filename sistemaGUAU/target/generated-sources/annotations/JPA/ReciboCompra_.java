package JPA;

import JPA.DetalleCompra;
import JPA.Proveedor;
import JPA.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-14T21:18:59")
@StaticMetamodel(ReciboCompra.class)
public class ReciboCompra_ { 

    public static volatile SingularAttribute<ReciboCompra, Date> fecha;
    public static volatile ListAttribute<ReciboCompra, DetalleCompra> detalleCompraList;
    public static volatile SingularAttribute<ReciboCompra, Float> total;
    public static volatile SingularAttribute<ReciboCompra, Integer> idRecibo;
    public static volatile SingularAttribute<ReciboCompra, Proveedor> proveedorid;
    public static volatile SingularAttribute<ReciboCompra, Usuario> usuarioidUsuario;

}