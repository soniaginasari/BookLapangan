package com.example.booklapangan.model;

import java.util.List;

public class ResponseLapangan {
    private List<LapanganItem> lapangan;

    public void setLapangan(List<LapanganItem> lapangan){
        this.lapangan = lapangan;
    }

    public List<LapanganItem> getLapangan(){
        return lapangan;
    }

    @Override
    public String toString(){
        return
                "ResponseLapangan{" +
                        "lapangan = '" + lapangan + '\'' +
                        "}";
    }
}
