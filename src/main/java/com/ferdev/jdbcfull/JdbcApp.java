package com.ferdev.jdbcfull;

import com.ferdev.jdbcfull.model.Produk;
import com.ferdev.jdbcfull.service.ProdukService;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class JdbcApp {
    public static void main( String[] args ) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("Begunggang1@23");
        dataSource.setDatabaseName("belajar_mysql");
        dataSource.setServerName("localhost");

        ProdukService produkService = new ProdukService();
        produkService.setDataSource(dataSource);

        // simpan data kedatabase / store
        Produk p = new Produk();
        p.setNama("BecomC");
        p.setHarga(22000);
        p.setQuantity(1300);

        System.out.println("ID          : " + p.getId());
        System.out.println("NAMA        : " + p.getNama());
        System.out.println("HARGA       : " + p.getHarga());
        System.out.println("QUANTITY    : " + p.getQuantity());
        System.out.println("\nDATA AKAN DI SIMPAN DALAM DATABASE");
        // simpan data baru, jika id nya null (insert query)
        produkService.save(p);

        // update produk jika id tidak null
        Produk p2 = new Produk();
        p2.setId("P0025");
        p2.setNama("Vitalong C");
        p2.setHarga(52000);
        p2.setQuantity(720);

        // ini akan menjalan bagian else statement, karena id nya tidak null
        // makan yg terjadi adalah logic update query
        produkService.save(p2);

        // tampilkan semua data dari database

        List<Produk> semuaProduk = produkService.getSemuaProduk();

        for(Produk data : semuaProduk) {
            System.out.println("ID         :   " + data.getId());
            System.out.println("NAMA       :   " + data.getNama());
            System.out.println("HARGA      :   " + data.getHarga());
            System.out.println("QUANTITY   :   " + data.getQuantity());
            System.out.print("\n");
        }

        try {
            dataSource.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
