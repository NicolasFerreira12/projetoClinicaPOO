package clinica.service;

import clinica.model.*;
import clinica.repository.ClinicaRepository;

public class ClinicaService 
{
    private ClinicaRepository repo;

    public ClinicaService(ClinicaRepository repo) 
    {
        this.repo = repo;
    }

    // 1. Quantos médicos estão cadastrados?
    public void totalMedicos() 
    {
        System.out.println("Total de médicos cadastrados: " + repo.getMedicos().size());
    }

    // 2. Quantas consultas cada médico tem?
    public void consultasPorMedico()
    {
        for (Medico m : repo.getMedicos())
        {
            int contador = 0;
            
            for (Consulta c : repo.getConsultas())
            {
                if (c.getMedico().equals(m))
                {
                    contador++;
                }
            }
            System.out.println(m.getNome() + " tem " + contador + " consultas.");
        }
    }

    // 3. Quantas consultas cada paciente marcou?
    public void consultasPorPaciente()
    {
        for (Paciente p : repo.getPacientes())
        {
            int contador = 0;
            for (Consulta c : repo.getConsultas())
            {
                if (c.getPaciente().equals(p))
                {
                contador++;
                }
            }
            System.out.println(p.getNome() + " marcou " + contador + " consultas.");
        }   
    }

    // 4. Qual médico tem mais consultas?
    public void exibirMedicoComMaisConsultas()
    {
        Medico escolhido = null;
        int max = 0;

        for (Medico m : repo.getMedicos())
        {
            int contador = 0;
            for (Consulta c : repo.getConsultas())
            {
                if (c.getMedico().equals(m))
                {
                    contador++;
                }
            }

            if (contador > max)
            {
                max = contador;
                escolhido = m;
            }
        }

        if (escolhido != null)
        {
            System.out.println("Médico mais ocupado: " + escolhido.getNome() + " com " + max + " consultas.");
        }
        else
        {
            System.out.println("Nenhum médico encontrado.");
        }
    }

    // 5. Quantas consultas já foram realizadas?
    public void totalConsultasRealizadas()
    {
        int contador = 0;
        for (Consulta c : repo.getConsultas())
        {
            if (c.isRealizado())
            {
                contador++;
            }
        }
        System.out.println("Consultas realizadas: " + contador);
    }

    // 6. Quantos pacientes estão cadastrados?

    public void totalPacientes()
    {
        System.out.println("Total de pacientes cadastrados: " + repo.getPacientes().size());
    }
}