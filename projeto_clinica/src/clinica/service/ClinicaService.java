package clinica.service;

import clinica.model.*;

import clinica.repository.ClinicaRepository;
import java.util.*;
import java.util.stream.Collectors;

public class ClinicaService
{
    private ClinicaRepository repo;

    public ClinicaService(ClinicaRepository repo)
    {
        this.repo = repo;
    }

    // Exemplo de "pergunta estratégica": quais médicos têm mais consultas?
    public List<Medico> medicosComMaisConsultas()
    {
        Map<Medico, Long> contagem = repo.getConsultas().stream()
            .collect(Collectors.groupingBy(Consulta::getMedico, Collectors.counting()));

        return contagem.entrySet().stream()
            .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
            .map(Map.Entry::getKey)
            .toList();
    }
}
