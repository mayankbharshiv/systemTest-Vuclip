package com.vuclip.premiumengg.automation.billing_package_service.common.utils;

import com.vuclip.premiumengg.automation.utils.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Kohitij_Das on 23/03/18.
 */
public class BPSDBUtil extends DBUtils {

    private Statement sqlStatement;

    /**
     * @param billingCode
     * @param dbConnection
     * @throws Exception
     */
    public void cleanBillingPackageTable(String billingCode, Connection dbConnection) {
        String query = "delete from billing_package where billing_code='" + billingCode + "'";
        try {
            sqlStatement = dbConnection.createStatement();
            sqlStatement.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param billingCode
     * @param dbConnection
     */
    public void cleanBillingPackageMappingTable(String billingCode, Connection dbConnection) {
        String query = "delete from billing_package_mapping where billing_code='" + billingCode + "'";
        try {
            sqlStatement = dbConnection.createStatement();
            sqlStatement.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param partnerId
     * @param dbConnection
     */
    public void cleanBillingPartnerTable(int partnerId, Connection dbConnection) {
        String query = "delete from partner where partner_id='" + partnerId + "'";
        try {
            sqlStatement = dbConnection.createStatement();
            sqlStatement.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param productId
     * @param dbConnection
     */
    public void cleanBillingProductTable(int productId, Connection dbConnection) {
        String query = "delete from product where product_id='" + productId + "'";
        try {
            sqlStatement = dbConnection.createStatement();
            sqlStatement.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
