import java.util.Scanner;

import br.com.dio.desafio.dominio.Bootcamp;
import br.com.dio.desafio.dominio.Curso;
import br.com.dio.desafio.dominio.Dev;
import br.com.dio.desafio.dominio.XMLReader;
import br.com.dio.desafio.dominio.XMLWriter;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        XMLWriter xmlWriter = new XMLWriter();
        XMLReader xmlReader = new XMLReader();

        System.out.println("Bem-vindo ao sistema de bootcamp.");
        System.out.print("Digite o nome do Bootcamp: ");
        String nomeBootcamp = scanner.nextLine();

        System.out.print("Digite a descrição do Bootcamp: ");
        String descricaoBootcamp = scanner.nextLine();

        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setNome(nomeBootcamp);
        bootcamp.setDescricao(descricaoBootcamp);

        System.out.println("\nDigite o nome dos desenvolvedores inscritos. Digite 'fim' para terminar:");
        boolean adicionarMaisDevs = true;
        while (adicionarMaisDevs) {
            System.out.print("Nome do desenvolvedor: ");
            String nomeDev = scanner.nextLine();
            if (nomeDev.equalsIgnoreCase("fim")) {
                adicionarMaisDevs = false;
            } else {
                Dev dev = new Dev();
                dev.setNome(nomeDev);
                bootcamp.getDevsInscritos().add(dev);
            }
        }

        boolean adicionarMaisConteudos = true;
        while (adicionarMaisConteudos) {
            System.out.println("\nAdicionar conteúdo:");
            System.out.print("Digite o título do conteúdo: ");
            String tituloConteudo = scanner.nextLine();

            System.out.print("Digite a descrição do conteúdo: ");
            String descricaoConteudo = scanner.nextLine();

            Curso curso = new Curso();
            curso.setTitulo(tituloConteudo);
            curso.setDescricao(descricaoConteudo);
            bootcamp.getConteudos().add(curso);

            System.out.print("\nDeseja adicionar mais conteúdo? (S/N): ");
            String resposta = scanner.nextLine();
            adicionarMaisConteudos = resposta.equalsIgnoreCase("S");
        }

        xmlWriter.writeXML(bootcamp);

        System.out.println("Conteúdos cadastrados e salvos em XML.");

        xmlReader.readXML();

        scanner.close();
    }
}
