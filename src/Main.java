
public class Main {
    public static void main(String [] args){
        String url= "https://api.themoviedb.org/3/discover/movie?api_key=96624ea86553cd7a4caed4ecbdc35ec1";
        ApiController.Api(new Api[]{new RequestMovies(url)});

    }


}




