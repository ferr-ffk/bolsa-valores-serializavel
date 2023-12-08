package services;

import constantes.LocaisArquivoTexto;
import modelo.*;

import java.io.*;
import java.util.ArrayList;

public class CorretoraService {

    private static String HISTORICO_ORDENS_SER = LocaisArquivoTexto.HISTORICO_ORDENS_SER_PADRAO;

    public static void enviarOrdem(Ordem ordem) throws Exception {
        ordem.getInvestidor().adicionarPapel(ordem.getAcao());

        try {
            ArrayList<Ordem> ordens = readOrdens();

            FileOutputStream fileOut = new FileOutputStream(HISTORICO_ORDENS_SER);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            ordens.add(ordem);

            out.writeObject(ordens);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Ordem> readOrdens() throws Exception {
        ArrayList<Ordem> ordens;

        try {
            FileInputStream fileIn = new FileInputStream(HISTORICO_ORDENS_SER);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            ordens = (ArrayList<Ordem>) in.readObject();

            fileIn.close();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("O sistema não pode encontrar o aquivo " + HISTORICO_ORDENS_SER, e);
        }

        return ordens;
    }

    public static void setCaminhoArquivo(String novoCaminho) {
        HISTORICO_ORDENS_SER = novoCaminho;
    }
}
