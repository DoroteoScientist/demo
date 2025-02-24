package Modelos;

public class ClientesDAO {

    private int idCte;
    private String nomCte;
    private String telefonoiCte;
    private String direccion;
    private String emailCte;

    public String getNomCte() {
        return nomCte;
    }

    public void setNomCte(String nomCte) {
        this.nomCte = nomCte;
    }

    public String getTelefonoCte() {
        return telefonoiCte;
    }

    public void setTelefonoiCte(String nomCte) {
        this.telefonoiCte = telefonoiCte;
    }

    public String getEmailCte() {
        return emailCte;
    }

    public void setEmailCte(String nomCte) {
        this.emailCte = emailCte;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String nomCte) {
        this.direccion = direccion;
    }

    public int getIdCte() {
        return idCte;
    }

    public void setIdCte(int idCte) {
        this.idCte = idCte;
    }

    public void INSERT()
    {
        //MANDAR CONSULTA A LA BD
        String query = "INSERT INTO cliente(nomCte,telefonoCte,direccion,emailCte) " +
                "values('"+nomCte+"','"+telefonoiCte+"','"+direccion+"','"+emailCte+"')";
    }
    public void UPDATE()
    {}
    public void DELETE()
    {}
    public void SELECT()
    {}

}
