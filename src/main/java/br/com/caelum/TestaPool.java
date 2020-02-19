/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caelum;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Gustavo
 */
public class TestaPool {

    public static void main(String[] args) throws PropertyVetoException, SQLException {

        ComboPooledDataSource dataSource = (ComboPooledDataSource) new JpaConfigurator().getDataSource();

        for (int i = 0; i < 10; i++) {
            Connection connection = dataSource.getConnection();

            System.out.println("Num Conexoes Ocupadas: " + dataSource.getNumBusyConnections());
            System.out.println("Num Conexoes Ociosas: " + dataSource.getNumIdleConnections());
            System.out.println();
        }
    }

}
