package JPA;

import JPA.Cliente;
import JPA.DetalleFactura;
import JPA.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-14T21:18:59")
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