import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestMovies extends Api {

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<Movies> gistJsonAdapter = moshi.adapter(Movies.class);
    final OkHttpClient client = new OkHttpClient();
    private Request request;
    private String url;

    public RequestMovies(String url){
        url = url;
        runApi(url);
    }

    @Override
    public void runApi(String url) {
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .build();
        this.request = request;
    }

    @Override
    public void PrintJson() {
        Movies movieName= null;
        try {
            movieName =jsonList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(movieName !=null){
            for (Movie movie : movieName.getMovies()){
                System.out.println(movie.getOriginal_title());
            }
        }

    }

    private Movies jsonList()throws IOException {
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            Movies moviesList = gistJsonAdapter.fromJson(response.body().source());
            return moviesList;
        }
    }


}
