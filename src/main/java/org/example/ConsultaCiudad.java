package org.example;

import java.util.Objects;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultaCiudad {
    static int availflight = 0;
    static String city;
    public static void main(String[] args) {
        Scanner ns = new Scanner(System.in);
        final String URL_DB = "jdbc:postgresql://localhost:5432/aerolinea";
        //final String URL_DB = "jdbc:postgresql://148.225.68.199:5432/javacoffee";
        //final String URL_DB = "jdbc:mysql://localhost/produccion";
        final String USER_DB = "newuser";
        final String PASS_DB = "rfvgygvfrocho";

        try {
            // Cargar driver de JDBC
            // para la base de datos apropiada.
            Class.forName("org.postgresql.Driver");
        } catch (Exception ex) {
            System.out.println("No se encontro el driver JDBC.");
            System.exit(1);
        }

        Connection conn = null;
        try {
            // Abrir conexión
            // USER_DB: Usuario valido en PostgreSQL
            conn = DriverManager.getConnection(
                    URL_DB,
                    USER_DB,
                    PASS_DB);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(1);
        }

        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Crear el comando para ejecución
            stmt = conn.createStatement();

            // Ejecutar comando
            System.out.print("Para revisar si existen vuelos para una ciudad, ingrese el nombre de la ciudad deseada: ");
            String city = ns.next();

            //rs = stmt.executeQuery("SELECT COF_NAME, SUP_ID, PRICE FROM COFFEES");
            rs = stmt.executeQuery("SELECT arr_apc, sch_dep_time, sch_arr_time FROM flight_leg");
            // Procesar resultado

            while( rs.next() ) {
                String apc = rs.getString("arr_apc");
                String dep_time = rs.getString("sch_dep_time");
                String arr_time = rs.getString("sch_arr_time");
                //String nombre = rs.getString(1);
                //int provID = rs.getInt("SUP_ID");
                //double price = rs.getDouble("PRICE");
                switch(city){
                    case "Aguascalientes":
                        if(Objects.equals(apc, city)){
                            System.out.println("Vuelo hacia: " + apc);
                            System.out.println("Salida a " + dep_time + " y llegada a" + arr_time);
                            availflight++;
                        }
                        break;
                    case "Jalisco":
                        if(Objects.equals(apc, city)){
                            System.out.println("Vuelo hacia: " + apc);
                            System.out.println("Salida a " + dep_time + " y llegada a" + arr_time);
                            availflight++;
                        }
                        break;
                    case "Hermosillo":
                        if(Objects.equals(apc, city)){
                            System.out.println("Vuelo hacia: " + apc);
                            System.out.println("Salida a " + dep_time + " y llegada a" + arr_time);
                            availflight++;
                        }
                        break;
                    case "La Paz":
                        if(Objects.equals(apc, city)){
                            System.out.println("Vuelo hacia: " + apc);
                            System.out.println("Salida a " + dep_time + " y llegada a" + arr_time);
                            availflight++;
                        }
                        break;
                    case "Leon":
                        if(Objects.equals(apc, city)){
                            System.out.println("Vuelo hacia: " + apc);
                            System.out.println("Salida a " + dep_time + " y llegada a" + arr_time);
                            availflight++;
                        }
                        break;
                    case "Los Cabos":
                        if(Objects.equals(apc, city)){
                            System.out.println("Vuelo hacia: " + apc);
                            System.out.println("Salida a " + dep_time + " y llegada a" + arr_time);
                            availflight++;
                        }
                        break;
                    case "Los Mochis":
                        if(Objects.equals(apc, city)){
                            System.out.println("Vuelo hacia: " + apc);
                            System.out.println("Salida a " + dep_time + " y llegada a" + arr_time);
                            availflight++;
                        }
                        break;
                    case "Manzanillo":
                        if(Objects.equals(apc, city)){
                            System.out.println("Vuelo hacia: " + apc);
                            System.out.println("Salida a " + dep_time + " y llegada a" + arr_time);
                            availflight++;
                        }
                        break;
                    case "Mexicali":
                        if(Objects.equals(apc, city)){
                            System.out.println("Vuelo hacia: " + apc);
                            System.out.println("Salida a " + dep_time + " y llegada a" + arr_time);
                            availflight++;
                        }
                        break;
                    case "Morelia":
                        if(Objects.equals(apc, city)){
                            System.out.println("Vuelo hacia: " + apc);
                            System.out.println("Salida a " + dep_time + " y llegada a" + arr_time);
                            availflight++;
                        }
                        break;
                    case "Puerto Vallarta":
                        if(Objects.equals(apc, city)){
                            System.out.println("Vuelo hacia: " + apc);
                            System.out.println("Salida a " + dep_time + " y llegada a" + arr_time);
                            availflight++;
                        }
                        break;
                    case "Tijuana":
                        if(Objects.equals(apc, city)){
                            System.out.println("Vuelo hacia: " + apc);
                            System.out.println("Salida a " + dep_time + " y llegada a" + arr_time);
                            availflight++;
                        }
                        break;
                }
                //System.out.print(nombre);
                //System.out.print("\t");
                //System.out.print(provID);
                //System.out.print("\t");
                //System.out.println(price);
            }
            if(availflight == 0){
                System.out.println("No hay vuelos disponibles para " + city);
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(1);
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error");
        }

    }
}