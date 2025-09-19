package clinica.menu;

import clinica.model.*;
import clinica.repository.ClinicaRepository;
import clinica.service.ClinicaService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ClinicaMenu
{
    public static void exibir()
    {
        ClinicaRepository repo = new ClinicaRepository();
        ClinicaService service = new ClinicaService(repo);

        Scanner sc = new Scanner(System.in);
        int opcao;

        do
        {
            System.out.println("\n==== Menu Clínica ====");
            System.out.println("1 - Cadastrar Paciente");
            System.out.println("2 - Cadastrar Médico");
            System.out.println("3 - Marcar Consulta");
            System.out.println("4 - Médicos com mais consultas");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao)
            {
                case 1 ->
                {
                    System.out.print("Nome do paciente: ");
                    String nome = sc.nextLine();

                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();

                    repo.adicionarPaciente(new Paciente(nome, cpf));
                }

                case 2 ->
                {
                    System.out.print("Nome do médico: ");
                    String nome = sc.nextLine();

                    System.out.print("Especialidade: ");
                    String esp = sc.nextLine();

                    repo.adicionarMedico(new Medico(nome, esp));
                }

                case 3 ->
                {
                    if (repo.getPacientes().isEmpty() || repo.getMedicos().isEmpty())
                    {
                        System.out.println("Cadastre pelo menos 1 paciente e 1 médico.");
                        break;
                    }

                    Paciente p = repo.getPacientes().get(0);
                    Medico m = repo.getMedicos().get(0);
					Local l = repo.getLocais().get(0); 

                    repo.adicionarConsulta(new Consulta(p, m, l, LocalDateTime.now().plusDays(1)));
                    System.out.println("Consulta marcada!");
                }

                case 4 ->
                {
                    System.out.println("Médicos com mais consultas:");
                    service.medicosComMaisConsultas().forEach(System.out::println);
                }

                case 0 -> System.out.println("Encerrando...");
				
                default -> System.out.println("Opção inválida!");
            }
        }
        while (opcao != 0);
    }
}
