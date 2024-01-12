package com.example.clinicaconsultas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SistemaClinica {
    private List<Paciente> pacientes = new ArrayList<>();
    private List<Consulta> consultas = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    private static final String PACIENTES_FILE = "pacientes.txt";
    private static final String CONSULTAS_FILE = "consultas.txt";

    public SistemaClinica() {
        carregarPacientes();
        carregarConsultas();
    }

    public void exibirMenu() {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar paciente");
            System.out.println("2. Marcações de consultas");
            System.out.println("3. Cancelamento de consultas");
            System.out.println("4. Sair");
            System.out.println("5. Exibir lista de Pacientes");
            System.out.println("6. Exibir lista de Consultas agendadas");
            System.out.println("7. Excluir cadastro de Paciente");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // usando so pra garantir que o buffer ta limpo

            switch (opcao) {
                case 1:
                    cadastrarPaciente();
                    salvarPacientes();
                    break;
                case 2:
                    marcarConsulta();
                    salvarConsultas();
                    break;
                case 3:
                    cancelarConsulta();
                    salvarConsultas();
                    break;
                case 4:
                    System.out.println("Programa encerrado.");
                    System.exit(0);
                    break;
                case 5:
                    exibirPacientes();
                    break;
                case 6:
                    exibirConsultas();
                    break;
                case 7:
                    excluirPaciente();
                    salvarPacientes();
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private boolean pacienteCadastrado(String telefone) {
        for (Paciente p : pacientes) {
            if (p.getTelefone().equals(telefone)) {
                return true; // Paciente já está cadastrado
            }
        }
        return false; // Paciente não ta cadastrado, prossegue com o cadastro
    }

    private boolean consultaExistente(String dia, String hora) {
        for (Consulta c : consultas) {
            if (c.getDia().equals(dia) && c.getHora().equals(hora)) {
                return true; // Consulta já ta agendada
            }
        }
        return false; // Consulta não ta agendada, prossegue com o cadastro
    }

    private boolean dataValida(String dia) {

        try {
            // Converter a string do dia para LocalDate
            LocalDate dataConsulta = LocalDate.parse(dia, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            LocalDate dataAtual = LocalDate.now();

            // Verificar se a data da consulta é igual ou posterior à data atual
            return !dataConsulta.isBefore(dataAtual);
        } catch (Exception e) {
            System.out.println("Formato de data inválido. Use o formato dd/MM/yyyy.");
            return false;
        }
    }

    private void cadastrarPaciente() {
        System.out.println("Digite o nome do paciente:");
        String nome = scanner.nextLine();

        String telefone;
        Pattern pattern = Pattern.compile("\\+55 \\(\\d{2}\\) \\d \\d{4}-\\d{4}");

        while (true) {
            System.out.println("Digite o telefone do paciente no formato: +55 (XX) X XXXX-XXXX:");
            telefone = scanner.nextLine();

            Matcher matcher = pattern.matcher(telefone);

            if (matcher.matches()) {
                break; // Sai do loop se o formato estiver correto
            } else {
                System.out.println("Formato de telefone inválido! Tente novamente.");
            }
        }

        if (pacienteCadastrado(telefone)) {
            System.out.println("Erro! o paciente já está cadastrado.");
            return; // Retornar ao menu principal
        }

        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setTelefone(telefone);

        pacientes.add(paciente);

        System.out.println("Paciente cadastrado com sucesso.");
    }

    private void marcarConsulta() {

        if (pacientes.isEmpty()) {
            System.out.println("Não há pacientes cadastrados. Cadastre um paciente primeiro.");
            return;
        }

        System.out.println("Lista de pacientes cadastrados:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + ". " + pacientes.get(i));
        }

        System.out.println("Escolha o número correspondente ao paciente:");
        int numPaciente = scanner.nextInt();
        scanner.nextLine();

        //validação para ter certeza que o numero de paciente está dentro do "banco de dados"
        if (numPaciente < 1 || numPaciente > pacientes.size()) {
            System.out.println("Número de paciente inválido.");
            return;
        }

        Paciente paciente = pacientes.get(numPaciente - 1);

        System.out.println("Digite o dia da consulta:");
        String dia = scanner.nextLine();

        System.out.println("Digite a hora da consulta:");
        String hora = scanner.nextLine();

        if (consultaExistente(dia, hora)) {
            System.out.println("Consulta já agendada para este dia e hora. Escolha outro horário.");
            return;
        }

        if (!dataValida(dia)) {
            System.out.println("Data inválida. Não é possível agendar consultas retroativas.");
            return;
        }

        System.out.println("Digite a especialidade da consulta");
        String especialidade = scanner.nextLine();

        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setDia(dia);
        consulta.setHora(hora);
        consulta.setEspecialidade(especialidade);

        consultas.add(consulta);

        System.out.println("Consulta marcada com sucesso.");
    }

    private void cancelarConsulta() {
        if (consultas.isEmpty()) {
            System.out.println("Não há consultas marcadas.");
            return;
        }

        System.out.println("Lista de consultas marcadas:");
        for (int i = 0; i < consultas.size(); i++) {
            System.out.println((i + 1) + ". " + consultas.get(i));
        }

        System.out.println("Escolha o número correspondente à consulta que deseja cancelar:");
        int numConsulta = scanner.nextInt();
        scanner.nextLine();

        if (numConsulta < 1 || numConsulta > consultas.size()) {
            System.out.println("Número de consulta inválido.");
            return;
        }

        Consulta consultaCancelada = consultas.remove(numConsulta - 1);

        System.out.println("Consulta cancelada com sucesso:");
        System.out.println(consultaCancelada);
    }

    private void salvarPacientes() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(PACIENTES_FILE))) {
            outputStream.writeObject(pacientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarPacientes() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PACIENTES_FILE))) {
            pacientes = (List<Paciente>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Arquivo não existe ou não pode ser lido, então não há pacientes ainda.
        }
    }

    private void salvarConsultas() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(CONSULTAS_FILE))) {
            outputStream.writeObject(consultas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarConsultas() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(CONSULTAS_FILE))) {
            consultas = (List<Consulta>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Arquivo não existe ou não pode ser lido, então não há consultas ainda.
        }
    }

    private void exibirPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("Não há pacientes cadastrados.");
        } else {
            System.out.println("Lista de pacientes cadastrados:");
            for (int i = 0; i < pacientes.size(); i++) {
                System.out.println((i + 1) + ". " + pacientes.get(i));
            }
        }
    }

    private void exibirConsultas() {
        if (consultas.isEmpty()) {
            System.out.println("Não há consultas marcadas.");
        } else {
            System.out.println("Lista de consultas marcadas:");
            for (int i = 0; i < consultas.size(); i++) {
                System.out.println((i + 1) + ". " + consultas.get(i));
            }
        }
    }

    private void excluirPaciente() {
        if (pacientes.isEmpty()) {
            System.out.println("Não há pacientes cadastrados.");
            return;
        }

        System.out.println("Lista de pacientes cadastrados:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + ". " + pacientes.get(i));
        }

        System.out.println("Escolha o número correspondente ao paciente que deseja excluir:");
        int numPaciente = scanner.nextInt();
        scanner.nextLine();

        if (numPaciente < 1 || numPaciente > pacientes.size()) {
            System.out.println("Número de paciente inválido.");
            return;
        }

        Paciente pacienteExcluido = pacientes.remove(numPaciente - 1);

        System.out.println("Paciente excluído com sucesso:");
        System.out.println(pacienteExcluido);
    }


    public static void main(String[] args) {
        SistemaClinica sistema = new SistemaClinica();
        sistema.exibirMenu();
        sistema.salvarPacientes();
        sistema.salvarConsultas();
    }
}
