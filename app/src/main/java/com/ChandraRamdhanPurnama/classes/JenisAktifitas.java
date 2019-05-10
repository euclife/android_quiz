package com.ChandraRamdhanPurnama.classes;

public class JenisAktifitas {

    private String jenis;
    private float presentase;

    public JenisAktifitas() {
    }

    public JenisAktifitas(String jenis, float presentase) {
        this.jenis = jenis;
        this.presentase = presentase;
    }

    public String getJenis() {
        return jenis;
    }

    public float getPresentase() {
        return presentase;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setPresentase(float presentase) {
        this.presentase = presentase;
    }

    @Override
    public String toString() {
        return this.getJenis();
    }
}
