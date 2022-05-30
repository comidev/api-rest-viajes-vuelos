package comidev.dswgrupo5.exceptions.badRequest;

public class BadRequestException extends RuntimeException {
    private static final String DESCRIPTION = "Bad Request Exception (400)";

    public BadRequestException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}

class FieldInvalidException extends BadRequestException {
    private static final String DESCRIPTION = "Field Invalid :(";

    public FieldInvalidException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}

class MalformedHeaderException extends BadRequestException {
    private static final String DESCRIPTION = "Field Malformed :(";

    public MalformedHeaderException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
