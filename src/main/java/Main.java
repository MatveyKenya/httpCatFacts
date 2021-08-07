import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;

public class Main {
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args){

        CloseableHttpClient httpClient = clientCreate();
        HttpGet request = new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
        }catch (IOException e){
            e.printStackTrace();
        }

        if (response != null){
            try{
                List<Post> posts = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<Post>>() {});
                //posts.forEach(System.out::println);

                System.out.println("=========== Отсортированные записи ===========");
                posts.stream()
                        .filter(value -> value.getUpvote() != null && value.getUpvote() > 0)
                        .forEach(System.out::println);

            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    static private CloseableHttpClient clientCreate(){
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
    }
}
