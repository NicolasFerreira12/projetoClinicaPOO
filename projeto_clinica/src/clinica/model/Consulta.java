package clinica.model;

import java.time.LocalDateTime; 

public class Consulta
{
    private Paciente paciente;
    private Medico medico;
    private Local local;
    private LocalDateTime dataHora;
    private boolean realizado;

    public Consulta(Paciente paciente, Medico medico, Local local, LocalDateTime dataHora)
    {
        this.paciente = paciente;
        this.medico = medico;
        this.local = local;
        this.dataHora = dataHora;
        this.realizado = false;
    }

    public Paciente getPaciente()
    {
        return paciente;
    }

    public Medico getMedico()
    {
        return medico;
    }

    public Local getLocal()
    {
        return local;
    }

    public LocalDateTime getDataHora()
    {
        return dataHora;
    }

    public boolean isRealizado()
    {
        return realizado;
    }

    public void marcarComoRealizado()
    {
        this.realizado = true;
    }

    @Override
    public String toString()
    {
        return "Consulta em " + local +
               " | Médico: " + medico.getNome() +
               " | Paciente: " + paciente.getNome() +
               " | Data: " + dataHora;
    }
}
