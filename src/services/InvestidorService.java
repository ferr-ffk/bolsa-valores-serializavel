package services;

import constantes.LocaisArquivoTexto;
import modelo.Investidor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InvestidorService {

    private static String INVESTIDORES_SER = LocaisArquivoTexto.INVESTIDORES_SER_PADRAO;

    public static void criarInvestidor(Investidor investidor) throws Exception {
        try {
            ArrayList<Investidor> investidores = readInvestidores();

            FileOutputStream fileOut = new FileOutputStream(INVESTIDORES_SER);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            investidores.add(investidor);

            out.writeObject(investidores);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Investidor> readInvestidores() throws Exception {
        ArrayList<Investidor> investidores;

        try {
            FileInputStream fileIn = new FileInputStream(INVESTIDORES_SER);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            investidores = (ArrayList<Investidor>) in.readObject();

            fileIn.close();
            in.close();
        } catch (FileNotFoundException e) {
            throw new Exception("O arquivo " + INVESTIDORES_SER + " não pode ser encontrado!", e);
        } catch (IOException | ClassNotFoundException ef) {
            investidores = new ArrayList<>();
        }

        return investidores;
    }

    public static Investidor readInvestidor(Integer id) {
        List<Investidor> investidoresCadastrados;

        try {
            FileInputStream fileIn = new FileInputStream(INVESTIDORES_SER);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            investidoresCadastrados = (List<Investidor>) in.readObject();

            fileIn.close();
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return investidoresCadastrados.get(id);
    }

    public static Integer getIdInvestidor(Investidor investidor) {
        List<Investidor> investidoresCadastrados;

        try {
            FileInputStream fileIn = new FileInputStream(INVESTIDORES_SER);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            investidoresCadastrados = (List<Investidor>) in.readObject();

            fileIn.close();
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return investidoresCadastrados.indexOf(investidor);
    }

    public static void updateInvestidor(Integer id, Investidor investidor) {
        List<Investidor> investidores = null;

        FileOutputStream fileOut = null;
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;

        try {
            fileIn = new FileInputStream(INVESTIDORES_SER);
            in = new ObjectInputStream(fileIn);

            fileOut = new FileOutputStream(INVESTIDORES_SER);
            out = new ObjectOutputStream(fileOut);

            investidores = (List<Investidor>) in.readObject();

            investidores.set(id, investidor);
        } catch (EOFException ef) {
            throw new RuntimeException("Nenhum usuário cadastrado!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.writeObject(investidores);

                out.close();
                fileOut.close();
                fileIn.close();
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean deleteInvestidor(Integer id) {
        List<Investidor> investidoresCadastrados;
        boolean remove;

        try {
            FileInputStream fileIn = new FileInputStream(INVESTIDORES_SER);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            FileOutputStream fileOut = new FileOutputStream(INVESTIDORES_SER);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            investidoresCadastrados = (List<Investidor>) in.readObject();

            remove = investidoresCadastrados.remove(id);

            out.writeObject(investidoresCadastrados);

            out.close();
            fileOut.close();
            fileIn.close();
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return remove;
    }

    public static void setCaminhoArquivo(String novoCaminho) {
        INVESTIDORES_SER = novoCaminho;
    }
}
