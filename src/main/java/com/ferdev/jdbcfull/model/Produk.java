package com.ferdev.jdbcfull.model;

import java.sql.Date;

public class Produk {
    private String id;
    private String nama;
    private String deskripsi;
    private int harga;
    private int quantity;
    private Date waktuDibuat;
    private String id_kategori;
    private String sample;
    private String contoh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getWaktuDibuat() {
        return waktuDibuat;
    }

    public void setWaktuDibuat(Date waktuDibuat) {
        this.waktuDibuat = waktuDibuat;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public String getContoh() {
        return contoh;
    }

    public void setContoh(String contoh) {
        this.contoh = contoh;
    }
}
