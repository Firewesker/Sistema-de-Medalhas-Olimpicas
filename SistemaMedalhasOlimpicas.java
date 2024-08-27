
/*  Aluno: Israel Da Silva De Andrade
   Matricula: 24213050191
   Polo: Duque de Caxias */

import java.util.ArrayList;
import java.util.List;

// Classe base para participantes (atletas e equipes)
abstract class Participante {
    private String nome;
    private int ouro;
    private int prata;
    private int bronze;

    public Participante(String nome) {
        this.nome = nome;
        this.ouro = 0;
        this.prata = 0;
        this.bronze = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getOuro() {
        return ouro;
    }

    public int getPrata() {
        return prata;
    }

    public int getBronze() {
        return bronze;
    }

    public void adicionarOuro() {
        ouro++;
    }

    public void adicionarPrata() {
        prata++;
    }

    public void adicionarBronze() {
        bronze++;
    }

    public int getPontos() {
        return ouro * 10 + prata * 8 + bronze * 6;
    }
}

// Classe para Atletas
class Atleta extends Participante {
    private int idade;
    private String nacionalidade;

    public Atleta(String nome, int idade, String nacionalidade) {
        super(nome);
        this.idade = idade;
        this.nacionalidade = nacionalidade;
    }

    public int getIdade() {
        return idade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }
}

// Classe para Equipes
class Equipe extends Participante {
    private int quantidadeAtletas;
    private List<Atleta> atletas;

    public Equipe(String nome, int quantidadeAtletas) {
        super(nome);
        this.quantidadeAtletas = quantidadeAtletas;
        this.atletas = new ArrayList<>();
    }

    public int getQuantidadeAtletas() {
        return quantidadeAtletas;
    }

    public void adicionarAtleta(Atleta atleta) {
        atletas.add(atleta);
    }

    public List<Atleta> getAtletas() {
        return atletas;
    }
}

// Classe para Esportes
class Esporte {
    private String nome;
    private String tipo; // Individual ou Equipe

    public Esporte(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }
}

// Classe para Eventos
class Evento {
    private String nome;
    private String data;
    private Esporte esporte;
    private int totalParticipantes;
    private Participante ouro;
    private Participante prata;
    private Participante bronze;

    public Evento(String nome, String data, Esporte esporte, int totalParticipantes) {
        this.nome = nome;
        this.data = data;
        this.esporte = esporte;
        this.totalParticipantes = totalParticipantes;
    }

    public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public Esporte getEsporte() {
        return esporte;
    }

    public int getTotalParticipantes() {
        return totalParticipantes;
    }

    public void adicionarMedalhas(Participante participante, int posicao) {
        if (posicao == 1) {
            ouro = participante;
            participante.adicionarOuro();
        } else if (posicao == 2) {
            prata = participante;
            participante.adicionarPrata();
        } else if (posicao == 3) {
            bronze = participante;
            participante.adicionarBronze();
        }
    }

    public Participante getOuro() {
        return ouro;
    }

    public Participante getPrata() {
        return prata;
    }

    public Participante getBronze() {
        return bronze;
    }
}

// Classe principal para gerenciar o sistema
public class SistemaMedalhasOlimpicas {

    private List<Atleta> atletas;
    private List<Equipe> equipes;
    private List<Esporte> esportes;
    private List<Evento> eventos;

    public SistemaMedalhasOlimpicas() {
        atletas = new ArrayList<>();
        equipes = new ArrayList<>();
        esportes = new ArrayList<>();
        eventos = new ArrayList<>();
    }

    public void cadastrarAtleta(String nome, int idade, String nacionalidade) {
        Atleta atleta = new Atleta(nome, idade, nacionalidade);
        atletas.add(atleta);
    }

    public void cadastrarEquipe(String nome, int quantidadeAtletas) {
        Equipe equipe = new Equipe(nome, quantidadeAtletas);
        equipes.add(equipe);
    }

    public void cadastrarEsporte(String nome, String tipo) {
        Esporte esporte = new Esporte(nome, tipo);
        esportes.add(esporte);
    }

    public void cadastrarEvento(String nome, String data, Esporte esporte, int totalParticipantes) {
        Evento evento = new Evento(nome, data, esporte, totalParticipantes);
        eventos.add(evento);
    }

    public void adicionarAtletaEmEquipe(Equipe equipe, Atleta atleta) {
        equipe.adicionarAtleta(atleta);
    }

    public void adicionarMedalhas(Evento evento, Participante participante, int posicao) {
        evento.adicionarMedalhas(participante, posicao);
    }

    public static void main(String[] args) {
        SistemaMedalhasOlimpicas sistema = new SistemaMedalhasOlimpicas();

        // Cadastro de atletas
        sistema.cadastrarAtleta("João Silva", 25, "Brasil");
        sistema.cadastrarAtleta("Maria Santos", 22, "Brasil");
        sistema.cadastrarAtleta("Pedro Rodrigues", 28, "Portugal");

        // Cadastro de equipes
        sistema.cadastrarEquipe("Time Brasil", 4);
        sistema.cadastrarEquipe("Time Portugal", 5);

        // Cadastro de esportes
        sistema.cadastrarEsporte("Futebol", "Equipe");
        sistema.cadastrarEsporte("Natação", "Individual");

        // Cadastro de eventos
        sistema.cadastrarEvento("Final do Futebol Masculino", "2024-08-10", sistema.esportes.get(0), 2);
        sistema.cadastrarEvento("Final dos 100m Livre Masculino", "2024-08-11", sistema.esportes.get(1), 8);

        // Adicionar atletas em equipes
        sistema.adicionarAtletaEmEquipe(sistema.equipes.get(0), sistema.atletas.get(0));
        sistema.adicionarAtletaEmEquipe(sistema.equipes.get(0), sistema.atletas.get(1));
        sistema.adicionarAtletaEmEquipe(sistema.equipes.get(1), sistema.atletas.get(2));

        // Adicionar medalhas
        sistema.adicionarMedalhas(sistema.eventos.get(0), sistema.equipes.get(0), 1); // Time Brasil - Ouro
        sistema.adicionarMedalhas(sistema.eventos.get(0), sistema.equipes.get(1), 2); // Time Portugal - Prata
        sistema.adicionarMedalhas(sistema.eventos.get(1), sistema.atletas.get(0), 1); // João Silva - Ouro

        // Mostrar resultados
        System.out.println("Resultados dos eventos:");
        for (Evento evento : sistema.eventos) {
            System.out.println("Evento: " + evento.getNome());
            System.out.println("Data: " + evento.getData());
            System.out.println("Esporte: " + evento.getEsporte().getNome());
            System.out.println("Total de participantes: " + evento.getTotalParticipantes());
            if (evento.getOuro() != null) {
                System.out.println("Ouro: " + evento.getOuro().getNome() + " - Pontos: " + evento.getOuro().getPontos());
            }
            if (evento.getPrata() != null) {
                System.out.println("Prata: " + evento.getPrata().getNome() + " - Pontos: " + evento.getPrata().getPontos());
            }
            if (evento.getBronze() != null) {
                System.out.println("Bronze: " + evento.getBronze().getNome() + " - Pontos: " + evento.getBronze().getPontos());
            }
            System.out.println("--------------------");
        }

        System.out.println("Total de pontos dos participantes:");
        for (Atleta atleta : sistema.atletas) {
            System.out.println(atleta.getNome() + ": " + atleta.getPontos() + " pontos");
        }
        for (Equipe equipe : sistema.equipes) {
            System.out.println(equipe.getNome() + ": " + equipe.getPontos() + " pontos");
        }
    }
}