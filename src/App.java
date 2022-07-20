import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP
        // String url = "https://api.mocki.io/v2/549a5d8b"; // API do IMDB estava fora do ar
        // ExtratorDeConteudo extrator = new ExtratorDeConteudodoIMDB();

        String url ="https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-07-01&end_date=2022-07-20";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoNASA();

        var http = new ClienteHTTP();
        String json = http.buscaDados(url);

        // exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        var geradora = new stickerGenerator();

        for (int i = 0; i < 10; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "../saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}
