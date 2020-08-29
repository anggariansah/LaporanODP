package tik.pnj.laporanodp.util;

import java.util.ArrayList;

import tik.pnj.laporanodp.data.PasienEntity;

public class DummyData {

    public static ArrayList<PasienEntity> getDummyPasien() {
        ArrayList<PasienEntity> listPasien = new ArrayList<>();

        listPasien.add(new PasienEntity(
           "8572901203014034",
                "8375050411990001",
                "Budi Kurniawan",
                "Jalan Margonda",
                "Laki-Laki"
        ));

        listPasien.add(new PasienEntity(
                "8572901203014034",
                "8375050411990002",
                "Susi Kumalawati",
                "Jalan Margonda",
                "Perempuan"
        ));

        listPasien.add(new PasienEntity(
                "8572901203014034",
                "8375050411990003",
                "Bambang Hasim",
                "Jalan Margonda",
                "Laki-Laki"
        ));

        return listPasien;
    }

}
