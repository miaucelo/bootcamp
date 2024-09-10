package br.com.dio.desafio.dominio;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLWriter {

    public void writeXML(Bootcamp bootcamp) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement("Bootcamp");
            document.appendChild(rootElement);

            Element nomeElement = document.createElement("nome");
            nomeElement.appendChild(document.createTextNode(bootcamp.getNome()));
            rootElement.appendChild(nomeElement);

            Element descricaoElement = document.createElement("descricao");
            descricaoElement.appendChild(document.createTextNode(bootcamp.getDescricao()));
            rootElement.appendChild(descricaoElement);

            Element devsElement = document.createElement("desenvolvedores");
            for (Dev dev : bootcamp.getDevsInscritos()) {
                Element devElement = document.createElement("dev");
                devElement.appendChild(document.createTextNode(dev.getNome()));
                devsElement.appendChild(devElement);
            }
            rootElement.appendChild(devsElement);

            Element conteudosElement = document.createElement("conteudos");
            for (Conteudo conteudo : bootcamp.getConteudos()) {
                Element conteudoElement = document.createElement("conteudo");

                Element tituloElement = document.createElement("titulo");
                tituloElement.appendChild(document.createTextNode(conteudo.getTitulo()));
                conteudoElement.appendChild(tituloElement);

                Element descricaoConteudoElement = document.createElement("descricao");
                descricaoConteudoElement.appendChild(document.createTextNode(conteudo.getDescricao()));
                conteudoElement.appendChild(descricaoConteudoElement);

                conteudosElement.appendChild(conteudoElement);
            }
            rootElement.appendChild(conteudosElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("InserirDados.xml"));

            transformer.transform(source, result);

            System.out.println("Arquivo XML salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
