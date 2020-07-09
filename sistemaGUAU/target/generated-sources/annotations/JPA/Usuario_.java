package JPA;

import JPA.Factura;
import JPA.ReciboCompra;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-08T18:52:10")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-08T20:31:49")
>>>>>>> FXMLUsuarioProveedor
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> nombreusuario;
    public static volatile SingularAttribute<Usuario, byte[]> password;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile ListAttribute<Usuario, ReciboCompra> reciboCompraList;
    public static volatile ListAttribute<Usuario, Factura> facturaList;
    public static volatile SingularAttribute<Usuario, String> nombre;

}