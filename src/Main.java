import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Main {
    public static void main(String [] args){
        String url= "https://api.themoviedb.org/3/discover/movie?api_key=96624ea86553cd7a4caed4ecbdc35ec1";
        ApiController.Api(new Api[]{new RequestMovies(url)});

    }


}




