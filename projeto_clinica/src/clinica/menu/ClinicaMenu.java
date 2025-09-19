package clinica.menu;

import clinica.model.*;
import clinica.repository.ClinicaRepository;
import clinica.service.ClinicaService;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ClinicaMenu 
{
    public static void exibir() 
	{
        ClinicaRepository repo = new ClinicaRepository();
        ClinicaService service = new ClinicaService(repo);

        Scanner sc = new Scanner(System.in);
        int opcao;

        // alguns dados padrões para teste
        inicializarDadosTeste(repo);

        do 
		{
            System.out.println("\n====== Menu  ======");

            System.out.println("1 - Cadastrar Paciente");
            System.out.println("2 - Cadastrar Médico");
            System.out.println("3 - Cadastrar Local");
            System.out.println("4 - Marcar Consulta");
            System.out.println("5 - Listar Todas as Consultas");

            System.out.println("--- Relatórios ---");

            System.out.println("6 - Total de Médicos");
            System.out.println("7 - Consultas por Médico");
            System.out.println("8 - Consultas por Paciente");
            System.out.println("9 - Médico com Mais Consultas");
            System.out.println("10 - Consultas Realizadas");
            System.out.println("11 - Total de Pacientes");

            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            String entrada = sc.nextLine();

            try 
            {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) 
            {
                opcao = -1;
                System.out.println("Digite um número válido!");
            }

            switch (opcao) 
			{
                // cadastros
                case 1 -> cadastrarPaciente(repo, sc);
                case 2 -> cadastrarMedico(repo, sc);
                case 3 -> cadastrarLocal(repo, sc);
                case 4 -> marcarConsulta(repo, sc);
                case 5 -> listarTodasConsultas(repo);

                // relatórios
                case 6 -> service.totalMedicos();
                case 7 -> service.consultasPorMedico();
                case 8 -> service.consultasPorPaciente();
                case 9 -> service.exibirMedicoComMaisConsultas();
                case 10 -> service.totalConsultasRealizadas();
                case 11 -> service.totalPacientes();

                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
        sc.close();
    }

        private static void inicializarDadosTeste(ClinicaRepository repo) // adicionar alguns locais padrão para teste
        {
            if (repo.getLocais().isEmpty()) 
            {
                repo.adicionarLocal(new Local("Clínica UNICAP", "R. do Principe, 526", "Sala 10"));
                repo.adicionarLocal(new Local("Clínica Restauração", "Av. Gov. Agamenon Magalhães, 123", "Sala 11"));
                repo.adicionarLocal(new Local("Clínica HOPE", "R. Francisco Alves, 887", "Sala 12"));
            }
        }

        private static void cadastrarPaciente(ClinicaRepository repo, Scanner sc) 
        {
            System.out.println("\n--- Cadastro de Paciente ---");
            System.out.print("Nome do paciente: ");
            String nome = sc.nextLine();

            System.out.print("CPF: ");
            String cpf = sc.nextLine();

            Paciente paciente = new Paciente(nome, cpf);
            repo.adicionarPaciente(paciente);
            System.out.println("Paciente cadastrado com sucesso!");
        }

        private static void cadastrarMedico(ClinicaRepository repo, Scanner sc) 
        {
            System.out.println("\n--- Cadastro de Médico ---");
            System.out.print("Nome do médico: ");
            String nome = sc.nextLine();

            System.out.print("Especialidade: ");
            String especialidade = sc.nextLine();

            Medico medico = new Medico(nome, especialidade);
            repo.adicionarMedico(medico);
            System.out.println("Médico cadastrado com sucesso!");
        }

        private static void cadastrarLocal(ClinicaRepository repo, Scanner sc) 
        {
            System.out.println("\n--- Cadastro de Local ---");
            System.out.print("Nome do local: ");
            String nome = sc.nextLine();

            System.out.print("Endereço: ");
            String endereco = sc.nextLine();

            System.out.print("Sala: ");
            String sala = sc.nextLine();

            Local local = new Local(nome, endereco, sala);
            repo.adicionarLocal(local);
            System.out.println("Local cadastrado com sucesso!");
        }

        private static void marcarConsulta(ClinicaRepository repo, Scanner sc) {
        System.out.println("\n--- Agendar Consulta ---");

        // Verificar se existem pacientes, médicos e locais cadastrados
        if (repo.getPacientes().isEmpty()) {
            System.out.println("Nenhum paciente cadastrado. Cadastre um paciente primeiro.");
            return;
        }
        if (repo.getMedicos().isEmpty()) {
            System.out.println("Nenhum médico cadastrado. Cadastre um médico primeiro.");
            return;
        }
        if (repo.getLocais().isEmpty()) {
            System.out.println("Nenhum local cadastrado. Cadastre um local primeiro.");
            return;
        }

        try 
        {
            // Selecionar paciente
            System.out.println("\n--- Pacientes Disponíveis ---");
            List<Paciente> pacientes = repo.getPacientes();

            for (int i = 0; i < pacientes.size(); i++) 
            {
                System.out.println((i + 1) + " - " + pacientes.get(i).getNome() + 
                                " (CPF: " + pacientes.get(i).getCpf() + ")");
            }

            System.out.print("Escolha o paciente (número): ");
            int pacienteIndex = sc.nextInt() - 1;
            sc.nextLine();

            if (pacienteIndex < 0 || pacienteIndex >= pacientes.size()) 
            {
                System.out.println("Índice de paciente inválido!");
                return;
            }

            // Selecionar médico
            System.out.println("\n--- Médicos Disponíveis ---");
            List<Medico> medicos = repo.getMedicos();

            for (int i = 0; i < medicos.size(); i++) 
            {
                System.out.println((i + 1) + " - " + medicos.get(i).getNome() + 
                                " (" + medicos.get(i).getEspecialidade() + ")");
            }

            System.out.print("Escolha o médico (número): ");
            int medicoIndex = sc.nextInt() - 1;
            sc.nextLine();

            if (medicoIndex < 0 || medicoIndex >= medicos.size()) 
            {
                System.out.println("Índice de médico inválido!");
                return;
            }

            // Selecionar local
            System.out.println("\n--- Locais Disponíveis ---");
            List<Local> locais = repo.getLocais();

            for (int i = 0; i < locais.size(); i++) 
            {
                System.out.println((i + 1) + " - " + locais.get(i).getNome() + 
                                " - Sala: " + locais.get(i).getSala());
            }

            System.out.print("Escolha o local (número): ");
            int localIndex = sc.nextInt() - 1;
            sc.nextLine();

            if (localIndex < 0 || localIndex >= locais.size()) 
            {
                System.out.println("Índice de local inválido!");
                return;
            }

            // solicitar data e hora
            System.out.print("Data da consulta (AAAA-MM-DD): ");
            String dataStr = sc.nextLine();

            System.out.print("Hora da consulta (HH:MM): ");
            String horaStr = sc.nextLine();

            // converter para LocalDateTime
            LocalDateTime dataHora = LocalDateTime.parse(dataStr + "T" + horaStr + ":00");

            // criar e adicionar a consulta
            Consulta consulta = new Consulta
            (
                pacientes.get(pacienteIndex),
                medicos.get(medicoIndex),
                locais.get(localIndex),
                dataHora
            );

            repo.adicionarConsulta(consulta);
            System.out.println("Consulta agendada com sucesso!");
            System.out.println("Detalhes: " + consulta);

        } catch (DateTimeParseException e) 
        {
            System.out.println("Formato de data/hora inválido! Use AAAA-MM-DD e HH:MM.");
        } catch (Exception e) 
        {
            System.out.println("Erro ao agendar consulta: " + e.getMessage());
        }
    }

    private static void listarTodasConsultas(ClinicaRepository repo) 
	{
        System.out.println("\n--- Todas as Consultas Agendadas ---");
        List<Consulta> consultas = repo.getConsultas();
        
        if (consultas.isEmpty()) 
		{
            System.out.println("Nenhuma consulta agendada.");
        } else 
		{
            for (int i = 0; i < consultas.size(); i++) 
            {
                System.out.println((i + 1) + " - " + consultas.get(i));
            }
        }
    }
}