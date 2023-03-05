package id.hadiyan.taxmanagement.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponse<T> {
    private Integer statusCode;
    private String message;
    private String error;
    private T data;

    public BaseResponse(HttpStatus status, T data) {
        this.message = status.getReasonPhrase();
        this.data = data;
        this.statusCode = status.value();
    }
}
