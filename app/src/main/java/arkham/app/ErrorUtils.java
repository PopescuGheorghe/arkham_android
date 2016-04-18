package arkham.app;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import arkham.app.datamodel.APIError;
import arkham.app.service.ServiceGenerator;

import java.io.IOException;
import java.lang.annotation.Annotation;

public class    ErrorUtils {

    public static APIError parseError(Response<?> response) {
        Converter<ResponseBody, APIError> converter =
                ServiceGenerator.getRetrofit()
                        .responseBodyConverter(APIError.class, new Annotation[0]);
        APIError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }

        return error;
    }
}