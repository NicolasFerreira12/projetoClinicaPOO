package clinica.model;

public class Medico
{
    private String nome;
    private String especialidade;

    public Medico(String nome, String especialidade)
    {
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getEspecialidade()
    {
        return especialidade;
    }

    public void setEspecialidade(String especialidade)
    {
        this.especialidade = especialidade;
    }

    @Override
    public String toString()
    {
        return "Dr(a). " + nome + " - " + especialidade;
    }
}
