package services;

import constantes.LocaisArquivoTexto;
import modelo.Empresa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaService {

    private static String EMPRESAS_SER = LocaisArquivoTexto.EMPRESAS_SER_PADRAO;

    public static void criarEmpresa(Empresa empresa) throws Exception {
        try {
            ArrayList<Empresa> empresas = readEmpresas();

            FileOutputStream fileOut = new FileOutputStream(EMPRESAS_SER);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            empresas.add(empresa);

            out.writeObject(empresas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Empresa> readEmpresas() throws Exception {
        ArrayList<Empresa> empresasCadastradas = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream(EMPRESAS_SER);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            empresasCadastradas = (ArrayList<Empresa>) in.readObject();

            fileIn.close();
            in.close();
        } catch (FileNotFoundException e) {
            throw new Exception("O arquivo " + EMPRESAS_SER + " não pode ser encontrado!", e);
        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
        }

        return empresasCadastradas;
    }

    public static Empresa readEmpresa(Integer id) {
        List<Empresa> empresasCadastradas;

        try {
            FileInputStream fileIn = new FileInputStream(EMPRESAS_SER);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            empresasCadastradas = readEmpresas();

            fileIn.close();
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return empresasCadastradas.get(id);
    }

    public static void updateEmpresa(Integer id, Empresa empresa) {
        List<Empresa> empresasCadastradas;

        try {
            FileInputStream fileIn = new FileInputStream(EMPRESAS_SER);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            FileOutputStream fileOut = new FileOutputStream(EMPRESAS_SER);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            empresasCadastradas = readEmpresas();

            empresasCadastradas.set(id, empresa);

            out.writeObject(empresasCadastradas);

            out.close();
            fileOut.close();
            fileIn.close();
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteEmpresa(Integer id) {
        ArrayList<Empresa> empresasCadastradas;

        try {
            FileInputStream fileIn = new FileInputStream(EMPRESAS_SER);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            empresasCadastradas = readEmpresas();

            fileIn.close();
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return empresasCadastradas.remove(id);
    }

    public static void setCaminhoArquivo(String novoCaminho) {
        EMPRESAS_SER = novoCaminho;
    }
}
