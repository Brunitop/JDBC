package org.example;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class BorrarDatos {
    static String airport_code;

    public static void main(String[] args) {
        Scanner ns = new Scanner(System.in);
        final String URL_DB = "jdbc:postgresql://localhost:5432/aerolinea";
        //final String URL_DB = "jdbc:mysql://localhost/produccion";
        final String USER_DB = "newuser";
        final String PASS_DB = "rfvgygvfrocho";

        if( args.length != 1) {
            System.out.println("java DeleteAirport <airport_code> ");
            System.exit(1);
        }

        StringBuilder sql = new StringBuilder("DELETE FROM airport WHERE airport_code = \'");
        try {
            System.out.println("Ingrese el codigo de aeropuerto");
            airport_code = ns.next();
            while (airport_code.length() != 3) {
                System.out.println("Debe ser un código de 3 letras mayusculas");
            }
            sql.append(airport_code);
            sql.append("\'");
        }catch(Exception e){
            System.out.println("ERROR: se ingreso algun tipo de dato erroneo");
            System.exit(1);
        }

        try {
            // Cargar driver de JDBC
            // para la base de datos apropiada.
            Class.forName("org.postgresql.jdbc.Driver");

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

            //rs = stmt.executeQuery("SELECT COF_NAME, SUP_ID, PRICE FROM COFFEES");
            rs = stmt.executeQuery("SELECT airport_code FROM airport");
            // Procesar resultado

            int c = 0;
            while( rs.next() ) {
                String apc = rs.getString("airport_code");

                if(Objects.equals(apc, airport_code)){
                    System.out.printf("Commando: %s%n", sql);
                    // Ejecutar comando
                    stmt.executeUpdate( sql.toString() );
                    c++;
                }
                stmt.close();
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