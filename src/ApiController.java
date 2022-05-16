public class ApiController {

    static public void Api(Api[] apis) {
        for (Api api :apis) {
            api.PrintJson();
        }
    }
}
