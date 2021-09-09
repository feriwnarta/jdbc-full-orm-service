package com.ferdev.jdbcfull.dao;

import com.ferdev.jdbcfull.model.Produk;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdukDaoJdbc {
    // DAO (data acces object)
    // sebuah jembatan untuk melakukan query relational database kemudian akan diteruskan ke orm (model), baik itu menyimpan (select) keobjek
    // atau insert update delete ke database

    // koneksi
    private Connection connection;
    // statement
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getByIdStatement;
    private PreparedStatement getAllStatement;

    // query
    private final String insertQuery = "insert into produk (nama, harga, quantity, id)" +
            "values(?, ?, ?, ?)";
    private final String updateQuery = "update produk set nama = ?, harga = ?, quantity = ? where id = ?";
    private final String deleteQuery = "delete from produk where id ?";
    private final String getByIdQuery = "select * from produk where id ?";
    private final String getAllQuery = "select * from produk";

    // String id increment
    private String id;
    private int increment = 23;


    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        insertStatement = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        updateStatement = this.connection.prepareStatement(updateQuery);
        deleteStatement = this.connection.prepareStatement(deleteQuery);
        getByIdStatement = this.connection.prepareStatement(getByIdQuery);
        getAllStatement = this.connection.prepareStatement(getAllQuery);
    }

    public String incrementId() {
        try {
            PreparedStatement getMaxId = connection.prepareStatement("select max(id) as max from produk");
            ResultSet rs = getMaxId.executeQuery();
            rs.next();
            increment++;
            id = "P00" + increment;
            return id;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public Produk save(Produk produk) throws SQLException {

        // yang ditutorial karena id nya auto increment, jadi diisi otomatis
        // kasus kita tidak menggunakan id auto, tapi generate id sendiri jadi beda logic
        if(produk.getId() == null){
            insertStatement.setString(1, produk.getNama());
            insertStatement.setInt(2, produk.getHarga());
            insertStatement.setInt(3, produk.getQuantity());
            produk.setId(incrementId());
            produk.setId(incrementId());

            insertStatement.setString(4, produk.getId());
            insertStatement.executeUpdate();
        } else {
            updateStatement.setString(1, produk.getNama());
            updateStatement.setInt(2, produk.getHarga());
            updateStatement.setInt(3, produk.getQuantity());
            updateStatement.setString(4, produk.getId());
            updateStatement.executeUpdate();
        }
        return produk;
    }

    public Produk delete(Produk produk) throws SQLException {
        deleteStatement.setString(1, produk.getId());
        deleteStatement.executeUpdate();
        return produk;
    }

    // akan ada proses maping dari relational database (data yg ada didatabase menjadi objek java)
    public Produk getById(String id) throws SQLException {
        // mengambil dulu data dari databse, berdasarkan id menggunakan query dengan where id
        getByIdStatement.setString(1, id);
        // setelah itu simpan kedalam result set menggunakan method executeQuery() -> method ini akan mengembalikan objek resulset
        ResultSet rs = getByIdStatement.executeQuery();

        // proses mapping dari database bentuk relational ke objek java
        if (rs.next()) {
            Produk p = new Produk(); // objek produk kosong, untuk disi hasil mapping dari data relational database yg dambil dari resulset
            p.setId(rs.getString("id"));
            p.setNama(rs.getString("nama"));
            p.setHarga(rs.getInt("harga"));
            p.setQuantity(rs.getInt("quantity"));
        }
        // jika id tidak ketemu return null
        return null;
    }

    public List<Produk> getAll() throws SQLException {
        // aray list untuk menyimpan hasil mapping dari database
        List<Produk> bykProduk = new ArrayList<>();

        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()) {
            Produk p = new Produk();
            p.setId(rs.getString("id"));
            p.setNama(rs.getString("nama"));
            p.setHarga(rs.getInt("harga"));
            p.setQuantity(rs.getInt("quantity"));
            bykProduk.add(p);
        }
        return bykProduk;
    }
}
