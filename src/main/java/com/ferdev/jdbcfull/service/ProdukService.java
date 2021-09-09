package com.ferdev.jdbcfull.service;

import com.ferdev.jdbcfull.dao.ProdukDaoJdbc;
import com.ferdev.jdbcfull.model.Produk;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProdukService {
    // proses transaction terjadi di kelas service
    // transcation baiknya dilakukan permethod
    // 1 method 1 transcation

    private ProdukDaoJdbc produkDao;
    private Connection connection;

    // set connection dari service ke dao menggunakan datasource
    public void setDataSource(DataSource dataSource) {
        try {
            connection = dataSource.getConnection();
            produkDao = new ProdukDaoJdbc();
            produkDao.setConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Produk save(Produk produk) {
        try {
            connection.setAutoCommit(false); // membuat transcation dengan autocomit difalse in
            produkDao.save(produk);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException exception) {
            exception.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return produk;
    }

    public Produk delete(Produk produk) {
        try {
            connection.setAutoCommit(false);
            produkDao.delete(produk);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return produk;
    }

    public Produk getProduk(String id) {
        try {
            return produkDao.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Produk> getSemuaProduk() {
        try {
            return  produkDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
