package clinica.repository;

import clinica.model.*;
import java.util.ArrayList;
import java.util.List;

public class ClinicaRepository
{
    private List<Consulta> consultas = new ArrayList<>(); 
    private List<Medico> medicos = new ArrayList<>();
    private List<Paciente> pacientes = new ArrayList<>();
    private List<Local> locais = new ArrayList<>();

    public void adicionarConsulta(Consulta consulta)
    {
        consultas.add(consulta);
    }

    public void adicionarMedico(Medico medico)
    {
        medicos.add(medico);
    }

    public void adicionarPaciente(Paciente paciente)
    {
        pacientes.add(paciente);
    }

    public void adicionarLocal(Local local)
    {
        locais.add(local);
    }

    public List<Consulta> getConsultas()
    {
        return consultas;
    }

    public List<Medico> getMedicos()
    {
        return medicos;
    }

    public List<Paciente> getPacientes()
    {
        return pacientes;
    }

    public List<Local> getLocais()
    {
        return locais;
    }
}
