package carritodecompras;

import java.sql.*;

public class conexionMySQL {
    
    private String db = "supermercado";
    private String url = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String password ="1234";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private Connection con;
    
    public conexionMySQL() throws SQLException {
    }

    public Connection conectar() throws ClassNotFoundException{
        Class.forName(driver);
        
        System.out.println("Conectando con base de datos...");
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url+db,user,password);
            
            
        } catch(Exception e){
            System.out.println("No se ha podido establecer la conexion con la base de datos");
            System.out.println(e);
        }
        return con;
    }
    
    public ResultSet ejecutarConsulta(String sql) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
    
    public String[] buscarProducto(String codigo) throws SQLException{
        String[] vecProd = new String[4];
        
        String consulta = "Select * from supermercado.producto where codigoP = "+codigo;
        
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(consulta);
        
        while(rs.next()){
            vecProd[0]=String.valueOf(rs.getInt("codigoP"));
            vecProd[1]=rs.getString("nombreP");
            vecProd[2]=rs.getString("descripcionP");
            vecProd[3]=String.valueOf(rs.getInt("precioP"));
        }
        
        return vecProd;
    }
    
    public  int ejecutarActualizacion (String sql) throws SQLException {
        Statement stmt = con.createStatement();
        int resultado = stmt.executeUpdate(sql);
        return resultado;
    }
    
    
    public void cerrarConexion() throws SQLException {
        if (con != null) {
            con.close();
        }
    }
}