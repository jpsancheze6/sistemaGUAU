package JPA;

import JPA.Factura;
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
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, Integer> idCliente;
    public static volatile SingularAttribute<Cliente, Boolean> mayorista;
    public static volatile SingularAttribute<Cliente, String> nit;
    public static volatile SingularAttribute<Cliente, String> direccion;
    public static volatile SingularAttribute<Cliente, String> tipocliente;
    public static volatile ListAttribute<Cliente, Factura> facturaList;
    public static volatile SingularAttribute<Cliente, String> telefono;
    public static volatile SingularAttribute<Cliente, String> nombre;

}