package br.com.dio.desafio.dominio;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLReader {

    public void readXML() {
        try {
            File inputFile = new File("InserirDados.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputFile);

            document.getDocumentElement().normalize();

            System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());

            NodeList nomeList = document.getElementsByTagName("nome");
            NodeList descricaoList = document.getElementsByTagName("descricao");

            if (nomeList.getLength() > 0) {
                System.out.println("Nome do Bootcamp: " + nomeList.item(0).getTextContent());
            }
            if (descricaoList.getLength() > 0) {
                System.out.println("Descrição do Bootcamp: " + descricaoList.item(0).getTextContent());
            }

            NodeList devsList = document.getElementsByTagName("dev");
            System.out.println("Desenvolvedores inscritos:");
            for (int i = 0; i < devsList.getLength(); i++) {
                System.out.println("- " + devsList.item(i).getTextContent());
            }

            NodeList conteudosList = document.getElementsByTagName("conteudo");
            System.out.println("Conteúdos:");
            for (int i = 0; i < conteudosList.getLength(); i++) {
                Element conteudo = (Element) conteudosList.item(i);
                System.out.println("- Título: " + conteudo.getElementsByTagName("titulo").item(0).getTextContent());
                System.out
                        .println("  Descrição: " + conteudo.getElementsByTagName("descricao").item(0).getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
