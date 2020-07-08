package JPA;

import JPA.DetallesActualizacion;
import JPA.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-08T08:04:32")
@StaticMetamodel(ActualizacionInventario.class)
public class ActualizacionInventario_ { 

    public static volatile SingularAttribute<ActualizacionInventario, Date> fecha;
    public static volatile SingularAttribute<ActualizacionInventario, Integer> idActualizacion;
    public static volatile SingularAttribute<ActualizacionInventario, String> descripccion;
    public static volatile SingularAttribute<ActualizacionInventario, Usuario> usuarioid;
    public static volatile ListAttribute<ActualizacionInventario, DetallesActualizacion> detallesActualizacionList;

}