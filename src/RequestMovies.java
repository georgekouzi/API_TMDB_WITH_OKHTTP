import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestMovies extends Api {

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<Movies> gistJsonAdapter = moshi.adapter(Movies.class);
    private final OkHttpClient client = new OkHttpClient();
    private Request request;
    private final String url;
    private  Movies movieName;

    public RequestMovies(String url){
        this.url = url;
    }

    @Override
    public void runApi() {
        this.request = new Request.Builder()
                .url(url)
                .build();
                PrintMoviesName();
    }

    private void PrintMoviesName() {
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
            return gistJsonAdapter.fromJson(Objects.requireNonNull(response.body()).source());
        }
    }


}
