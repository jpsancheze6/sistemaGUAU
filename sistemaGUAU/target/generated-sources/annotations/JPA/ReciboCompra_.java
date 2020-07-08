package JPA;

import JPA.DetalleCompra;
import JPA.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-07T23:46:54")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-07T21:02:39")
>>>>>>> Formularios
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-08T08:04:32")
>>>>>>> FXMLUsuarioProveedor
@StaticMetamodel(ReciboCompra.class)
public class ReciboCompra_ { 

    public static volatile SingularAttribute<ReciboCompra, Date> fecha;
    public static volatile ListAttribute<ReciboCompra, DetalleCompra> detalleCompraList;
    public static volatile SingularAttribute<ReciboCompra, Float> total;
    public static volatile SingularAttribute<ReciboCompra, Integer> idRecibo;
    public static volatile SingularAttribute<ReciboCompra, Usuario> usuarioidUsuario;

}