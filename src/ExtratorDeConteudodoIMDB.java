import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudodoIMDB implements ExtratorDeConteudo {
    public List<Conteudo> extraiConteudos(String json) {

        // pegar apenas os dados que interessam (título, poster e classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        // popular a lista de conteudos
        for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("image")
                    .replaceAll("(@+) (.*).jpg$", "$1.jpg");
            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
