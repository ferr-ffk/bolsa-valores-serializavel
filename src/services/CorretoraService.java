package services;

import constantes.LocaisArquivoTexto;
import modelo.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CorretoraService {

    private static String HISTORICO_ORDENS_SER = LocaisArquivoTexto.HISTORICO_ORDENS_SER_PADRAO;

    public static void enviarOrdem(Ordem ordem) {
        ordem.getInvestidor().adicionarPapel(ordem.getAcao());
        ArrayList<Ordem> ordens = readOrdens();


        try {
            FileOutputStream fileOut = new FileOutputStream(HISTORICO_ORDENS_SER);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            ordens.add(ordem);

            out.writeObject(ordens);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Ordem> readOrdens() {
        ArrayList<Ordem> ordens = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream(HISTORICO_ORDENS_SER);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            ordens = (ArrayList<Ordem>) in.readObject();

            fileIn.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ordens;
    }

    public static void setCaminhoArquivo(String novoCaminho) {
        HISTORICO_ORDENS_SER = novoCaminho;
    }
}
