package com.eduarda.api_livros.principal;

import com.eduarda.api_livros.dto.DadosAutor;
import com.eduarda.api_livros.dto.DadosLivro;
import com.eduarda.api_livros.dto.RespostaAPI;
import com.eduarda.api_livros.model.Autor;
import com.eduarda.api_livros.model.Livro;
import com.eduarda.api_livros.repository.AutorRepository;
import com.eduarda.api_livros.repository.LivroRepository;
import com.eduarda.api_livros.service.ConsumoAPI;
import com.eduarda.api_livros.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

import java.util.List;
import java.util.Scanner;

@Component
public class Menu {

    Scanner leitura = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/?search=";

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    public void exibirMenu() {

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("===== CATÁLOGO DE LIVROS =====");
            System.out.println("1 - Buscar livro por título ");
            System.out.println("2 - Listar livros registrados");
            System.out.println("3 - Listar autores");
            System.out.println("4 - Listar autores vivos em determinado ano");
            System.out.println("5 - Listar livros por idioma");
            System.out.println("6 - Quantidade de livros por idioma");
            System.out.println(" ");
            System.out.println("0 - Sair ");

            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {

                case 1:
                    buscarLivro();
                    break;

                case 2:
                    listarLivros();
                    break;

                case 3:
                    listarAutores();
                    break;

                case 4:
                    autoresVivos();
                    break;

                case 5:
                    livrosPorIdioma();
                    break;

                case 6:
                    estatisticaIdioma();
                    break;
            }
        }
    }

    private void buscarLivro() {

        System.out.println("Digite o nome do livro: ");
        String titulo = leitura.nextLine();

        String json = consumo.obterDados(URL_BASE + titulo.replace(" ", "+"));

        RespostaAPI resposta = conversor.obterDados(json, RespostaAPI.class);

        Optional<DadosLivro> livroBuscado = resposta.resultados().stream().findFirst();

        if (livroBuscado.isPresent()) {

            DadosLivro dados = livroBuscado.get();
            DadosAutor dadosAutor = dados.autores().get(0);
            Autor autor = new Autor(dadosAutor);
            Livro livro = new Livro(dados, autor);

            autorRepository.save(autor);
            livroRepository.save(livro);

            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private void listarLivros() {

        List<Livro> livros = livroRepository.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    private void autoresVivos() {

        System.out.println("Digite o ano: ");
        int ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autores = autorRepository.findAutoresVivosNoAno(ano);
        autores.forEach(System.out::println);
    }

    private void livrosPorIdioma() {

        System.out.println("Digite o idioma (ex: en, pt, es): ");
        String idioma = leitura.nextLine();

        List<Livro> livros = livroRepository.findByIdioma(idioma);

        livros.forEach(System.out::println);
    }

    private void estatisticaIdioma() {

        long ingles = livroRepository.countByIdioma("en");
        long portugues = livroRepository.countByIdioma("pt");

        System.out.println("Livros em inglês: " + ingles);
        System.out.println("Livros em português: " + portugues);
    }
}
