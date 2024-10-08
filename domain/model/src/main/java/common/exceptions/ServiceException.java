package common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder(toBuilder = true)
public class ServiceException extends Exception{


    private static final long serialVersionUID = 1L;

    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String e) {
        super(e);
    }
}
