package common.exceptions;

import lombok.Getter;

import java.io.Serial;

@Getter
public class BussinessException extends  Exception{

    @Serial
    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_CODE = 400;

    private final Integer code;
    public BussinessException(Exception e) {
        super(e);
        this.code = DEFAULT_CODE;
    }

    public BussinessException(String e) {
        super(e);
        code = DEFAULT_CODE;
    }

    public BussinessException(String e, Integer code) {
        super(e);
        this.code = code;
    }

    public BussinessException(String message, Exception e) {
        super(message, e);
        this.code = DEFAULT_CODE;
    }

}
